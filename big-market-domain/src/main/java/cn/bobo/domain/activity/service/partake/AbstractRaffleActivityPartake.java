package cn.bobo.domain.activity.service.partake;

import cn.bobo.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.bobo.domain.activity.model.entity.ActivityEntity;
import cn.bobo.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.bobo.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.bobo.domain.activity.model.vo.ActivityStateVO;
import cn.bobo.domain.activity.repository.IActivityRepository;
import cn.bobo.domain.activity.service.IRaffleActivityPartakeService;
import cn.bobo.types.enums.ResponseCode;
import cn.bobo.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author BO HE
 */
@Slf4j
public abstract class AbstractRaffleActivityPartake implements IRaffleActivityPartakeService {

    protected final IActivityRepository activityRepository;

    public AbstractRaffleActivityPartake(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        // 0. basic information
        String userId = partakeRaffleActivityEntity.getUserId();
        Long activityId = partakeRaffleActivityEntity.getActivityId();
        Date currentDate = new Date();

        // 1. Query activity
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);

        // verify; activity status
        if (!ActivityStateVO.OPEN.equals(activityEntity.getState())) {
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        // verify; activity date [start time <- current time -> end time]
        if (activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)) {
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }

        // 2. Query unused activity participation order records
        UserRaffleOrderEntity userRaffleOrderEntity = activityRepository.queryNoUsedRaffleOrder(partakeRaffleActivityEntity);
        if (null != userRaffleOrderEntity) {
            log.info("Create activity participation order records. userId:{} activityId:{} userRaffleOrderEntity:{}", userId, activityId, JSON.toJSONString(userRaffleOrderEntity));
            return userRaffleOrderEntity;
        }

        // 3. Account filtering & return account construction object
        CreatePartakeOrderAggregate createPartakeOrderAggregate = this.doFilterAccount(userId, activityId, currentDate);

        // 4. Build order
        UserRaffleOrderEntity userRaffleOrder = this.buildUserRaffleOrder(userId, activityId, currentDate);

        // 5. Fill in the raffle order entity object
        createPartakeOrderAggregate.setUserRaffleOrderEntity(userRaffleOrder);

        // 6. Save the participation order aggregate
        activityRepository.saveCreatePartakeOrderAggregate(createPartakeOrderAggregate);

        // 7. return order info
        return userRaffleOrder;
    }

    protected abstract UserRaffleOrderEntity buildUserRaffleOrder(String userId, Long activityId, Date currentDate);

    protected abstract CreatePartakeOrderAggregate doFilterAccount(String userId, Long activityId, Date currentDate);

}