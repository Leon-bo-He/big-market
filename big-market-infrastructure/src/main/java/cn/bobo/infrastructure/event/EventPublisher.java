package cn.bobo.infrastructure.event;

import cn.bobo.types.event.BaseEvent;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String topic, BaseEvent.EventMessage<?> eventMessage) {
        try {
            String messageJson = JSON.toJSONString(eventMessage);
            rabbitTemplate.convertAndSend(topic, messageJson);
            log.info("sending MQ message. topic:{} message:{}", topic, messageJson);
        } catch (Exception e) {
            log.error("sending MQ message failed. topic:{} message:{}", topic, JSON.toJSONString(eventMessage), e);
            throw e;
        }
    }

    public void publish(String topic, String eventMessageJSON){
        try {
            rabbitTemplate.convertAndSend(topic, eventMessageJSON);
            log.info("sending MQ message. topic:{} message:{}", topic, eventMessageJSON);
        } catch (Exception e) {
            log.error("sending MQ message failed. topic:{} message:{}", topic, JSON.toJSONString(eventMessageJSON), e);
            throw e;
        }
    }

}
