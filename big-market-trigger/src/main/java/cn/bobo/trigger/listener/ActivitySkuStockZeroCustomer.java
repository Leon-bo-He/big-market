package cn.bobo.trigger.listener;

import cn.bobo.domain.activity.service.IRaffleActivitySkuStockService;
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
public class ActivitySkuStockZeroCustomer {

    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Resource
    private IRaffleActivitySkuStockService skuStock;

    @RabbitListener(queuesToDeclare = @Queue(value = "activity_sku_stock_zero"))
    public void listener(String message) {
        try {
            log.info("Listening to the message of activity sku stock consumption being 0. topic: {} message: {}", topic, message);
            // Convert object
            BaseEvent.EventMessage<Long> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<Long>>() {
            }.getType());
            Long sku = eventMessage.getData();
            // update stock
            skuStock.clearActivitySkuStock(sku);
            // Clear queue 「At this time, there is no need to delay updating the database record」
            skuStock.clearQueueValue();
        } catch (Exception e) {
            log.error("Listening to the message of activity sku stock consumption being 0 failed. topic: {} message: {}", topic, message);
            throw e;
        }
    }

}
