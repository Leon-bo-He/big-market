package cn.bobo.domain.strategy.service.rule.tree;

import cn.bobo.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author BO HE
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);
}
