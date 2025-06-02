package cn.bobo.domain.activity.model.entity;

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
public class DeliveryOrderEntity {

    private String userId;
    private String outBusinessNo;
}
