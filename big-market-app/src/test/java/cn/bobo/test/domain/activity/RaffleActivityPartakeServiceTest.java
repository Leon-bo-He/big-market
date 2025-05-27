package cn.bobo.test.domain.activity;

import cn.bobo.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.bobo.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.bobo.domain.activity.service.IRaffleActivityPartakeService;
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
public class RaffleActivityPartakeServiceTest {

    @Resource
    private IRaffleActivityPartakeService raffleActivityPartakeService;

    @Test
    public void test_createOrder() {
        PartakeRaffleActivityEntity partakeRaffleActivityEntity = new PartakeRaffleActivityEntity();
        partakeRaffleActivityEntity.setUserId("bobo");
        partakeRaffleActivityEntity.setActivityId(100301L);
        UserRaffleOrderEntity userRaffleOrder = raffleActivityPartakeService.createOrder(partakeRaffleActivityEntity);
        log.info("Query parameters：{}", JSON.toJSONString(partakeRaffleActivityEntity));
        log.info("Test results：{}", JSON.toJSONString(userRaffleOrder));
    }

}
