package cn.bobo.domain.task.repository;

import cn.bobo.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 任务服务仓储接口
 * @create 2024-04-06 10:51
 */
public interface ITaskRepository {

    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateSendMessageCompleted(String userId, String messageId);

    void updateSendMessageFail(String userId, String messageId);
}
