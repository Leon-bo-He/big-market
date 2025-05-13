package cn.bobo.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
public class RuleTreeNodeLine {

    private Long id;
    private String tree_id;
    private String rule_node_from;
    private String rule_node_to;
    private String rule_limit_type;
    private String rule_limit_value;
    private Date create_time;
    private Date update_time;
}
