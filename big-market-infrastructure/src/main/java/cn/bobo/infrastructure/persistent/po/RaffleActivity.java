package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RaffleActivity {

    private Long id;
    private Long activityId;
    private String activityName;
    private String activityDesc;
    private Date beginDateTime;
    private Date endDateTime;
    private Long strategyId;
    private String state;
    private Date createTime;
    private Date updateTime;

}
