package cn.bobo.infrastructure.persistent.repository;

import cn.bobo.domain.credit.model.aggregate.TradeAggregate;
import cn.bobo.domain.credit.model.entity.CreditAccountEntity;
import cn.bobo.domain.credit.model.entity.CreditOrderEntity;
import cn.bobo.domain.credit.model.entity.TaskEntity;
import cn.bobo.domain.credit.repository.ICreditRepository;
import cn.bobo.infrastructure.event.EventPublisher;
import cn.bobo.infrastructure.persistent.dao.ITaskDao;
import cn.bobo.infrastructure.persistent.dao.IUserCreditAccountDao;
import cn.bobo.infrastructure.persistent.dao.IUserCreditOrderDao;
import cn.bobo.infrastructure.persistent.po.Task;
import cn.bobo.infrastructure.persistent.po.UserCreditAccount;
import cn.bobo.infrastructure.persistent.po.UserCreditOrder;
import cn.bobo.infrastructure.persistent.redis.IRedisService;
import cn.bobo.types.common.Constants;
import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author BO HE
 */

@Slf4j
@Repository
public class CreditRepository implements ICreditRepository {

    @Resource
    private IRedisService redisService;
    @Resource
    private IUserCreditAccountDao userCreditAccountDao;
    @Resource
    private IUserCreditOrderDao userCreditOrderDao;
    @Resource
    private ITaskDao taskDao;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private EventPublisher eventPublisher;

    @Override
    public void saveUserCreditTradeOrder(TradeAggregate tradeAggregate) {
        String userId = tradeAggregate.getUserId();
        CreditAccountEntity creditAccountEntity = tradeAggregate.getCreditAccountEntity();
        CreditOrderEntity creditOrderEntity = tradeAggregate.getCreditOrderEntity();
        TaskEntity taskEntity = tradeAggregate.getTaskEntity();

        // credit account
        UserCreditAccount userCreditAccountReq = new UserCreditAccount();
        userCreditAccountReq.setUserId(userId);
        userCreditAccountReq.setTotalAmount(creditAccountEntity.getAdjustAmount());
        userCreditAccountReq.setAvailableAmount(creditAccountEntity.getAdjustAmount());

        // credit order
        UserCreditOrder userCreditOrderReq = new UserCreditOrder();
        userCreditOrderReq.setUserId(creditOrderEntity.getUserId());
        userCreditOrderReq.setOrderId(creditOrderEntity.getOrderId());
        userCreditOrderReq.setTradeName(creditOrderEntity.getTradeName().getName());
        userCreditOrderReq.setTradeType(creditOrderEntity.getTradeType().getCode());
        userCreditOrderReq.setTradeAmount(creditOrderEntity.getTradeAmount());
        userCreditOrderReq.setOutBusinessNo(creditOrderEntity.getOutBusinessNo());

        Task task = new Task();
        task.setUserId(taskEntity.getUserId());
        task.setTopic(taskEntity.getTopic());
        task.setMessageId(taskEntity.getMessageId());
        task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
        task.setState(taskEntity.getState().getCode());

        RLock lock = redisService.getLock(Constants.RedisKey.USER_CREDIT_ACCOUNT_LOCK + userId + Constants.UNDERLINE + creditOrderEntity.getOutBusinessNo());
        try {
            lock.lock(3, TimeUnit.SECONDS);
            dbRouter.doRouter(userId);

            transactionTemplate.execute(status -> {
                try {
                    // 1. save or update user credit account
                    UserCreditAccount userCreditAccount = userCreditAccountDao.queryUserCreditAccount(userCreditAccountReq);
                    if (null == userCreditAccount) {
                        userCreditAccountDao.insert(userCreditAccountReq);
                    } else {
                        userCreditAccountDao.updateAddAmount(userCreditAccountReq);
                    }
                    // 2. save user credit order
                    userCreditOrderDao.insert(userCreditOrderReq);
                    // 3. save task for message compensation
                    taskDao.insert(task);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("adjust account credit failed, unique index conflict userId:{} orderId:{}", userId, creditOrderEntity.getOrderId(), e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    log.error("adjust account credit failed. userId:{} orderId:{}", userId, creditOrderEntity.getOrderId(), e);
                }
                return 1;
            });
        } finally {
            dbRouter.clear();
            lock.unlock();
        }

        try {
            eventPublisher.publish(task.getTopic(), task.getMessage());
            // update db task state
            taskDao.updateTaskSendMessageCompleted(task);
            log.info("adjust account credit record sent to MQ successfully. userId: {} orderId:{} topic: {}", userId, creditOrderEntity.getOrderId(), task.getTopic());
        } catch (Exception e) {
            log.error("adjust account credit record send MQ failed. userId: {} orderId:{} topic: {}", userId, creditOrderEntity.getOrderId(), task.getTopic(), e);
            taskDao.updateTaskSendMessageFail(task);
        }

    }
}
