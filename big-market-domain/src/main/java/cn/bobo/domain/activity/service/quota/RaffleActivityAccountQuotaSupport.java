package cn.bobo.domain.activity.service.quota;

import cn.bobo.domain.activity.model.entity.ActivityCountEntity;
import cn.bobo.domain.activity.model.entity.ActivityEntity;
import cn.bobo.domain.activity.model.entity.ActivitySkuEntity;
import cn.bobo.domain.activity.repository.IActivityRepository;
import cn.bobo.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;

/**
 * @author BO HE
 */
public class RaffleActivityAccountQuotaSupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivityAccountQuotaSupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }

    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }

}
