package cn.bobo.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BO HE
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchAwardEntity {

    private String userId;
    private String orderId;
    private Integer awardId;
    private String awardConfig;
}
