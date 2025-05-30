package cn.bobo.domain.rebate.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 任务状态值对象
 * @create 2024-04-06 10:00
 */
@Getter
@AllArgsConstructor
public enum TaskStateVO {

    CREATE("CREATE", "create task"),
    COMPLETED("COMPLETED", "send complete"),
    FAIL("FAIL", "send fail"),
    ;

    private final String code;
    private final String desc;

}
