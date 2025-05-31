package cn.bobo.infrastructure.persistent.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BO HE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleActivityAccountDay {

    private final static SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");

    private String id;
    private String userId;
    private Long activityId;
    private String day;
    private Integer dayCount;
    private Integer dayCountSurplus;
    private Date createTime;
    private Date updateTime;

    public static String currentDay() {
        return dateFormatDay.format(new Date());
    }

}
