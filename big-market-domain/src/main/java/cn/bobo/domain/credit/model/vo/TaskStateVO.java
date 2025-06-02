package cn.bobo.domain.credit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStateVO {

    CREATE("CREATE", "create task"),
    COMPLETED("COMPLETED", "send complete"),
    FAIL("FAIL", "send fail"),
    ;

    private final String code;
    private final String desc;

}
