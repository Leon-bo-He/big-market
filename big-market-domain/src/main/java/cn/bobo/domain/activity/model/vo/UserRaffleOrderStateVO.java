package cn.bobo.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */
@Getter
@AllArgsConstructor
public enum UserRaffleOrderStateVO {

    CREATE("CREATE", "created order"),
    USED("USED", "used order"),
    CANCEL("CANCEL", "cancelled order"),
    ;

    private final String code;
    private final String desc;

}
