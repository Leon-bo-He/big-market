package cn.bobo.domain.strategy.model.entity;

import cn.bobo.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {

    private Long strategyId;
    private Integer awardId;
    private Integer ruleType;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;

    public Map<String, List<Integer>> getRuleWeightValues() {
        if (!Constants.RULE_WEIGHT.equals(ruleModel)) return null;
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) continue;

            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight invalid value format: " + ruleValueGroup);
            }

            String[] valuesString = parts[1].split(Constants.COMMA);
            List<Integer> values = new ArrayList<>();
            for (String valueString : valuesString) {
                values.add(Integer.parseInt(valueString));
            }

            resultMap.put(ruleValueGroup, values);
        }

        return resultMap;
    }


}
