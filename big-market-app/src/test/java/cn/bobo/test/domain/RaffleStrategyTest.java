package cn.bobo.test.domain;

import cn.bobo.domain.strategy.model.entity.RaffleAwardEntity;
import cn.bobo.domain.strategy.model.entity.RaffleFactorEntity;
import cn.bobo.domain.strategy.service.IRaffleStrategy;
import cn.bobo.domain.strategy.service.armory.IStrategyArmory;
import cn.bobo.domain.strategy.service.rule.impl.RuleLockLogicFilter;
import cn.bobo.domain.strategy.service.rule.impl.RuleWeightLogicFilter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

/**
 * @author BO HE
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private RuleWeightLogicFilter ruleWeightLogicFilter;
    @Resource
    private RuleLockLogicFilter ruleLockLogicFilter;

    @Before
    public void setUp() {
        log.info("Test Result: {}", strategyArmory.assembleLotteryStrategy(100001L));
        log.info("Test Result: {}", strategyArmory.assembleLotteryStrategy(100002L));
        log.info("Test Result: {}", strategyArmory.assembleLotteryStrategy(100003L));

        ReflectionTestUtils.setField(ruleWeightLogicFilter, "userScore", 4500L);
        ReflectionTestUtils.setField(ruleLockLogicFilter, "userRaffleCount", 0L);
    }


    @Test
    public void test_performRaffle() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("bobo001")
                .strategyId(100001L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("Request Parameters: {}", JSON.toJSONString(raffleFactorEntity));
        log.info("Test Result: {}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void test_performRaffle_blacklist() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("user003")  // blacklist users: user001,user002,user003
                .strategyId(100001L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("Request Parameters: {}", JSON.toJSONString(raffleFactorEntity));
        log.info("Test Result: {}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void test_raffle_in_rule_lock(){
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("bobo001")
                .strategyId(100003L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("Request Parameters: {}", JSON.toJSONString(raffleFactorEntity));
        log.info("Test Result: {}", JSON.toJSONString(raffleAwardEntity));
    }


}
