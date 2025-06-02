package cn.bobo.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * @author BO HE
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuEntity {
    private Long sku;
    private Long activityId;
    private Long activityCountId;
    private Integer stockCount;
    private Integer stockCountSurplus;
    private BigDecimal productAmount;

}
