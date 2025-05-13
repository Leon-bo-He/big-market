package cn.bobo.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

import java.util.Map;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeVO {

    private String treeId;
    private String treeName;
    private String treeDesc;
    private String treeRootRuleNode;
    private Map<String, RuleTreeNodeVO> treeNodeMap;
}
