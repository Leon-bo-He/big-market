package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */
@Data
public class RaffleActivityAccount {

    private Long id;
    private String userId;
    private Long activityId;
    private Integer totalCount;
    private Integer totalCountSurplus;
    private Integer dayCount;
    private Integer dayCountSurplus;
    private Integer monthCount;
    private Integer monthCountSurplus;
    private Date createTime;
    private Date updateTime;

}
