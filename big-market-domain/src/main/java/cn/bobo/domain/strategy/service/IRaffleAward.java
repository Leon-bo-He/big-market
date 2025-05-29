package cn.bobo.domain.strategy.service;

import cn.bobo.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @author BO HE
 */
public interface IRaffleAward {

    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);

    List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId);

}
