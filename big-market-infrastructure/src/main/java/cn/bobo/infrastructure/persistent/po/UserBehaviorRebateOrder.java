package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;


@Data
public class UserBehaviorRebateOrder {

    private Long id;
    private String userId;
    private String orderId;
    private String behaviorType;
    private String rebateDesc;
    private String rebateType;
    private String rebateConfig;
    private String outBusinessNo;
    private String bizId;
    private Date createTime;
    private Date updateTime;

}
