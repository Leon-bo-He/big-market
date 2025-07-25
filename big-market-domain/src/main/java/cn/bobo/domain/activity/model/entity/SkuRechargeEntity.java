package cn.bobo.domain.activity.model.entity;

import cn.bobo.domain.activity.model.vo.OrderTradeTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuRechargeEntity {

    private String userId;
    private Long sku;
    private String outBusinessNo;
    private OrderTradeTypeVO orderTradeType = OrderTradeTypeVO.REBATE_NO_PAY_TRADE;
}
