package cn.bobo.domain.strategy.service;

import cn.bobo.domain.strategy.model.vo.RuleWeightVO;

import java.util.List;
import java.util.Map;

/**
 * @author BO HE
 */
public interface IRaffleRule {

    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);

    List<RuleWeightVO> queryAwardRuleWeight(Long strategyId);

    List<RuleWeightVO> queryAwardRuleWeightByActivityId(Long activityId);
}
