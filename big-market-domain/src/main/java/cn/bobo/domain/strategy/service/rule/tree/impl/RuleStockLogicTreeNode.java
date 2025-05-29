package cn.bobo.domain.strategy.service.rule.tree.impl;

import cn.bobo.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.bobo.domain.strategy.model.vo.StrategyAwardInventoryKeyVO;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.armory.IStrategyDispatch;
import cn.bobo.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bobo.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author BO HE
 */

@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {

    @Resource
    private IStrategyDispatch strategyDispatch;

    @Resource
    private IStrategyRepository repository;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue, Date endDateTime) {

        log.info("rule filter - inventory subtraction - strategyId: {}, awardId: {}", strategyId, awardId);

        Boolean status = strategyDispatch.subtractAwardInventory(strategyId, awardId, endDateTime);
        if (status) {
            // write to delay queue, delay consumption update database record
            // update award inventory record under trigger.job.UpdateAwardInventoryJob
            repository.awardStockConsumeSendQueue(StrategyAwardInventoryKeyVO.builder()
                    .strategyId(strategyId)
                    .awardId(awardId)
                    .build());

            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                    .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                            .awardId(awardId)
                            .awardRuleValue("")
                            .build())
                    .build();
        }


        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }
}
