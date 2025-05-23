package cn.bobo.infrastructure.persistent.dao;

import cn.bobo.infrastructure.persistent.po.RaffleActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author BO HE
 */

@Mapper
public interface IRaffleActivityCountDao {

    RaffleActivityCount queryRaffleActivityCountByActivityCountId(Long activityCountId);


}
