package cn.bobo.domain.activity.service.rule.impl;

import cn.bobo.domain.activity.model.entity.ActivityCountEntity;
import cn.bobo.domain.activity.model.entity.ActivityEntity;
import cn.bobo.domain.activity.model.entity.ActivitySkuEntity;
import cn.bobo.domain.activity.model.vo.ActivitySkuStockKeyVO;
import cn.bobo.domain.activity.repository.IActivityRepository;
import cn.bobo.domain.activity.service.armory.IActivityDispatch;
import cn.bobo.domain.activity.service.rule.AbstractActionChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("activity_sku_stock_action")
public class ActivitySkuStockActionChain extends AbstractActionChain {

    @Resource
    private IActivityDispatch activityDispatch;
    @Resource
    private IActivityRepository activityRepository;


    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        log.info("activity responsibility chain - sku stock processing [verification & deduction] start. sku:{} activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());

        // Deduct stock
        boolean status = activityDispatch.subtractionActivitySkuStock(activitySkuEntity.getSku(), activityEntity.getEndDateTime());
        // true; Stock deduction successful
        if (status) {
            log.info("activity responsibility chain - sku stock processing [verification & deduction] successful. sku:{} activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());

            // Write to delay queue, delay consumption update stock record
            activityRepository.activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO.builder()
                    .sku(activitySkuEntity.getSku())
                    .activityId(activityEntity.getActivityId())
                    .build());

            return true;
        }

        return true;
    }

}
