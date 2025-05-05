package cn.bobo.test.domain;

import cn.bobo.domain.strategy.service.armory.IStrategyArmory;
import cn.bobo.infrastructure.persistent.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Test
    public void test_strategyArmory() {
        boolean success = strategyArmory.assembleLotteryStrategy(100002L);
        log.info("Test Result: {}", success);
    }

    @Test
    public void test_getAssembleRandomVal() {
        for (int i = 0; i < 200; i++) {
            log.info("Test Result：{} - returned award_id", strategyArmory.getRandomAwardId(100002L));
        }
    }
}
