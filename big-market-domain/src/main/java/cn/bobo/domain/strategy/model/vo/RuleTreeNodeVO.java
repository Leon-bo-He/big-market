package cn.bobo.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeVO {

    private String treeId;
    private String ruleKey;
    private String ruleDesc;
    private String ruleValue;
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;
}
