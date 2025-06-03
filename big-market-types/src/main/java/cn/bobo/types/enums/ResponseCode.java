package cn.bobo.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "Success"),
    UN_ERROR("0001", "Unknown error"),
    ILLEGAL_PARAMETER("0002", "Illegal parameter"),
    INDEX_DUP("0003", "Unique index conflict"),
    STRATEGY_RULE_WEIGHT_IS_NULL("ERR_BIZ_001", "Service error, rule_weight is active but not exist"),
    UN_ASSEMBLED_STRATEGY_ARMORY("ERR_BIZ_002", "Service error, strategy armory is not assembled, using IStrategyArmory.assembleLotteryStrategy() to assemble"),
    ACTIVITY_STATE_ERROR("ERR_BIZ_003", "activity not open (not in open state)"),
    ACTIVITY_DATE_ERROR("ERR_BIZ_004", "not in activity date range"),
    ACTIVITY_SKU_STOCK_ERROR("ERR_BIZ_005", "activity inventory not enough"),
    ACCOUNT_QUOTA_ERROR("ERR_BIZ_006","account total quota insufficient"),
    ACCOUNT_MONTH_QUOTA_ERROR("ERR_BIZ_007","account month quota insufficient"),
    ACCOUNT_DAY_QUOTA_ERROR("ERR_BIZ_008","account day quota insufficient"),
    ACTIVITY_ORDER_ERROR("ERR_BIZ_009", "user raffle order has been used, cannot raffle again"),
    USER_CREDIT_ACCOUNT_NO_AVAILABLE_AMOUNT("ERR_CREDIT_001", "User credit account no available amount")
    ;

    private String code;
    private String info;

}
