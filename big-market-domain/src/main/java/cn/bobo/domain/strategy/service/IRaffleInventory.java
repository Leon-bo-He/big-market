package cn.bobo.domain.strategy.service;

import cn.bobo.domain.strategy.model.vo.StrategyAwardInventoryKeyVO;

/**
 * @author BO HE
 * @description raffle inventory related service, get inventory consumer queue
 */
public interface IRaffleInventory {

    StrategyAwardInventoryKeyVO takeQueueValue() throws InterruptedException;

    void updateStrategyAwardInventory(Long strategyId, Integer awardId);
}
