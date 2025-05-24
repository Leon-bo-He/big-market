package cn.bobo.infrastructure.persistent.po;

import lombok.Data;
import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RaffleActivityOrder {

    private Long id;
    private String userId;
    private Long sku;
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private String orderId;
    private Date orderTime;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
    private String state;
    private String outBusinessNo;
    private Date createTime;
    private Date updateTime;

}
