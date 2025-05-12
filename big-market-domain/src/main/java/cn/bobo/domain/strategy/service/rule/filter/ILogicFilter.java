package cn.bobo.domain.strategy.service.rule.filter;

import cn.bobo.domain.strategy.model.entity.RuleActionEntity;
import cn.bobo.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author BO HE
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);

}