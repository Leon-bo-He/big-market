package cn.bobo.domain.strategy.model.vo;

import cn.bobo.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import cn.bobo.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BO HE
 * @description Strategy award rule model value object, no unique id, query object from database
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

    private String ruleModels;

    public String[] inRaffleRuleModelList() {
        String[] ruleModelValues = ruleModels.split(Constants.COMMA);
        List<String> ruleModelList = new ArrayList<>();

        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isInRaffle(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }

        return ruleModelList.toArray(new String[0]);
    }

    public String[] afterRaffleRuleModelList() {
        String[] ruleModelValues = ruleModels.split(Constants.COMMA);
        List<String> ruleModelList = new ArrayList<>();

        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isAfterRaffle(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }

        return ruleModelList.toArray(new String[0]);
    }
}
