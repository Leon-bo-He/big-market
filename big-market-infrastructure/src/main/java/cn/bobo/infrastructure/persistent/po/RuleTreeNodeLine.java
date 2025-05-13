package cn.bobo.infrastructure.persistent.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RuleTreeNodeLine {

    private Long id;
    private String treeId;
    private String ruleNodeFrom;
    private String ruleNodeTo;
    private String ruleLimitType;
    private String ruleLimitValue;
    private Date createTime;
    private Date updateTime;
}
