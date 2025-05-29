package cn.bobo.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */

@Getter
@AllArgsConstructor
public enum RaffleActivityOrderStateVO {

    COMPLETED("COMPLETED", "completed order"),;

    private final String code;
    private final String desc;
}
