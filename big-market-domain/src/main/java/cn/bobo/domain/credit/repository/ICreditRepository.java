package cn.bobo.domain.credit.repository;

import cn.bobo.domain.credit.model.aggregate.TradeAggregate;

/**
 * @author BO HE
 */
public interface ICreditRepository {
    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);
}
