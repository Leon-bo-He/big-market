package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RuleTreeNode {

    private Long id;
    private String tree_id;
    private String rule_key;
    private String rule_desc;
    private String rule_value;
    private Date create_time;
    private Date update_time;
}
