package cn.bobo.trigger.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BO HE
 */

@Data
public class SkuProductResponseDTO {
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
