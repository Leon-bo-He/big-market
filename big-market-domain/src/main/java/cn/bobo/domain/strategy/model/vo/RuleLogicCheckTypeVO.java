package cn.bobo.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */

@Getter
@AllArgsConstructor
public enum RuleLogicCheckTypeVO {

    ALLOW("0000", "Allow; Execute follow-up logic, not affected by the rule"),
    TAKE_OVER("0001", "Take Over; The rule result will affect the follow-up logic"),
    ;

    private final String code;
    private final String info;
}
