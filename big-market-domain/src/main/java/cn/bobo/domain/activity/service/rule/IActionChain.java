package cn.bobo.domain.activity.service.rule;

import cn.bobo.domain.activity.model.entity.ActivityCountEntity;
import cn.bobo.domain.activity.model.entity.ActivityEntity;
import cn.bobo.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author BO HE
 */
public interface IActionChain extends IActionChainArmory {

    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
