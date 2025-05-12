package cn.bobo.test.domain;

import cn.bobo.domain.strategy.service.armory.IStrategyArmory;
import cn.bobo.domain.strategy.service.rule.chain.ILogicChain;
import cn.bobo.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.bobo.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
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
public class LogicChainTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;
    @Resource
    private DefaultChainFactory defaultChainFactory;

    @Before
    public void setUp() {
        log.info("Test Result: {}", strategyArmory.assembleLotteryStrategy(100001L));
        log.info("Test Result: {}", strategyArmory.assembleLotteryStrategy(100002L));
        log.info("Test Result: {}", strategyArmory.assembleLotteryStrategy(100003L));
    }

    @Test
    public void test_LogicChain_rule_blacklist() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        Integer awardId = logicChain.logic("user001", 100001L);
        log.info("Test Result: {}", awardId);
    }

    @Test
    public void test_LogicChain_rule_weight() {
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 4900L);

        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        Integer awardId = logicChain.logic("bobo001", 100001L);
        log.info("Test Result: {}", awardId);
    }

    @Test
    public void test_LogicChain_rule_default() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        Integer awardId = logicChain.logic("bobo001", 100001L);
        log.info("Test Result: {}", awardId);
    }


}
