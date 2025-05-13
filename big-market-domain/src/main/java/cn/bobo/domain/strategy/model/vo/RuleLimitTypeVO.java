package cn.bobo.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */

@Getter
@AllArgsConstructor
public enum RuleLimitTypeVO {

    EQUAL(1, "equal"),
    GT(2, "greater than"),
    LT(3, "less than"),
    GE(4, "greater than or equal"),
    LE(5, "less than or equal"),
    ENUM(6, "enum"),
    ;

    private final Integer code;
    private final String info;
}
