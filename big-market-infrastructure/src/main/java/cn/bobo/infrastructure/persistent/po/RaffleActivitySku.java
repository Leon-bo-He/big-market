package cn.bobo.infrastructure.persistent.po;

import lombok.Data;
import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RaffleActivitySku {

    private Long id;
    private Long sku;
    private Long activityId;
    private Long activityCountId;
    private Integer stockCount;
    private Integer stockCountSurplus;
    private Date createTime;
    private Date updateTime;



}
