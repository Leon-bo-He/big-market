package cn.bobo.domain.strategy.service.rule.filter.factory;

import cn.bobo.domain.strategy.model.entity.RuleActionEntity;
import cn.bobo.domain.strategy.service.annotation.LogicStrategy;
import cn.bobo.domain.strategy.service.rule.filter.ILogicFilter;
import com.alibaba.fastjson2.util.AnnotationUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BO HE
 */

@Service
public class DefaultLogicFactory {

    public Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        logicFilters.forEach(logic -> {
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logic.getClass(), LogicStrategy.class);
            if (null != strategy) {
                logicFilterMap.put(strategy.logicMode().getCode(), logic);
            }
        });
    }

    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_WIGHT("rule_weight","pre_draw rule, return awards key based on rule_weight", "before"),
        RULE_BLACKLIST("rule_blacklist","pre_draw rule, filter blacklist users, if exist return directly", "before"),
        RULE_LOCK("rule_lock","in_draw rule, unlock awards after n times draw", "in"),
        RULE_LUCK_AWARD("rule_luck_award","after_draw rule, fallback prize", "after"),
        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isBeforeRaffle(String code) {
            return "before".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

        public static boolean isInRaffle(String code) {
            return "in".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

        public static boolean isAfterRaffle(String code) {
            return "after".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

    }

}
