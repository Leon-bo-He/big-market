package cn.bobo.infrastructure.persistent.repository;

import cn.bobo.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.bobo.infrastructure.persistent.po.StrategyAward;
import cn.bobo.infrastructure.persistent.redis.IRedisService;
import cn.bobo.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Resource
    private IRedisService redisService;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {

        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> StrategyAwardEntities= redisService.getValue(cacheKey);

        if (StrategyAwardEntities != null && !StrategyAwardEntities.isEmpty()) return StrategyAwardEntities;

        //  Query the strategy award list from the database if not found in the cache
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        StrategyAwardEntities = new ArrayList<>(strategyAwards.size());

        for (StrategyAward strategyAward : strategyAwards) {
            StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                    .strategyId(strategyAward.getStrategyId())
                    .awardId(strategyAward.getAwardId())
                    .awardCount(strategyAward.getAwardCount())
                    .awardCountSurplus(strategyAward.getAwardCountSurplus())
                    .awardRate(strategyAward.getAwardRate())
                    .build();

            StrategyAwardEntities.add(strategyAwardEntity);
        }
        redisService.setValue(cacheKey, StrategyAwardEntities);
        return StrategyAwardEntities;
    }

    @Override
    public void storeStrategyAwardRateSearchTable(Long strategyId, Integer rateRange,
                                                  Map<Integer, Integer> strategyAwardRateSearchTable) {
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId, rateRange);
        Map<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId);
        cacheRateTable.putAll(strategyAwardRateSearchTable);
    }

    @Override
    public int getRateRange(Long strategyId) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId);
    }

    @Override
    public Integer getStrategyAwardAssemble(Long strategyId, int rateKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId, rateKey);
    }

}
