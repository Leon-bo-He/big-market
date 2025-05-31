package cn.bobo.trigger.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author BO HE
 */

@Data
public class RaffleStrategyRuleWeightResponseDTO {

    private Integer ruleWeightCount;
    private Integer userActivityAccountTotalUseCount;
    private List<StrategyAward> strategyAwards;

    @Data
    public static class StrategyAward {
        private Integer awardId;
        private String awardTitle;
    }
}
