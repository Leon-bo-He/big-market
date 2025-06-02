package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BO HE
 */
@Data
public class UserCreditOrder {

    private Long id;
    private String userId;
    private String orderId;
    private String tradeName;
    private String tradeType;
    private BigDecimal tradeAmount;
    private String outBusinessNo;
    private Date createTime;
    private Date updateTime;
}
