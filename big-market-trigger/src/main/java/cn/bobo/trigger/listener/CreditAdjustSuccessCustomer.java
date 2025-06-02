package cn.bobo.trigger.listener;

import cn.bobo.domain.activity.model.entity.DeliveryOrderEntity;
import cn.bobo.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.bobo.domain.credit.event.CreditAdjustSuccessMessageEvent;
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

@Slf4j
@Component
public class CreditAdjustSuccessCustomer {

    @Value("${spring.rabbitmq.topic.credit_adjust_success}")
    private String topic;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.credit_adjust_success}"))
    public void listener(String message) {
        try {
            log.info("listener credit adjust success message. process delivery order. topic: {} message: {}", topic, message);
            BaseEvent.EventMessage<CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage>>() {
            }.getType());
            CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage creditAdjustSuccessMessage = eventMessage.getData();

            // Process delivery order of use credit
            DeliveryOrderEntity deliveryOrderEntity = new DeliveryOrderEntity();
            deliveryOrderEntity.setUserId(creditAdjustSuccessMessage.getUserId());
            deliveryOrderEntity.setOutBusinessNo(creditAdjustSuccessMessage.getOutBusinessNo());
            raffleActivityAccountQuotaService.updateOrder(deliveryOrderEntity);
        } catch (AppException e) {
            if (ResponseCode.INDEX_DUP.getCode().equals(e.getCode())) {
                log.warn("listener credit adjust success message, process delivery order, duplicate consumption. topic: {} message: {}", topic, message, e);
                return;
            }
            throw e;
        } catch (Exception e) {
            log.error("listener credit adjust success message, process delivery order failed. topic: {} message: {}", topic, message, e);
            throw e;
        }
    }

}
