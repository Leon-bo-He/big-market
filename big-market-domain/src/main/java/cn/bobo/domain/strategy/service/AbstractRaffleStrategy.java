package cn.bobo.domain.strategy.service;

import cn.bobo.domain.strategy.model.entity.RaffleAwardEntity;
import cn.bobo.domain.strategy.model.entity.RaffleFactorEntity;
import cn.bobo.domain.strategy.model.entity.RuleActionEntity;
import cn.bobo.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.bobo.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.armory.IStrategyDispatch;
import cn.bobo.domain.strategy.service.rule.chain.ILogicChain;
import cn.bobo.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.bobo.types.enums.ResponseCode;
import cn.bobo.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BO HE
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    protected IStrategyRepository repository;
    protected IStrategyDispatch strategyDispatch;
    private final DefaultChainFactory defaultChainFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory) {
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
        this.defaultChainFactory = defaultChainFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {

        // 1. check parameter
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. chain of responsibility pattern to filter the strategy
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        Integer awardId = logicChain.logic(userId, strategyId);

        // 5. query award rules [ in-draw: filter out the awardId based on some lock rule; after-draw: filter out awardId after
        // deducting the prize inventory, If the prize is blocked during the draw or out of stock, return a fallback prize.]
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModel(strategyId, awardId);

        // 6. in-draw, rule logic check and filter
        RuleActionEntity<RuleActionEntity.RaffleInEntity> ruleActionEntityIn = this.doCheckRaffleInLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId)
                .build(), strategyAwardRuleModelVO.inRaffleRuleModelList());

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionEntityIn.getCode())){
            log.info("[temp log] in raffle rule intercept, fallback award through rule_luck_award.");
            return RaffleAwardEntity.builder()
                    .awardDesc("In raffle rule intercept, fallback award through rule_luck_award.")
                    .build();
        }

        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();


    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
    protected abstract RuleActionEntity<RuleActionEntity.RaffleInEntity> doCheckRaffleInLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
}
