package cn.bobo.domain.strategy.service.rule.chain;

/**
 * @author BO HE
 */
public interface ILogicChain extends ILogicChainArmory{

    Integer logic(String userId, Long strategyId);

}
