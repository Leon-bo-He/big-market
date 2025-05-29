package cn.bobo.domain.award.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AwardStateVO {

    CREATE("CREATE", "create award"),
    COMPLETED("COMPLETED", "send complete"),
    FAIL("FAIL", "send fail"),
    ;

    private final String code;
    private final String desc;

}
