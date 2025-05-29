package cn.bobo.domain.award.model.entity;

import cn.bobo.domain.award.event.SendAwardMessageEvent;
import cn.bobo.domain.award.model.vo.TaskStateVO;
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
    private BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> message;
    private TaskStateVO state;
}
