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
public class ActivityDrawResponseDTO {

    private Integer awardId;
    private String awardTitle;
    private Integer awardIndex;
}
