package cn.bobo.trigger.http;

import cn.bobo.domain.activity.model.entity.*;
import cn.bobo.domain.activity.model.vo.OrderTradeTypeVO;
import cn.bobo.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.bobo.domain.activity.service.IRaffleActivityPartakeService;
import cn.bobo.domain.activity.service.IRaffleActivitySkuProductService;
import cn.bobo.domain.activity.service.armory.IActivityArmory;
import cn.bobo.domain.award.model.entity.UserAwardRecordEntity;
import cn.bobo.domain.award.model.vo.AwardStateVO;
import cn.bobo.domain.award.service.IAwardService;
import cn.bobo.domain.credit.model.entity.CreditAccountEntity;
import cn.bobo.domain.credit.model.entity.TradeEntity;
import cn.bobo.domain.credit.model.vo.TradeNameVO;
import cn.bobo.domain.credit.model.vo.TradeTypeVO;
import cn.bobo.domain.credit.service.ICreditAdjustService;
import cn.bobo.domain.rebate.model.entity.BehaviorEntity;
import cn.bobo.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.bobo.domain.rebate.model.vo.BehaviorTypeVO;
import cn.bobo.domain.rebate.service.IBehaviorRebateService;
import cn.bobo.domain.strategy.model.entity.RaffleAwardEntity;
import cn.bobo.domain.strategy.model.entity.RaffleFactorEntity;
import cn.bobo.domain.strategy.service.IRaffleStrategy;
import cn.bobo.domain.strategy.service.armory.IStrategyArmory;
import cn.bobo.trigger.api.IRaffleActivityService;
import cn.bobo.trigger.api.dto.*;
import cn.bobo.types.enums.ResponseCode;
import cn.bobo.types.exception.AppException;
import cn.bobo.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BO HE
 */
@Slf4j
@RestController
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/activity")
public class RaffleActivityController implements IRaffleActivityService {

    private final SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyyMMdd");

    @Resource
    private IRaffleActivityPartakeService raffleActivityPartakeService;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @Resource
    private IRaffleActivitySkuProductService raffleActivitySkuProductService;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private IAwardService awardService;
    @Resource
    private IActivityArmory activityArmory;
    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IBehaviorRebateService behaviorRebateService;
    @Resource
    private ICreditAdjustService creditAdjustService;


    /**
     * <a href="http://localhost:8091/api/v1/raffle/activity/armory">/api/v1/raffle/activity/armory</a>
     * parameters：{"activityId":100001,"userId":"bobo"}
     *
     * curl --request GET \
     *   --url 'http://localhost:8091/api/v1/raffle/activity/armory?activityId=100301'
     *
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "armory", method = RequestMethod.GET)
    @Override
    public Response<Boolean> armory(@RequestParam Long activityId) {
        try {
            log.info("activity assembly, data preheating starting; activityId:{}", activityId);
            // 1. assemble activity
            activityArmory.assembleActivitySkuByActivityId(activityId);
            // 2. assemble strategy
            strategyArmory.assembleLotteryStrategyByActivityId(activityId);
            Response<Boolean> response = Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
            log.info("activity assembly, data preheating completed; activityId:{}", activityId);
            return response;
        } catch (Exception e) {
            log.error("activity assembly, data preheating fail; activityId:{}", activityId);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }


    /**
     *
     *
     * <a href="http://localhost:8091/api/v1/raffle/activity/draw">/api/v1/raffle/activity/draw</a>
     * parameters：{"activityId":100001,"userId":"bobo"}
     *
     * curl --request POST \
     *   --url http://localhost:8091/api/v1/raffle/activity/draw \
     *   --header 'content-type: application/json' \
     *   --data '{
     *     "userId":"bobo",
     *     "activityId": 100301
     *     }'
     *
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "draw", method = RequestMethod.POST)
    @Override
    public Response<ActivityDrawResponseDTO> draw(@RequestBody ActivityDrawRequestDTO request) {
        try {
            log.info("activity draw start, userId:{}, activityId:{}", request.getUserId(), request.getActivityId());
            // 1. check parameters
            if (StringUtils.isBlank(request.getUserId()) || null == request.getActivityId()) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            // 2. participate in activity - create participation order
            UserRaffleOrderEntity orderEntity = raffleActivityPartakeService.createOrder(request.getUserId(), request.getActivityId());
            log.info("activity draw order created, userId:{}, activityId:{}, orderId:{}",
                    request.getUserId(), request.getActivityId(), orderEntity.getOrderId());
            // 3. raffle strategy - execute raffle
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(RaffleFactorEntity.builder()
                    .userId(orderEntity.getUserId())
                    .strategyId(orderEntity.getStrategyId())
                    .endDateTime(orderEntity.getEndDateTime())
                    .build());
            // 4. store result - write winning record
            UserAwardRecordEntity userAwardRecord = UserAwardRecordEntity.builder()
                    .userId(orderEntity.getUserId())
                    .activityId(orderEntity.getActivityId())
                    .strategyId(orderEntity.getStrategyId())
                    .orderId(orderEntity.getOrderId())
                    .awardId(raffleAwardEntity.getAwardId())
                    .awardTitle(raffleAwardEntity.getAwardTitle())
                    .awardTime(new Date())
                    .awardState(AwardStateVO.CREATE)
                    .awardConfig(raffleAwardEntity.getAwardConfig())
                    .build();
            awardService.saveUserAwardRecord(userAwardRecord);
            // 5. return result
            return Response.<ActivityDrawResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(ActivityDrawResponseDTO.builder()
                            .awardId(raffleAwardEntity.getAwardId())
                            .awardTitle(raffleAwardEntity.getAwardTitle())
                            .awardIndex(raffleAwardEntity.getSort())
                            .build())
                    .build();
        } catch (AppException e) {
            log.error("activity draw failed, userId:{}, activityId:{}", request.getUserId(), request.getActivityId(), e);
            return Response.<ActivityDrawResponseDTO>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("activity draw failed, userId:{}, activityId:{}", request.getUserId(), request.getActivityId(), e);
            return Response.<ActivityDrawResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }


    /**
     * Daily check-in rebate interface
     *
     * <a href="http://localhost:8091/api/v1/raffle/activity/daily_checkin_rebate">/api/v1/raffle/activity/daily_checkin_rebate</a>
     * parameters：{"userId":"bobo"}
     *
     * curl -X POST http://localhost:8091/api/v1/raffle/activity/daily_checkin_rebate -d "userId=bobo" -H "Content-Type: application/x-www-form-urlencoded"
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "daily_checkin_rebate", method = RequestMethod.POST)
    @Override
    public Response<Boolean> dailyCheckinRebate(@RequestParam String userId) {
        try {
            log.info("Daily check-in rebate start. userId:{}", userId);
            BehaviorEntity behaviorEntity = new BehaviorEntity();
            behaviorEntity.setUserId(userId);
            behaviorEntity.setBehaviorTypeVO(BehaviorTypeVO.CHECK_IN);
            behaviorEntity.setOutBusinessNo(dateFormatDay.format(new Date()));
            List<String> orderIds = behaviorRebateService.createOrder(behaviorEntity);
            log.info("Daily check-in rebate completed. userId:{} orderIds: {}", userId, JSON.toJSONString(orderIds));
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        } catch (AppException e) {
            log.error("Daily check-in rebate error. userId:{} ", userId, e);
            return Response.<Boolean>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("Daily check-in rebate failed. userId:{}", userId);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();
        }

    }

    /**
     *
     * curl -X POST http://localhost:8091/api/v1/raffle/activity/is_daily_checkin_rebate -d "userId=bobo" -H "Content-Type: application/x-www-form-urlencoded"
     */
    @RequestMapping(value = "is_daily_checkin_rebate", method = RequestMethod.POST)
    @Override
    public Response<Boolean> isDailyCheckinRebate(@RequestParam String userId) {
        try {
            log.info("check if user has completed daily check-in rebate start. userId:{}", userId);
            String outBusinessNo = dateFormatDay.format(new Date());
            List<BehaviorRebateOrderEntity> behaviorRebateOrderEntities = behaviorRebateService.queryOrderByOutBusinessNo(userId, outBusinessNo);
            log.info("check if user has completed daily check-in rebate completed. userId:{} orders.size:{}", userId, behaviorRebateOrderEntities.size());
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(!behaviorRebateOrderEntities.isEmpty())
                    .build();
        } catch (Exception e) {
            log.error("check if user has completed daily check-in rebate failed. userId:{}", userId, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();
        }
    }


    /**
     * curl --request POST \
     * --url http://localhost:8091/api/v1/raffle/activity/query_user_activity_account \
     * --header 'content-type: application/json' \
     * --data '{
     * "userId":"bobo",
     * "activityId": 100301
     * }'
     */
    @RequestMapping(value = "query_user_activity_account", method = RequestMethod.POST)
    @Override
    public Response<UserActivityAccountResponseDTO> queryUserActivityAccount(@RequestBody UserActivityAccountRequestDTO request) {
        try {
            log.info("check user activity account start, userId:{}, activityId:{}", request.getUserId(), request.getActivityId());
            // 1. check parameters
            if (StringUtils.isBlank(request.getUserId()) || null == request.getActivityId()) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            ActivityAccountEntity activityAccountEntity = raffleActivityAccountQuotaService.queryActivityAccountEntity(request.getActivityId(), request.getUserId());
            UserActivityAccountResponseDTO userActivityAccountResponseDTO = UserActivityAccountResponseDTO.builder()
                    .totalCount(activityAccountEntity.getTotalCount())
                    .totalCountSurplus(activityAccountEntity.getTotalCountSurplus())
                    .dayCount(activityAccountEntity.getDayCount())
                    .dayCountSurplus(activityAccountEntity.getDayCountSurplus())
                    .monthCount(activityAccountEntity.getMonthCount())
                    .monthCountSurplus(activityAccountEntity.getMonthCountSurplus())
                    .build();
            log.info("check user activity account completed, userId:{}, activityId:{}, response:{}",
                    request.getUserId(), request.getActivityId(), JSON.toJSONString(userActivityAccountResponseDTO));
            return Response.<UserActivityAccountResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(userActivityAccountResponseDTO)
                    .build();
        } catch (Exception e) {
            log.error("check user activity account failed, userId:{}, activityId:{}", request.getUserId(), request.getActivityId(), e);
            return Response.<UserActivityAccountResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    public Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId) {
        try {
            log.info("query sku product list by activityId start, activityId:{}", activityId);
            // 1. parameter check
            if (null == activityId) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            // 2. query sku product entities by activityId
            List<SkuProductEntity> skuProductEntities = raffleActivitySkuProductService.querySkuProductEntityListByActivityId(activityId);
            List<SkuProductResponseDTO> skuProductResponseDTOS = new ArrayList<>(skuProductEntities.size());
            for (SkuProductEntity skuProductEntity : skuProductEntities) {

                SkuProductResponseDTO.ActivityCount activityCount = new SkuProductResponseDTO.ActivityCount();
                activityCount.setTotalCount(skuProductEntity.getActivityCount().getTotalCount());
                activityCount.setMonthCount(skuProductEntity.getActivityCount().getMonthCount());
                activityCount.setDayCount(skuProductEntity.getActivityCount().getDayCount());

                SkuProductResponseDTO skuProductResponseDTO = new SkuProductResponseDTO();
                skuProductResponseDTO.setSku(skuProductEntity.getSku());
                skuProductResponseDTO.setActivityId(skuProductEntity.getActivityId());
                skuProductResponseDTO.setActivityCountId(skuProductEntity.getActivityCountId());
                skuProductResponseDTO.setStockCount(skuProductEntity.getStockCount());
                skuProductResponseDTO.setStockCountSurplus(skuProductEntity.getStockCountSurplus());
                skuProductResponseDTO.setProductAmount(skuProductEntity.getProductAmount());
                skuProductResponseDTO.setActivityCount(activityCount);
                skuProductResponseDTOS.add(skuProductResponseDTO);
            }

            log.info("query sku product list by activityId completed, activityId:{}, response:{}", activityId, JSON.toJSONString(skuProductResponseDTOS));
            return Response.<List<SkuProductResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(skuProductResponseDTOS)
                    .build();
        } catch (Exception e) {
            log.error("query sku product list by activityId failed, activityId:{}", activityId, e);
            return Response.<List<SkuProductResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @Override
    public Response<BigDecimal> queryUserCreditAccount(String userId) {
        try {
            log.info("query user credit account start, userId:{}", userId);
            CreditAccountEntity creditAccountEntity = creditAdjustService.queryUserCreditAccount(userId);
            log.info("query user credit account completed, userId:{} adjustAmount:{}", userId, creditAccountEntity.getAdjustAmount());
            return Response.<BigDecimal>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(creditAccountEntity.getAdjustAmount())
                    .build();
        } catch (Exception e) {
            log.error("query user credit account failed, userId:{}", userId, e);
            return Response.<BigDecimal>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @RequestMapping(value = "credit_pay_exchange_sku", method = RequestMethod.POST)
    @Override
    public Response<Boolean> creditPayExchangeSku(@RequestBody SkuProductShopCartRequestDTO request) {
        try {
            log.info("credit redeem products start, userId:{}, sku:{}", request.getUserId(), request.getSku());
            UnpaidActivityOrderEntity unpaidActivityOrder = raffleActivityAccountQuotaService.createOrder(SkuRechargeEntity.builder()
                    .userId(request.getUserId())
                    .sku(request.getSku())
                    .outBusinessNo(RandomStringUtils.randomNumeric(12))
                    .orderTradeType(OrderTradeTypeVO.CREDIT_PAY_TRADE)
                    .build());
            log.info("credit redeem products create order completed, userId:{}, sku:{}, orderId:{}",
                    request.getUserId(), request.getSku(), unpaidActivityOrder.getOutBusinessNo());

            String orderId = creditAdjustService.createOrder(TradeEntity.builder()
                    .userId(unpaidActivityOrder.getUserId())
                    .tradeName(TradeNameVO.CONVERT_SKU)
                    .tradeType(TradeTypeVO.REVERSE)
                    .amount(unpaidActivityOrder.getPayAmount().negate())
                    .outBusinessNo(unpaidActivityOrder.getOutBusinessNo())
                    .build());
            log.info("credit redeem products order payment completed, userId:{}, sku:{}, orderId:{}",
                    request.getUserId(), request.getSku(), orderId);

            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        } catch (Exception e) {
            log.error("credit redeem products failed, userId:{}, sku:{}", request.getUserId(), request.getSku(), e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();
        }
    }
}
