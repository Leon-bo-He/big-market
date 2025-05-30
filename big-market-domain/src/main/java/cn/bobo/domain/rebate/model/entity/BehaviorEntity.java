package cn.bobo.domain.rebate.model.entity;

import cn.bobo.domain.rebate.model.vo.BehaviorTypeVO;
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
public class BehaviorEntity {

    private String userId;
    private BehaviorTypeVO behaviorTypeVO;
    private String outBusinessNo;
}
