package cn.bobo.test.trigger;

import cn.bobo.trigger.api.IRaffleActivityService;
import cn.bobo.trigger.api.dto.ActivityDrawRequestDTO;
import cn.bobo.trigger.api.dto.ActivityDrawResponseDTO;
import cn.bobo.trigger.api.dto.UserActivityAccountRequestDTO;
import cn.bobo.trigger.api.dto.UserActivityAccountResponseDTO;
import cn.bobo.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
        request.setUserId("bobo");
        Response<ActivityDrawResponseDTO> response = raffleActivityService.draw(request);

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
        Response<Boolean> response = raffleActivityService.dailyCheckinRebate("user003");
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

}
