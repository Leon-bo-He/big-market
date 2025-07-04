package cn.bobo.test.trigger;

import cn.bobo.trigger.api.IRaffleActivityService;
import cn.bobo.trigger.api.dto.*;
import cn.bobo.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityControllerTest {

    @Resource
    private IRaffleActivityService raffleActivityService;

    @Test
    public void test_armory() {
        Response<Boolean> response = raffleActivityService.armory(100301L);
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_draw() {
        ActivityDrawRequestDTO request = new ActivityDrawRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("BB");
        Response<ActivityDrawResponseDTO> response = raffleActivityService.draw(request);

        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_draw_ten() {
        ActivityDrawRequestDTO request = new ActivityDrawRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("BB");
        Response<List<ActivityDrawResponseDTO>> response = raffleActivityService.drawTenTimes(request);

        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_draw_blacklist() {
        ActivityDrawRequestDTO request = new ActivityDrawRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("user003");
        Response<ActivityDrawResponseDTO> response = raffleActivityService.draw(request);

        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_dailyCheckinRebate(){
        Response<Boolean> response = raffleActivityService.dailyCheckinRebate("BB");
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_queryUserActivityAccount() {
        UserActivityAccountRequestDTO request = new UserActivityAccountRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("bobo");

        Response<UserActivityAccountResponseDTO> response = raffleActivityService.queryUserActivityAccount(request);

        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_querySkuProductListByActivityId() {
        Long request = 100301L;
        Response<List<SkuProductResponseDTO>> response = raffleActivityService.querySkuProductListByActivityId(request);
        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_queryUserCreditAccount() {
        String request = "bobo";
        Response<BigDecimal> response = raffleActivityService.queryUserCreditAccount(request);
        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_creditPayExchangeSku() throws InterruptedException {
        SkuProductShopCartRequestDTO request = new SkuProductShopCartRequestDTO();
        request.setUserId("bobo");
        request.setSku(9011L);
        Response<Boolean> response = raffleActivityService.creditPayExchangeSku(request);
        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));

        new CountDownLatch(1).await();
    }

}
