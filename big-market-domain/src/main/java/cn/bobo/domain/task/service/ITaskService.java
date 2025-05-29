package cn.bobo.domain.task.service;

import cn.bobo.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author BO HE
 */
public interface ITaskService {

    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);

}
