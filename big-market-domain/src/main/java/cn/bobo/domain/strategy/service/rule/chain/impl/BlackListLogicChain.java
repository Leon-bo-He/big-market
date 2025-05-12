package cn.bobo.domain.strategy.service.rule.chain.impl;

import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.bobo.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@Component("rule_blacklist")
public class BlackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Override
    public Integer logic(String userId, Long strategyId) {
        log.info("Ruffle chain of responsibility - blacklist \u001B[31mSTART\u001B[0m; userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        // check if userId is in the blacklist
        String[] userBlackIds = splitRuleValue[1].split(Constants.COMMA);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("Ruffle chain of responsibility - blacklist \u001B[31mTAKE_OVER\u001B[0m; userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
                return awardId;
            }
        }
        // if userId is not in the blacklist, continue to the next logic chain
        log.info("Ruffle chain of responsibility - blacklist \u001B[31mALLOW\u001B[0m; userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return "rule_blacklist";
    }
}
