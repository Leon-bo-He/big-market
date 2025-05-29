package cn.bobo.domain.strategy.service.rule.tree.impl;

import cn.bobo.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.bobo.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bobo.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.bobo.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author BO HE
 */

@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue, Date endDateTime) {
        log.info("rule filter - rule luck(fallback) award userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        String[] split = ruleValue.split(Constants.COLON);
        if (split.length == 0) {
            log.error("rule filter - rule luck(fallback) award exception config error. userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
            throw new RuntimeException("No fallback prize config" + ruleValue);
        }

        // get fallback award config
        Integer luckAwardId = Integer.valueOf(split[0]);
        String awardRuleValue = split.length > 1 ? split[1] : "";

        // return fallback award
        log.info("rule filter - return fallback award userId:{} strategyId:{} awardId:{} awardRuleValue:{}", userId, strategyId, luckAwardId, awardRuleValue);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(luckAwardId)
                        .awardRuleValue(awardRuleValue)
                        .build())
                .build();
    }

}

