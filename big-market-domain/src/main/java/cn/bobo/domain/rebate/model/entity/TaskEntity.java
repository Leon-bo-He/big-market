package cn.bobo.domain.rebate.model.entity;

import cn.bobo.domain.rebate.event.SendRebateMessageEvent;
import cn.bobo.domain.rebate.model.vo.TaskStateVO;
import cn.bobo.types.event.BaseEvent;
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
public class TaskEntity {

    private String userId;
    private String topic;
    private String messageId;
    private BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> message;
    private TaskStateVO state;
}
