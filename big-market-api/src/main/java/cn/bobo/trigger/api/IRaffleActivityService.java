package cn.bobo.trigger.api;

import cn.bobo.trigger.api.dto.*;
import cn.bobo.types.model.Response;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BO HE
 */
public interface IRaffleActivityService {

    Response<Boolean> armory(Long activityId);

    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

    Response<Boolean> dailyCheckinRebate(String userId);

    Response<Boolean> isDailyCheckinRebate(String userId);

    Response<UserActivityAccountResponseDTO> queryUserActivityAccount(UserActivityAccountRequestDTO request);

    Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId);

    Response<BigDecimal> queryUserCreditAccount(String userId);

    Response<Boolean> creditPayExchangeSku(SkuProductShopCartRequestDTO request);

}
