package cn.bobo.trigger.api;

import cn.bobo.trigger.api.dto.ActivityDrawRequestDTO;
import cn.bobo.trigger.api.dto.ActivityDrawResponseDTO;
import cn.bobo.types.model.Response;

/**
 * @author BO HE
 */
public interface IRaffleActivityService {

    Response<Boolean> armory(Long activityId);

    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

}
