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

    private int treeId;
    private String treeKey;
    private String treeDesc;
    private String ruleValue;
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;
}
