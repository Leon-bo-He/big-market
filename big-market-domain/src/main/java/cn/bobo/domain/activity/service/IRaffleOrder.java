package cn.bobo.domain.activity.service;

import cn.bobo.domain.activity.model.entity.ActivityOrderEntity;
import cn.bobo.domain.activity.model.entity.ActivityShopCartEntity;

/**
 * @author BO HE
 */
public interface IRaffleOrder {

    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);
}
