package cn.bobo.infrastructure.persistent.po;

import lombok.Data;
import java.util.Date;


/**
 * @author BO HE
 */

@Data
public class RaffleActivityAccountFlow {

    private Integer id;
    private String userId;
    private Long activityId;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
    private String flowId;
    private String flowChannel;
    private String bizId;
    private Date createTime;
    private Date updateTime;


}
