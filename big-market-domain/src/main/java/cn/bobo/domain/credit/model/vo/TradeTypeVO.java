package cn.bobo.domain.credit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TradeTypeVO {

    FORWARD("FORWARD", "forward trade, + credit to user account"),
    REVERSE("REVERSE", "reverse trade, - credit from user account")
    ;

    private final String code;
    private final String info;

}
