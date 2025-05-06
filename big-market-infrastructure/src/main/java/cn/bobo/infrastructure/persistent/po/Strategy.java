package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Bo He
 * @Description: Sweepstakes strategy
 * @Date: 6/24/24
 */

@Data
public class Strategy {
    private Long id;
    private Long strategyId;
    private String strategyDesc;
    private String ruleModels;
    private Date createTime;
    private Date updateTime;
}
