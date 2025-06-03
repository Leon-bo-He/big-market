package cn.bobo.domain.activity.service;

import cn.bobo.domain.activity.model.entity.SkuProductEntity;

import java.util.List;

/**
 * @author BO HE
 */
public interface IRaffleActivitySkuProductService {
    List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId);
}
