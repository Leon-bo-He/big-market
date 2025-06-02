package cn.bobo.domain.activity.service.quota.policy.impl;

import cn.bobo.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.bobo.domain.activity.model.vo.RaffleActivityOrderStateVO;
import cn.bobo.domain.activity.repository.IActivityRepository;
import cn.bobo.domain.activity.service.quota.policy.ITradePolicy;
import org.springframework.stereotype.Service;

/**
 * @author BO HE
 */

@Service("CREDIT_PAY_TRADE")
public class CreditPayTradePolicy implements ITradePolicy {

    private final IActivityRepository activityRepository;

    public CreditPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        createQuotaOrderAggregate.setOrderState(RaffleActivityOrderStateVO.WAIT_PAY);
        activityRepository.doSaveCreditPayOrder(createQuotaOrderAggregate);
    }
}
