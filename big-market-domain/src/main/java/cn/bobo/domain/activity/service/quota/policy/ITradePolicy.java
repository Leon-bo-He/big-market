package cn.bobo.domain.activity.service.quota.policy;

import cn.bobo.domain.activity.model.aggregate.CreateQuotaOrderAggregate;

/**
 * @author BO HE
 */
public interface ITradePolicy {

    void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate);
}
