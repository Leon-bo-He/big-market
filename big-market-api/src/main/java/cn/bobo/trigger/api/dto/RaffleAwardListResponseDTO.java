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
public class RaffleAwardListResponseDTO {

    private Integer awardId;
    private String awardTitle;
    private String awardSubtitle;
    private Integer sort;

    private Integer awardRuleLockCount;
    private boolean isAwardUnlock;
    private Integer waitUnlockCount;

}
