package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class Task {

    private String id;
    private String userId;
    private String topic;
    private String messageId;
    private String message;
    private String state;
    private Date createTime;
    private Date updateTime;

}
