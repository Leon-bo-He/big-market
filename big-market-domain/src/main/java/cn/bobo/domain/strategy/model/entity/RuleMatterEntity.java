package cn.bobo.domain.strategy.model.entity;

import lombok.Data;

/**
 * @author BO HE
 * @description rule matter entity, contains required fields for filter rule logic
 */

@Data
public class RuleMatterEntity {

    private String userId;
    private Long strategyId;
    private Integer awardId;
    private String ruleModel;

}
