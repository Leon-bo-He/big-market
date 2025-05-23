package cn.bobo.domain.activity.model.aggregate;

import cn.bobo.domain.activity.model.entity.ActivityAccountEntity;
import cn.bobo.domain.activity.model.entity.ActivityOrderEntity;
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
public class CreateOrderAggregate {

    private ActivityAccountEntity activityAccountEntity;
    private ActivityOrderEntity activityOrderEntity;
}
