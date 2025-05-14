package cn.bobo.domain.strategy.service.rule.chain.factory;

import cn.bobo.domain.strategy.model.entity.StrategyEntity;
import cn.bobo.domain.strategy.repository.IStrategyRepository;
import cn.bobo.domain.strategy.service.rule.chain.ILogicChain;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BO HE
 */
@Service
public class DefaultChainFactory {

    private final Map<String, ILogicChain> logicChainGroup;

    protected IStrategyRepository repository;


    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    public ILogicChain openLogicChain(Long strategyId) {
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        if (null == ruleModels || 0 == ruleModels.length) return logicChainGroup.get("default");

        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for (int i = 1; i < ruleModels.length; i++) {
            ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
            current = current.appendNext(nextChain);
        }

        current.appendNext(logicChainGroup.get("default"));

        return logicChain;


    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {
        private Integer awardId;
        private String logicModel;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {
        RULE_DEFAULT("rule_default", "default"),
        RULE_BLACKLIST("rule_blacklist", "blacklist"),
        RULE_WEIGHT("rule_weight", "weight"),
        ;

        private final String code;
        private final String info;
    }
}
