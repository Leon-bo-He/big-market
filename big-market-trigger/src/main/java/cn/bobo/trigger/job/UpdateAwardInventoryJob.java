package cn.bobo.trigger.job;

import cn.bobo.domain.strategy.model.vo.StrategyAwardInventoryKeyVO;
import cn.bobo.domain.strategy.service.IRaffleInventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@Component()
public class UpdateAwardInventoryJob {

    @Resource
    private IRaffleInventory raffleInventory;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        try {
            log.info("Scheduled task, update award inventory [Delay queue acquisition, reduce database update frequency, do not generate competition]");
            StrategyAwardInventoryKeyVO strategyAwardStockKeyVO = raffleInventory.takeQueueValue();
            if (null == strategyAwardStockKeyVO) return;
            log.info("Scheduled task, update award inventory strategyId:{} awardId:{}", strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
            raffleInventory.updateStrategyAwardInventory(strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
        } catch (Exception e) {
            log.error("Scheduled task, update award inventory failed", e);
        }
    }
}
