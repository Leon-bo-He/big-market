package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BO HE
 */
@Data
public class UserCreditAccount {

    private Long id;
    private String userId;
    private BigDecimal totalAmount;
    private BigDecimal availableAmount;
    private String accountStatus;
    private Date createTime;
    private Date updateTime;
}
