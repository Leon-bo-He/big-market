//package cn.bobo.test.domain;
//
//import cn.bobo.domain.strategy.model.vo.*;
//import cn.bobo.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
//import cn.bobo.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * @author BO HE
// */
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LogicTreeTest {
//
//    @Resource
//    private DefaultTreeFactory defaultTreeFactory;
//
//    /**
//     * rule_lock --left--> rule_luck_award
//     *           --right--> rule_stock --right--> rule_luck_award
//     */
//    @Test
//    public void test_tree_rule() {
//        RuleTreeNodeVO rule_lock = RuleTreeNodeVO.builder()
//                .treeId(100000001)
//                .ruleKey("rule_lock")
//                .ruleDesc("unlock certain award as the user completes N times of lottery")
//                .ruleValue("1")
//                .treeNodeLineVOList(new ArrayList<RuleTreeNodeLineVO>() {{
//                    add(RuleTreeNodeLineVO.builder()
//                            .treeId(100000001)
//                            .ruleNodeFrom("rule_lock")
//                            .ruleNodeTo("rule_luck_award")
//                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
//                            .ruleLimitValue(RuleLogicCheckTypeVO.TAKE_OVER)
//                            .build());
//
//                    add(RuleTreeNodeLineVO.builder()
//                            .treeId(100000001)
//                            .ruleNodeFrom("rule_lock")
//                            .ruleNodeTo("rule_stock")
//                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
//                            .ruleLimitValue(RuleLogicCheckTypeVO.ALLOW)
//                            .build());
//                }})
//                .build();
//
//        RuleTreeNodeVO rule_luck_award = RuleTreeNodeVO.builder()
//                .treeId(100000001)
//                .ruleKey("rule_luck_award")
//                .ruleDesc("unlock certain award as the user completes N times of lottery")
//                .ruleValue("1")
//                .treeNodeLineVOList(null)
//                .build();
//
//        RuleTreeNodeVO rule_stock = RuleTreeNodeVO.builder()
//                .treeId(100000001)
//                .ruleKey("rule_stock")
//                .ruleDesc("storage processing rule")
//                .ruleValue(null)
//                .treeNodeLineVOList(new ArrayList<RuleTreeNodeLineVO>() {{
//                    add(RuleTreeNodeLineVO.builder()
//                            .treeId(100000001)
//                            .ruleNodeFrom("rule_lock")
//                            .ruleNodeTo("rule_luck_award")
//                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
//                            .ruleLimitValue(RuleLogicCheckTypeVO.TAKE_OVER)
//                            .build());
//                }})
//                .build();
//
//        RuleTreeVO ruleTreeVO = new RuleTreeVO();
//        ruleTreeVO.setTreeId(100000001);
//        ruleTreeVO.setTreeName("decision tree rule；add dall-e-3 draw model");
//        ruleTreeVO.setTreeDesc("decision tree rule；add dall-e-3 draw model");
//        ruleTreeVO.setTreeRootRuleNode("rule_lock");
//
//        ruleTreeVO.setTreeNodeMap(new HashMap<String, RuleTreeNodeVO>() {{
//            put("rule_lock", rule_lock);
//            put("rule_stock", rule_stock);
//            put("rule_luck_award", rule_luck_award);
//        }});
//
//        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
//
//        DefaultTreeFactory.StrategyAwardData data = treeEngine.process("bobo", 100001L, 100);
//        log.info("Test Result: {}", JSON.toJSONString(data));
//
//    }
//
//}
//
