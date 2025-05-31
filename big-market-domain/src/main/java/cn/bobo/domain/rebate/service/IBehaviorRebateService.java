package cn.bobo.domain.rebate.service;

import cn.bobo.domain.rebate.model.entity.BehaviorEntity;
import cn.bobo.domain.rebate.model.entity.BehaviorRebateOrderEntity;

import java.util.List;

/**
 * @author BO HE
 */
public interface IBehaviorRebateService {

    List<String> createOrder(BehaviorEntity behaviorEntity);

    List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo);
}
