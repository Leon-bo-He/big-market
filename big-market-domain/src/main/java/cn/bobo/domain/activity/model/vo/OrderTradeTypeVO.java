package cn.bobo.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTradeTypeVO {

    CREDIT_PAY_TRADE("CREDIT_PAY_TRADE","credit redemption, requires payment type transaction"),
    REBATE_NO_PAY_TRADE("REBATE_NO_PAY_TRADE", "rebate prize, does not require payment type transaction")
    ;

    private final String code;
    private final String desc;

}
