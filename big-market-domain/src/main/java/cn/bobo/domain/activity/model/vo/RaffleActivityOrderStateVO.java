package cn.bobo.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */

@Getter
@AllArgsConstructor
public enum RaffleActivityOrderStateVO {

    WAIT_PAY("WAIT_PAY", "waiting for payment"),
    COMPLETED("COMPLETED", "completed order"),
    ;

    private final String code;
    private final String desc;
}
