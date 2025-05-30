package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

@Data
public class DailyBehaviorRebate {

    private Long id;
    private String behaviorType;
    private String rebateDesc;
    private String rebateType;
    private String rebateConfig;
    private String state;
    private Date createTime;
    private Date updateTime;

}
