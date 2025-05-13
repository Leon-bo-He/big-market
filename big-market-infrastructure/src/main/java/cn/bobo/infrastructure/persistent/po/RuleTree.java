package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RuleTree {

    private Long id;
    private String treeId ;
    private String treeName;
    private String treeDesc;
    private String treeRootRuleKey;
    private Date createTime;
    private Date updateTime;
}
