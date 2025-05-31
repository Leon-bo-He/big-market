package cn.bobo.trigger.api;

import cn.bobo.trigger.api.dto.ActivityDrawRequestDTO;
import cn.bobo.trigger.api.dto.ActivityDrawResponseDTO;
import cn.bobo.trigger.api.dto.UserActivityAccountRequestDTO;
import cn.bobo.trigger.api.dto.UserActivityAccountResponseDTO;
import cn.bobo.types.model.Response;

/**
 * @author BO HE
 */
public interface IRaffleActivityService {

    Response<Boolean> armory(Long activityId);

    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

    Response<Boolean> dailyCheckinRebate(String userId);

    Response<Boolean> isDailyCheckinRebate(String userId);

    Response<UserActivityAccountResponseDTO> queryUserActivityAccount(UserActivityAccountRequestDTO request);

}
