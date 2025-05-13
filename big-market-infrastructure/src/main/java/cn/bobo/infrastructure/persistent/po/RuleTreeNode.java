package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RuleTreeNode {

    private Long id;
    private String treeId;
    private String ruleKey;
    private String ruleDesc;
    private String ruleValue;
    private Date createTime;
    private Date updateTime;
}
