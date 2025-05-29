package cn.bobo.infrastructure.persistent.repository;

import cn.bobo.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.bobo.domain.award.model.entity.TaskEntity;
import cn.bobo.domain.award.model.entity.UserAwardRecordEntity;
import cn.bobo.domain.award.repository.IAwardRepository;
import cn.bobo.infrastructure.event.EventPublisher;
import cn.bobo.infrastructure.persistent.dao.ITaskDao;
import cn.bobo.infrastructure.persistent.dao.IUserAwardRecordDao;
import cn.bobo.infrastructure.persistent.po.Task;
import cn.bobo.infrastructure.persistent.po.UserAwardRecord;
import cn.bobo.types.enums.ResponseCode;
import cn.bobo.types.exception.AppException;
import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@Repository
public class AwardRepository implements IAwardRepository {

    @Resource
    private ITaskDao taskDao;
    @Resource
    private IUserAwardRecordDao userAwardRecordDao;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private EventPublisher eventPublisher;

    @Override
    public void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate) {

        UserAwardRecordEntity userAwardRecordEntity = userAwardRecordAggregate.getUserAwardRecordEntity();
        TaskEntity taskEntity = userAwardRecordAggregate.getTaskEntity();
        String userId = userAwardRecordEntity.getUserId();
        Long activityId = userAwardRecordEntity.getActivityId();
        Integer awardId = userAwardRecordEntity.getAwardId();

        UserAwardRecord userAwardRecord = new UserAwardRecord();
        userAwardRecord.setUserId(userAwardRecordEntity.getUserId());
        userAwardRecord.setActivityId(userAwardRecordEntity.getActivityId());
        userAwardRecord.setStrategyId(userAwardRecordEntity.getStrategyId());
        userAwardRecord.setOrderId(userAwardRecordEntity.getOrderId());
        userAwardRecord.setAwardId(userAwardRecordEntity.getAwardId());
        userAwardRecord.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        userAwardRecord.setAwardTime(userAwardRecordEntity.getAwardTime());
        userAwardRecord.setAwardState(userAwardRecordEntity.getAwardState().getCode());

        Task task = new Task();
        task.setUserId(taskEntity.getUserId());
        task.setTopic(taskEntity.getTopic());
        task.setMessageId(taskEntity.getMessageId());
        task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
        task.setState(taskEntity.getState().getCode());

        try {
            dbRouter.doRouter(userId);
            transactionTemplate.execute(status -> {
                try {
                    // write user award record
                    userAwardRecordDao.insert(userAwardRecord);
                    // write task record
                    taskDao.insert(task);
                    return 1;
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("write user award record failed, duplicate key userId: {}, activityId: {}, awardId: {}", userId, activityId, awardId, e);
                    throw new AppException(ResponseCode.INDEX_DUP.getCode(), e);
                }
            });
        } finally {
            dbRouter.clear();
        }

        try {
            // send MQ message
            eventPublisher.publish(task.getTopic(), task.getMessage());
            // update task state to completed
            taskDao.updateTaskSendMessageCompleted(task);
        } catch (Exception e) {
            log.error("write user award record failed, send MQ message failed, userId: {}, activityId: {}, awardId: {}", userId, activityId, awardId, e);
            taskDao.updateTaskSendMessageFail(task);
        }
    }
}
