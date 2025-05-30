package cn.bobo.domain.rebate.service;

import cn.bobo.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * @author BO HE
 */
public interface IBehaviorRebateService {

    List<String> createOrder(BehaviorEntity behaviorEntity);
}
