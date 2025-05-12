package cn.bobo.domain.strategy.service.rule.chain.impl;

import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.armory.IStrategyDispatch;
import cn.bobo.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.bobo.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author BO HE
 */

@Slf4j
@Component("rule_weight")
public class RuleWeightLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;
    @Resource
    private IStrategyDispatch strategyDispatch;

    private Long userScore = 0L;


    @Override
    public Integer logic(String userId, Long strategyId) {
        log.info("Ruffle chain of responsibility - rule_weight \u001B[31mSTART\u001B[0m; userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());

        // 1. query used points by user using userId
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if (null == analyticalValueGroup || analyticalValueGroup.isEmpty()) return null;

        // 2. transform key value and sort
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);
        Collections.reverse(analyticalSortedKeys);

        // 3. find the largest key less than or equal to userScore
        Long nextValue = analyticalSortedKeys.stream()
                .filter(key -> userScore >= key)
                .findFirst()
                .orElse(null);

        if (null != nextValue) {
            Integer awardId = strategyDispatch.getRandomAwardId(strategyId, analyticalValueGroup.get(nextValue));
            log.info("Ruffle chain of responsibility - rule_weight \u001B[31mTAKE_OVER\u001B[0m; userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
            return awardId;
        }

        log.info("Ruffle chain of responsibility - rule_weight \u001B[31mALLOW\u001B[0m; userId:{} strategyId:{}", userId, strategyId);
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return "rule_weight";
    }

    private Map<Long, String> getAnalyticalValue(String ruleValue) {
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, String> ruleValueMap = new HashMap<>();
        for (String ruleValueKey : ruleValueGroups) {

            // check if ruleValueKey is null or empty
            if (ruleValueKey == null || ruleValueKey.isEmpty()) continue;

            // split ruleValueKey by colon
            String[] parts = ruleValueKey.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight invalid value format: " + ruleValueKey);
            }
            ruleValueMap.put(Long.parseLong(parts[0]), ruleValueKey);
        }
        return ruleValueMap;
    }
}
