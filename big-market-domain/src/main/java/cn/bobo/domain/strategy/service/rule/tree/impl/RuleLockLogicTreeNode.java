package cn.bobo.domain.strategy.service.rule.tree.impl;

import cn.bobo.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bobo.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    @Resource
    private IStrategyRepository repository;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("rule filter - rule lock userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);

        long raffleCount = 0L;
        try {
            raffleCount = Long.parseLong(ruleValue);
        } catch (Exception e) {
            throw new RuntimeException("rule filter - rule lock exception ruleValue: " + ruleValue + " config error");
        }


        // query user today's raffle count; strategyId: activityId 1:1 configuration, can directly use strategyId query.
        Integer userRaffleCount = repository.queryTodayUserRaffleCount(userId, strategyId);

        // user raffle count >= rule value, ALLOW
        if (userRaffleCount >= raffleCount) {
            log.info("rule filter - rule lock [ALLOW] userId:{} strategyId:{} awardId:{} raffleCount:{} userRaffleCount:{}", userId, strategyId, awardId, userRaffleCount, userRaffleCount);
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }

        // user raffle count < rule value, TAKE_OVER
        log.info("rule filter - rule lock [TAKE_OVER] userId:{} strategyId:{} awardId:{} raffleCount:{} userRaffleCount:{}", userId, strategyId, awardId, userRaffleCount, userRaffleCount);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }


}
