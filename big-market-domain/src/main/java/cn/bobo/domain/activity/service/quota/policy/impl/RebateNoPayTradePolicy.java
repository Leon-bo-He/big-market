package cn.bobo.domain.activity.service.quota.policy.impl;

import cn.bobo.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.bobo.domain.activity.model.vo.RaffleActivityOrderStateVO;
import cn.bobo.domain.activity.repository.IActivityRepository;
import cn.bobo.domain.activity.service.quota.policy.ITradePolicy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author BO HE
 */

@Service("REBATE_NO_PAY_TRADE")
public class RebateNoPayTradePolicy implements ITradePolicy {

    private final IActivityRepository activityRepository;

    public RebateNoPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        // change order state to 'COMPLETED' and set pay amount to zero if no payment is required
        createQuotaOrderAggregate.setOrderState(RaffleActivityOrderStateVO.COMPLETED);
        createQuotaOrderAggregate.getActivityOrderEntity().setPayAmount(BigDecimal.ZERO);
        activityRepository.doSaveNoPayOrder(createQuotaOrderAggregate);
    }
}
