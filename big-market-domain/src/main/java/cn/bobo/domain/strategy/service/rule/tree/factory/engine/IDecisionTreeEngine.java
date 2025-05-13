package cn.bobo.domain.strategy.service.rule.tree.factory.engine;

import cn.bobo.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author BO HE
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
