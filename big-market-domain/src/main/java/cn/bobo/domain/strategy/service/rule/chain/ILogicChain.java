package cn.bobo.domain.strategy.service.rule.chain;

import cn.bobo.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @author BO HE
 */
public interface ILogicChain extends ILogicChainArmory{

    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);

}
