package cn.bobo.domain.strategy.service.armory;

/**
 * @author BO HE
 */
public interface IStrategyDispatch {

    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);
}
