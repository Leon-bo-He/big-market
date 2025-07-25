package cn.bobo.domain.award.model.aggregate;

import cn.bobo.domain.award.model.entity.TaskEntity;
import cn.bobo.domain.award.model.entity.UserAwardRecordEntity;
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
public class UserAwardRecordAggregate {

    private TaskEntity taskEntity;
    private UserAwardRecordEntity userAwardRecordEntity;
}
