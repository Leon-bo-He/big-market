package cn.bobo.domain.rebate.repository;

import cn.bobo.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.bobo.domain.rebate.model.vo.BehaviorTypeVO;
import cn.bobo.domain.rebate.model.vo.DailyBehaviorRebateVO;

import java.util.List;

public interface IBehaviorRebateRepository {

    List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO);

    void saveUserRebateRecord(String userId, List<BehaviorRebateAggregate> behaviorRebateAggregates);

}
