package cn.bobo.domain.strategy.model.entity;

import cn.bobo.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {

    private Long strategyId;
    private String strategyDesc;
    private String ruleModels;

    public String[] ruleModels() {
        if (StringUtils.isBlank(ruleModels)) return null;
        return ruleModels.split(Constants.COMMA);
    }

    public String getRuleWeight() {
        String[] ruleModels = this.ruleModels();
        if (null == ruleModels) {
            return null;
        }
        for (String ruleModel : ruleModels) {
            if (Constants.RULE_WEIGHT.equals(ruleModel)) return ruleModel;
        }
        return null;
    }
}
