package cn.bobo.domain.credit.service;

import cn.bobo.domain.credit.model.entity.CreditAccountEntity;
import cn.bobo.domain.credit.model.entity.TradeEntity;

/**
 * @author BO HE
 */
public interface ICreditAdjustService {

    String createOrder(TradeEntity tradeEntity);

    CreditAccountEntity queryUserCreditAccount(String userId);
}
