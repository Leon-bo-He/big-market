package cn.bobo.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BO HE
 */

@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    CREATE("create", "create activity"),
    OPEN("open", "open activity"),
    CLOSE("close", "close activity"),
    ;

    private final String code;
    private final String desc;
}
