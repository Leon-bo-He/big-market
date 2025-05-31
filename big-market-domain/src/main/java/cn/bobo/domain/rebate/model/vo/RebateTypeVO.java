package cn.bobo.domain.rebate.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RebateTypeVO {

    SKU("SKU", "activity stock recharge product"),
    INTEGRAL("INTEGRAL", "user activity points"),
    ;

    private final String code;
    private final String info;

}
