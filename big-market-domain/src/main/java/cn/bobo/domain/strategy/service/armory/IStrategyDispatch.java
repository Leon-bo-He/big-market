package cn.bobo.domain.strategy.service.armory;

import java.util.Date;

/**
 * @author BO HE
 */
public interface IStrategyDispatch {

    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);

    Boolean subtractAwardInventory(Long strategyId, Integer awardId, Date endDateTime);
}
