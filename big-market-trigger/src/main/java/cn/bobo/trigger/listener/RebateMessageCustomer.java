package cn.bobo.trigger.listener;

import cn.bobo.domain.activity.model.entity.SkuRechargeEntity;
import cn.bobo.domain.activity.model.vo.OrderTradeTypeVO;
import cn.bobo.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.bobo.domain.credit.model.entity.TradeEntity;
import cn.bobo.domain.credit.model.vo.TradeNameVO;
import cn.bobo.domain.credit.model.vo.TradeTypeVO;
import cn.bobo.domain.credit.service.ICreditAdjustService;
import cn.bobo.domain.rebate.event.SendRebateMessageEvent;
import cn.bobo.domain.rebate.model.vo.RebateTypeVO;
import cn.bobo.types.enums.ResponseCode;
import cn.bobo.types.event.BaseEvent;
import cn.bobo.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@Component
public class RebateMessageCustomer {

    @Value("${spring.rabbitmq.topic.send_rebate}")
    private String topic;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @Resource
    private ICreditAdjustService creditAdjustService;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_rebate}"))
    public void listener(String message) {
        try {
            log.info("listening to user behavior rebate message. topic: {} message: {}", topic, message);
            // 1. transform message
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage>>() {
            }.getType());
            SendRebateMessageEvent.RebateMessage rebateMessage = eventMessage.getData();

            // 2. credit reward
            switch (rebateMessage.getRebateType()) {
                case "SKU":
                    SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
                    skuRechargeEntity.setUserId(rebateMessage.getUserId());
                    skuRechargeEntity.setSku(Long.valueOf(rebateMessage.getRebateConfig()));
                    skuRechargeEntity.setOutBusinessNo(rebateMessage.getBizId());
                    skuRechargeEntity.setOrderTradeType(OrderTradeTypeVO.REBATE_NO_PAY_TRADE);
                    raffleActivityAccountQuotaService.createOrder(skuRechargeEntity);
                    break;
                case "INTEGRAL":
                    TradeEntity tradeEntity = new TradeEntity();
                    tradeEntity.setUserId(rebateMessage.getUserId());
                    tradeEntity.setTradeName(TradeNameVO.REBATE);
                    tradeEntity.setTradeType(TradeTypeVO.FORWARD);
                    tradeEntity.setAmount(new BigDecimal(rebateMessage.getRebateConfig()));
                    tradeEntity.setOutBusinessNo(rebateMessage.getBizId());
                    creditAdjustService.createOrder(tradeEntity);
                    break;
            }
        } catch (AppException e) {
            if (ResponseCode.INDEX_DUP.getCode().equals(e.getCode())) {
                log.warn("listening to user behavior rebate message, duplicate consumption. topic: {} message: {}", topic, message, e);
                return;
            }
            throw e;
        } catch (Exception e) {
            log.error("listening to user behavior rebate message, consumption failed. topic: {} message: {}", topic, message, e);
            throw e;
        }
    }

}
