package cn.bobo.trigger.listener;

import cn.bobo.domain.award.event.SendAwardMessageEvent;
import cn.bobo.domain.award.model.entity.DispatchAwardEntity;
import cn.bobo.domain.award.service.IAwardService;
import cn.bobo.types.event.BaseEvent;
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
public class SendAwardCustomer {

    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;

    @Resource
    private IAwardService awardService;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_award}"))
    public void listener(String message) {
        try {
            log.info("Listening to user award sending message, topic: {} message: {}", topic, message);

            BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage>>() {
            }.getType());
            SendAwardMessageEvent.SendAwardMessage sendAwardMessage = eventMessage.getData();

            DispatchAwardEntity dispatchAwardEntity = new DispatchAwardEntity();
            dispatchAwardEntity.setUserId(sendAwardMessage.getUserId());
            dispatchAwardEntity.setOrderId(sendAwardMessage.getOrderId());
            dispatchAwardEntity.setAwardId(sendAwardMessage.getAwardId());
            dispatchAwardEntity.setAwardConfig(sendAwardMessage.getAwardConfig());
            awardService.dispatchAward(dispatchAwardEntity);

        } catch (Exception e) {
            log.error("Listening to user award sending message failed, topic: {} message: {}", topic, message, e);
            throw e;
        }
    }

}
