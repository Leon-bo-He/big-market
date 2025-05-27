package cn.bobo.domain.activity.service.quota.rule;

/**
 * @author BO HE
 */
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);
}
