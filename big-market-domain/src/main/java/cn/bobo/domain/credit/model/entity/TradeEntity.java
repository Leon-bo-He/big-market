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
public class TradeEntity {

    private String userId;
    private TradeNameVO tradeName;
    private TradeTypeVO tradeType;
    private BigDecimal amount;
    private String outBusinessNo;
}
