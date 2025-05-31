package cn.bobo.test.domain.rebate;

import cn.bobo.domain.rebate.model.entity.BehaviorEntity;
import cn.bobo.domain.rebate.model.vo.BehaviorTypeVO;
import cn.bobo.domain.rebate.service.IBehaviorRebateService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BehaviorRebateServiceTest {

    @Resource
    private IBehaviorRebateService behaviorRebateService;

    @Test
    public void test_createOrder() throws InterruptedException {
        BehaviorEntity behaviorEntity = new BehaviorEntity();
        behaviorEntity.setUserId("bobo");
        behaviorEntity.setBehaviorTypeVO(BehaviorTypeVO.CHECK_IN);
        // 重复的 OutBusinessNo 会报错唯一索引冲突，这也是保证幂等的手段，确保不会多记账
        behaviorEntity.setOutBusinessNo("20240516");

        List<String> orderIds = behaviorRebateService.createOrder(behaviorEntity);
        log.info("parameters：{}", JSON.toJSONString(behaviorEntity));
        log.info("test results：{}", JSON.toJSONString(orderIds));

        new CountDownLatch(1).await();
    }

}
