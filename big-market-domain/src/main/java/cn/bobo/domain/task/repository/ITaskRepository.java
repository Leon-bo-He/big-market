package cn.bobo.domain.task.repository;

import cn.bobo.domain.task.model.entity.TaskEntity;

import java.util.List;

public interface ITaskRepository {

    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateSendMessageCompleted(String userId, String messageId);

    void updateSendMessageFail(String userId, String messageId);
}
