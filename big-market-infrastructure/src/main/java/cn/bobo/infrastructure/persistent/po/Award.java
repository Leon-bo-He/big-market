package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Bo He
 * @Description: Award form
 * @Date: 6/25/24
 */

@Data
public class Award {
    private Long id;
    private Long awardId;
    private String awardKey;
    private String awardConfig;
    private String awardDesc;
    private Date createTime;
    private Date updateTime;

}
