package cn.bobo.domain.rebate.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyBehaviorRebateVO {

    private String behaviorType;
    private String rebateDesc;
    private String rebateType;
    private String rebateConfig;

}
