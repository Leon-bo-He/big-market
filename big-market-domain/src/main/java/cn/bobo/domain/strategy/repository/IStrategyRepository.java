package cn.bobo.domain.strategy.repository;

import cn.bobo.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bobo.domain.strategy.model.entity.StrategyEntity;
import cn.bobo.domain.strategy.model.entity.StrategyRuleEntity;
import cn.bobo.domain.strategy.model.vo.RuleTreeVO;
import cn.bobo.domain.strategy.model.vo.RuleWeightVO;
import cn.bobo.domain.strategy.model.vo.StrategyAwardInventoryKeyVO;
import cn.bobo.domain.strategy.model.vo.StrategyAwardRuleModelVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IStrategyRepository {

    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardRateSearchTable(String key, Integer rateRange, Map<Integer, Integer> strategyAwardRateSearchTable);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    void cacheStrategyAwardInventory(String cacheKey, Integer awardCount);

    Boolean subtractAwardInventory(String cacheKey);

    Boolean subtractAwardInventory(String cacheKey, Date endDateTime);

    void awardStockConsumeSendQueue(StrategyAwardInventoryKeyVO strategyAwardInventoryKeyVO);

    StrategyAwardInventoryKeyVO takeQueueValue();

    void updateStrategyAwardInventory(Long strategyId, Integer awardId);

    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);

    Long queryStrategyIdByActivityId(Long activityId);

    Integer queryTodayUserRaffleCount(String userId, Long strategyId);

    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);

    Integer queryActivityAccountTotalUseCount(String userId, Long strategyId);

    List<RuleWeightVO> queryAwardRuleWeight(Long strategyId);
}
