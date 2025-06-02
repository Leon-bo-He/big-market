package cn.bobo.domain.strategy.service.rule.chain.impl;

import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.bobo.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
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
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("Ruffle chain of responsibility - blacklist {}; userId:{} strategyId:{} ruleModel:{}", Constants.RED_START, userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        // check if userId is in the blacklist
        String[] userBlackIds = splitRuleValue[1].split(Constants.COMMA);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("Ruffle chain of responsibility - blacklist {}; userId:{} strategyId:{} ruleModel:{}", Constants.RED_TAKE_OVER, userId, strategyId, ruleModel());
                return DefaultChainFactory.StrategyAwardVO.builder()
                        .awardId(awardId)
                        .logicModel(ruleModel())
                        .awardRuleValue(Constants.BLACKLIST_RULE_CONFIG_VALUE)
                        .build();
            }
        }
        // if userId is not in the blacklist, continue to the next logic chain
        log.info("Ruffle chain of responsibility - blacklist {}; userId:{} strategyId:{} ruleModel:{}", Constants.RED_ALLOW, userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_BLACKLIST.getCode();
    }
}
