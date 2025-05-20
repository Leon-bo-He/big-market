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
    STRATEGY_RULE_WEIGHT_IS_NULL("ERR_BIZ_001", "Service error, rule_weight is active but not exist"),
    UN_ASSEMBLED_STRATEGY_ARMORY("ERR_BIZ_002", "Service error, strategy armory is not assembled, using IStrategyArmory.assembleLotteryStrategy() to assemble"),
    ;

    private String code;
    private String info;

}
