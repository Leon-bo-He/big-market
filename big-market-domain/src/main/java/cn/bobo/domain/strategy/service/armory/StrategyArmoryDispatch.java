package cn.bobo.domain.strategy.service.armory;

import cn.bobo.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bobo.domain.strategy.model.entity.StrategyEntity;
import cn.bobo.domain.strategy.model.entity.StrategyRuleEntity;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.types.common.Constants;
import cn.bobo.types.enums.ResponseCode;
import cn.bobo.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
public class StrategyArmoryDispatch implements IStrategyArmory, IStrategyDispatch {

    @Resource
    private IStrategyRepository repository;

    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {

        //  1. Query and assemble the strategy award list from the database
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        // 2. Cache the strategy award inventory in Redis - used for decr operation
        for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
            Integer awardId = strategyAward.getAwardId();
            Integer awardCount = strategyAward.getAwardCount();
            cacheStrategyAwardInventory(strategyId, awardId, awardCount);
        }


        // 3.1 default strategy assembly
        assembleLotteryStrategy(String.valueOf(strategyId), strategyAwardEntities);

        // 3.2. weight strategy assembly - different prizes based on rule_weight from the database
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if (ruleWeight == null) return true;

        StrategyRuleEntity strategyRuleEntity = repository.queryStrategyRule(strategyId, ruleWeight);
        if (strategyRuleEntity == null) {
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(),
                    ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }

        Map<String, List<Integer>> ruleWeightValueMap = strategyRuleEntity.getRuleWeightValues();
        Set<String> keys = ruleWeightValueMap.keySet();
        for (String key : keys){
            List<Integer> ruleWeightValues = ruleWeightValueMap.get(key);
            ArrayList<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity->!ruleWeightValues.contains(entity.getAwardId()));
            assembleLotteryStrategy(String.valueOf(strategyId).concat("_").concat(key), strategyAwardEntitiesClone);
        }

        return true;
    }

    private void cacheStrategyAwardInventory(Long strategyId, Integer awardId, Integer awardCount) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        repository.cacheStrategyAwardInventory(cacheKey, awardCount);
    }

    private boolean assembleLotteryStrategy(String key, List<StrategyAwardEntity> strategyAwardEntities) {

        if (strategyAwardEntities.isEmpty() || strategyAwardEntities == null) return false;

        //  1. Get the minimum award rate
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 2. Calculate the sum of the award rates
        BigDecimal sumAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Determine the rate range by calculating sumAwardRate divided by min award rate
        BigDecimal rateRange = sumAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 4. Create a list to store the strategy award rate
        // The more placeholders a prize has, the higher its probability
        ArrayList<Integer> strategyAwardRateSearchTable = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
            Integer awardID = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++) {
                strategyAwardRateSearchTable.add(awardID);
            }
        }

        // 5. Shuffle the stored prizes.
        Collections.shuffle(strategyAwardRateSearchTable);

        // 6. Create a Map where each key represents a probability value,
        // and use it to determine the corresponding prize ID.
        Map<Integer, Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for (int i = 0; i < strategyAwardRateSearchTable.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardRateSearchTable.get(i));
        }

        // 7. Store the strategy to redis
        repository.storeStrategyAwardRateSearchTable(key, shuffleStrategyAwardSearchRateTable.size(),
                shuffleStrategyAwardSearchRateTable);

        return true;
    }


    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(String.valueOf(strategyId), new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        int rateRange = repository.getRateRange(key);
        return repository.getStrategyAwardAssemble(key, new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Boolean subtractAwardInventory(Long strategyId, Integer awardId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        return repository.subtractAwardInventory(cacheKey);
    }
}
