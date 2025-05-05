package cn.bobo.domain.strategy.service.armory;

import cn.bobo.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory {

    @Resource
    private IStrategyRepository repository;

    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {

        //  1. Query the strategy award list from the database
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);
        if (strategyAwardEntities.isEmpty() || strategyAwardEntities == null) return false;

        //  2. Get the minimum award rate
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 3. Calculate the sum of the award rates
        BigDecimal sumAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Determine the rate range by calculating sumAwardRate divided by min award rate
        BigDecimal rateRange = sumAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 5. Create a list to store the strategy award rate
        // The more placeholders a prize has, the higher its probability
        ArrayList<Integer> strategyAwardRateSearchTable = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
            Integer awardID = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++) {
                strategyAwardRateSearchTable.add(awardID);
            }
        }

        // 6. Shuffle the stored prizes.
        Collections.shuffle(strategyAwardRateSearchTable);

        // 7. Create a Map where each key represents a probability value,
        // and use it to determine the corresponding prize ID.
        Map<Integer, Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for (int i = 0; i < strategyAwardRateSearchTable.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardRateSearchTable.get(i));
        }

        // 8. Store the strategy to redis
        repository.storeStrategyAwardRateSearchTable(strategyId, shuffleStrategyAwardSearchRateTable.size(),
                shuffleStrategyAwardSearchRateTable);

        return true;
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
