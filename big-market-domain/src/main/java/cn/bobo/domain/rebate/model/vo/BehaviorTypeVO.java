package cn.bobo.domain.rebate.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BehaviorTypeVO {

    CHECK_IN("CHECK_IN", "user daily check-in"),
    PAYMENT("PAYMENT", "external payment"),
    ;

    private final String code;
    private final String info;

}
