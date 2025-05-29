package cn.bobo.domain.task.model.entity;

import lombok.Data;

/**
 * @author BO HE
 */
@Data
public class TaskEntity {

    private String userId;
    private String topic;
    private String messageId;
    private String message;

}
