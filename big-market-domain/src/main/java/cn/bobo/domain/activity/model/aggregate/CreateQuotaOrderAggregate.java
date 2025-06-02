package cn.bobo.domain.activity.model.aggregate;

import cn.bobo.domain.activity.model.entity.ActivityOrderEntity;
import cn.bobo.domain.activity.model.vo.RaffleActivityOrderStateVO;
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
public class CreateQuotaOrderAggregate {

    private String userId;
    private Long activityId;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
    private ActivityOrderEntity activityOrderEntity;

    public void setOrderState(RaffleActivityOrderStateVO orderState) {
        this.activityOrderEntity.setState(orderState);
    }

}
