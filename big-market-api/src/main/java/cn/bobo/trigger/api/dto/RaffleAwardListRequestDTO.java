package cn.bobo.trigger.api.dto;

import lombok.Data;

/**
 * @author BO HE
 */

@Data
public class RaffleAwardListRequestDTO {

    @Deprecated
    private Long strategyId;

    private Long activityId;

    private String userId;

}
