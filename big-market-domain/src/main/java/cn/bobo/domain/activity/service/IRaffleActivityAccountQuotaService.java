package cn.bobo.domain.activity.service;

import cn.bobo.domain.activity.model.entity.*;

/**
 * @author BO HE
 */
public interface IRaffleActivityAccountQuotaService {

    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);

    /**
     * Create an account recharge order, add raffle amount to the user account
     *
     * Create a recharge order under behaviors [Check-in, Share, redeem points, etc.], add the corresponding raffle amount to the user account
     *
     * @param skuRechargeEntity sku recharge entity
     * @return activity id
     */
    String createOrder(SkuRechargeEntity skuRechargeEntity);

    void updateOrder(DeliveryOrderEntity deliveryOrderEntity);

    Integer queryRaffleActivityAccountPartakeCount(Long activityId, String userId);

    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);

    ActivityAccountEntity queryActivityAccountEntity(Long activityId, String userId);

}
