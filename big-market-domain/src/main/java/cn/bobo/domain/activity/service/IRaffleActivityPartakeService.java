package cn.bobo.domain.activity.service;

import cn.bobo.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.bobo.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * @author BO HE
 */
public interface IRaffleActivityPartakeService {

    /**
     * Creates an order for the raffle activity.
     * If there are any unused quota, it will be used first.
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);


    UserRaffleOrderEntity createOrder(String userId, Long activityId);


}
