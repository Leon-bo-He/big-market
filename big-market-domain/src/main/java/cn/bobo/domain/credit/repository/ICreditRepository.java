package cn.bobo.domain.credit.repository;

import cn.bobo.domain.credit.model.aggregate.TradeAggregate;
import cn.bobo.domain.credit.model.entity.CreditAccountEntity;

/**
 * @author BO HE
 */
public interface ICreditRepository {
    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);

    CreditAccountEntity queryUserCreditAccount(String userId);
}
