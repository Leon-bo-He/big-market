package cn.bobo.test.trigger;

import cn.bobo.trigger.api.IRaffleStrategyService;
import cn.bobo.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.bobo.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.bobo.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyControllerTest {

    @Resource
    private IRaffleStrategyService raffleStrategyService;

    @Test
    public void test_queryRaffleAwardList() {
        RaffleAwardListRequestDTO request = new RaffleAwardListRequestDTO();
        request.setUserId("bobo");
        request.setActivityId(100301L);
        Response<List<RaffleAwardListResponseDTO>> response = raffleStrategyService.queryRaffleAwardList(request);

        log.info("parameters：{}", JSON.toJSONString(request));
        log.info("Test Results：{}", JSON.toJSONString(response));
    }

}
