package cn.bobo.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {

    private Integer treeId;
    private String ruleNodeFrom;
    private String ruleNodeTo;
    private RuleLimitTypeVO ruleLimitType;
    private RuleLogicCheckTypeVO ruleLimitValue;

}
