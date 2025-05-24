package cn.bobo.domain.activity.service.rule.impl;

import cn.bobo.domain.activity.model.entity.ActivityCountEntity;
import cn.bobo.domain.activity.model.entity.ActivityEntity;
import cn.bobo.domain.activity.model.entity.ActivitySkuEntity;
import cn.bobo.domain.activity.service.rule.AbstractActionChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component("activity_base_action")
public class ActivityBaseActionChain extends AbstractActionChain {

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        log.info("activity responsibility chain - basic information [validity period, status] verification start.");

        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }

}
