package cn.bobo.domain.strategy.service.rule.chain;

/**
 * @author BO HE
 */
public interface ILogicChainArmory {

    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
