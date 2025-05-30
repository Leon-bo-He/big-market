package cn.bobo.domain.rebate.model.aggregate;

import cn.bobo.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.bobo.domain.rebate.model.entity.TaskEntity;
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
public class BehaviorRebateAggregate {

    private String userId;
    private BehaviorRebateOrderEntity behaviorRebateOrderEntity;
    private TaskEntity taskEntity;
}
