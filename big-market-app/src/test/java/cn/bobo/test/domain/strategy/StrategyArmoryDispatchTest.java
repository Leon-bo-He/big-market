package cn.bobo.test.domain.strategy;

import cn.bobo.domain.strategy.service.armory.IStrategyArmory;
import cn.bobo.domain.strategy.service.armory.IStrategyDispatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryDispatchTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private IStrategyDispatch strategyDispatch;

    @Before
    public void test_strategyArmory() {
        boolean success = strategyArmory.assembleLotteryStrategy(100006L);
        log.info("Test Result: {}", success);
    }

    @Test
    public void test_getRandomAwardId() {
        for (int i = 0; i < 200 ; i++) {
            log.info("Test Result: returned award_id - {} ", strategyDispatch.getRandomAwardId(100006L));
        }
    }

    @Test
    public void test_getRandomAwardId_ruleWeightValue() {
        log.info("Test Result: rule_weight at 4000 - {} ", strategyDispatch.getRandomAwardId(100001L, "4000:102,103,104,105"));
        log.info("Test Result: rule_weight at 5000 - {} ", strategyDispatch.getRandomAwardId(100001L, "5000:102,103,104,105,106,107"));
        log.info("Test Result: rule_weight at 6000 - {} ", strategyDispatch.getRandomAwardId(100001L, "6000:102,103,104,105,106,107,108,109"));

    }
}
