package cn.bobo.domain.strategy.repository;

import cn.bobo.domain.strategy.model.entity.StrategyAwardEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IStrategyRepository {

    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardRateSearchTable(Long strategyId, Integer precision, Map<Integer, Integer> shuffleStrategyAwardRateSearchTable);

    int getPrecision(Long strategyId);

    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
