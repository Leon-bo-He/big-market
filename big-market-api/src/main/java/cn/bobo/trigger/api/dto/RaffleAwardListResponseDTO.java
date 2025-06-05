package cn.bobo.trigger.api.dto;

import java.math.BigDecimal;

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
public class RaffleAwardListResponseDTO {

    private Integer awardId;
    private String awardTitle;
    private String awardSubtitle;
    private Integer sort;
    private BigDecimal awardRate;

    private Integer awardRuleLockCount;
    private boolean isAwardUnlock;
    private Integer waitUnlockCount;

}
