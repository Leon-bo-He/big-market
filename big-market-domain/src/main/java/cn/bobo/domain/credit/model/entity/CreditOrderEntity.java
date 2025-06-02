package cn.bobo.domain.credit.model.entity;

import cn.bobo.domain.credit.model.vo.TradeNameVO;
import cn.bobo.domain.credit.model.vo.TradeTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditOrderEntity {

    private String userId;
    private String orderId;
    private TradeNameVO tradeName;
    private TradeTypeVO tradeType;
    private BigDecimal tradeAmount;
    private String outBusinessNo;
}
