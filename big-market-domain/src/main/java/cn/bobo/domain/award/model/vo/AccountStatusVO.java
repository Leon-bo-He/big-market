package cn.bobo.domain.award.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatusVO {

    OPEN("OPEN", "account open"),
    CLOSE("CLOSE", "account close"),
    ;

    private final String code;
    private final String desc;

}
