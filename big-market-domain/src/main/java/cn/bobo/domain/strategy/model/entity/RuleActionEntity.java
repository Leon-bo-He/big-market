package cn.bobo.domain.strategy.model.entity;

import cn.bobo.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;

    static public class RaffleEntity {
    }


    /**
     * This is the entity that includes additional fields specific to the pre-draw state.
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity {
        private Long strategyId;
        private String ruleWeightValueKey;
        private Integer awardId;
    }

    static public class RaffleInEntity extends RaffleEntity {

    }

    static public class RaffleAfterEntity extends RaffleEntity {

    }

}
