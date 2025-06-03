package cn.bobo.domain.credit.service;

import cn.bobo.domain.credit.event.CreditAdjustSuccessMessageEvent;
import cn.bobo.domain.credit.model.aggregate.TradeAggregate;
import cn.bobo.domain.credit.model.entity.CreditAccountEntity;
import cn.bobo.domain.credit.model.entity.CreditOrderEntity;
import cn.bobo.domain.credit.model.entity.TaskEntity;
import cn.bobo.domain.credit.model.entity.TradeEntity;
import cn.bobo.domain.credit.repository.ICreditRepository;
import cn.bobo.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@Service
public class CreditAdjustService implements ICreditAdjustService {

    @Resource
    private ICreditRepository creditRepository;
    @Resource
    private CreditAdjustSuccessMessageEvent creditAdjustSuccessMessageEvent;

    @Override
    public String createOrder(TradeEntity tradeEntity) {
        log.info("adjust account credit start. userId:{} tradeName:{} amount:{}",
                tradeEntity.getUserId(), tradeEntity.getTradeName(), tradeEntity.getAmount());
        // 1. create credit account entity
        CreditAccountEntity creditAccountEntity = TradeAggregate.createCreditAccountEntity(
                tradeEntity.getUserId(),
                tradeEntity.getAmount());

        // 2. create credit order entity
        CreditOrderEntity creditOrderEntity = TradeAggregate.createCreditOrderEntity(
                tradeEntity.getUserId(),
                tradeEntity.getTradeName(),
                tradeEntity.getTradeType(),
                tradeEntity.getAmount(),
                tradeEntity.getOutBusinessNo());

        // 3. create task entity for message compensation
        CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage creditAdjustSuccessMessage = new CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage();
        creditAdjustSuccessMessage.setUserId(tradeEntity.getUserId());
        creditAdjustSuccessMessage.setOrderId(creditOrderEntity.getOrderId());
        creditAdjustSuccessMessage.setAmount(tradeEntity.getAmount());
        creditAdjustSuccessMessage.setOutBusinessNo(tradeEntity.getOutBusinessNo());
        BaseEvent.EventMessage<CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage> creditAdjustSuccessMessageEventMessage = creditAdjustSuccessMessageEvent.buildEventMessage(creditAdjustSuccessMessage);

        TaskEntity taskEntity = TradeAggregate.createTaskEntity(tradeEntity.getUserId(), creditAdjustSuccessMessageEvent.topic(), creditAdjustSuccessMessageEventMessage.getId(), creditAdjustSuccessMessageEventMessage);

        // 4. create trade aggregate object
        TradeAggregate tradeAggregate = TradeAggregate.builder()
                .userId(tradeEntity.getUserId())
                .creditAccountEntity(creditAccountEntity)
                .creditOrderEntity(creditOrderEntity)
                .taskEntity(taskEntity)
                .build();

        // 5. save credit trade order
        creditRepository.saveUserCreditTradeOrder(tradeAggregate);
        log.info("adjust account credit end. userId:{} orderId:{}",
                tradeEntity.getUserId(), creditOrderEntity.getOrderId());

        return creditOrderEntity.getOrderId();
    }

    @Override
    public CreditAccountEntity queryUserCreditAccount(String userId) {
        return creditRepository.queryUserCreditAccount(userId);
    }
}
