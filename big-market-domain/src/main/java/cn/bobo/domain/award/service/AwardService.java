package cn.bobo.domain.award.service;

import cn.bobo.domain.award.event.SendAwardMessageEvent;
import cn.bobo.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.bobo.domain.award.model.entity.DispatchAwardEntity;
import cn.bobo.domain.award.model.entity.TaskEntity;
import cn.bobo.domain.award.model.entity.UserAwardRecordEntity;
import cn.bobo.domain.award.model.vo.TaskStateVO;
import cn.bobo.domain.award.repository.IAwardRepository;
import cn.bobo.domain.award.service.dispatch.IDispatchAward;
import cn.bobo.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author BO HE
 */

@Slf4j
@Service
public class AwardService implements IAwardService {

    private final IAwardRepository awardRepository;
    private final SendAwardMessageEvent sendAwardMessageEvent;
    private final Map<String, IDispatchAward> dispatchAwardMap;

    public AwardService(IAwardRepository awardRepository, SendAwardMessageEvent sendAwardMessageEvent, Map<String, IDispatchAward> dispatchAwardMap) {
        this.awardRepository = awardRepository;
        this.sendAwardMessageEvent = sendAwardMessageEvent;
        this.dispatchAwardMap = dispatchAwardMap;
    }

    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        // build message object
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = new SendAwardMessageEvent.SendAwardMessage();
        sendAwardMessage.setOrderId(userAwardRecordEntity.getOrderId());
        sendAwardMessage.setUserId(userAwardRecordEntity.getUserId());
        sendAwardMessage.setAwardId(userAwardRecordEntity.getAwardId());
        sendAwardMessage.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        sendAwardMessage.setAwardConfig(userAwardRecordEntity.getAwardConfig());

        BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> sendAwardMessageEventMessage = sendAwardMessageEvent.buildEventMessage(sendAwardMessage);

        // build task object
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(userAwardRecordEntity.getUserId());
        taskEntity.setTopic(sendAwardMessageEvent.topic());
        taskEntity.setMessageId(sendAwardMessageEventMessage.getId());
        taskEntity.setMessage(sendAwardMessageEventMessage);
        taskEntity.setState(TaskStateVO.CREATE);

        // build aggregate object
        UserAwardRecordAggregate userAwardRecordAggregate = UserAwardRecordAggregate.builder()
                .taskEntity(taskEntity)
                .userAwardRecordEntity(userAwardRecordEntity)
                .build();

        // store aggregate object - user award record under one transaction
        awardRepository.saveUserAwardRecord(userAwardRecordAggregate);
    }

    @Override
    public void dispatchAward(DispatchAwardEntity dispatchAwardEntity) {
        String awardKey = awardRepository.queryAwardKey(dispatchAwardEntity.getAwardId());
        if (null == awardKey) {
            log.error("dispatch award, the award key does not exist. awardId:{}", dispatchAwardEntity.getAwardId());
            return;
        }

        IDispatchAward dispatchAward = dispatchAwardMap.get(awardKey);

        if (null == dispatchAward) {
            log.error("dispatch award, the corresponding service does not exist. awardKey:{}", awardKey);
            //TODO: implement all award services handling before throwing an exception
//            throw new RuntimeException("dispatch award, the service for award " + awardKey + " does not exist");
            return;
        }

        dispatchAward.giveOutPrizes(dispatchAwardEntity);
    }
}
