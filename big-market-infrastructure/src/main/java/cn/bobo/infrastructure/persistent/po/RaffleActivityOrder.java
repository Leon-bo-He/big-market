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
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private String orderId;
    private Date orderTime;
    private String state;
    private Date createTime;
    private Date updateTime;

}
