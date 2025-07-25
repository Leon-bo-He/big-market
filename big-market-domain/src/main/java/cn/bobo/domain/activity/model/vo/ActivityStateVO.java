package cn.bobo.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */

@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    CREATE("CREATE", "create activity"),
    OPEN("OPEN", "activity opened"),
    CLOSE("CLOSE", "activity closed"),
    ;

    private final String code;
    private final String desc;
}
