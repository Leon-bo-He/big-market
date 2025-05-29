package cn.bobo.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleStrategyResponseDTO {

    private Integer awardId;
    private Integer awardIndex;


}
