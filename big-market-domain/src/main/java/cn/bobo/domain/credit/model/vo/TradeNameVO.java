package cn.bobo.domain.credit.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TradeNameVO {

    REBATE("REBATE"),
    CONVERT_SKU("CONVERT_SKU"),
    ;

    private final String name;

}
