package cn.bobo.domain.strategy.service;

import java.util.Map;

/**
 * @author BO HE
 */
public interface IRaffleRule {

    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);
}
