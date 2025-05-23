package cn.bobo.domain.activity.model.entity;

import cn.bobo.domain.activity.model.vo.ActivityStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {

    private Long activityId;
    private String activityName;
    private String activityDesc;
    private Date beginDateTime;
    private Date endDateTime;
    private Long activityCountId;
    private Long strategyId;
    private ActivityStateVO state;


}
