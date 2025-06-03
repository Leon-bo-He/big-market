package cn.bobo.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuProductEntity {

    private Long sku;
    private Long activityId;
    private Long activityCountId;
    private Integer stockCount;
    private Integer stockCountSurplus;
    private BigDecimal productAmount;

    private ActivityCount activityCount;

    @Data
    public static class ActivityCount {
        private Integer totalCount;
        private Integer dayCount;
        private Integer monthCount;
    }

}
