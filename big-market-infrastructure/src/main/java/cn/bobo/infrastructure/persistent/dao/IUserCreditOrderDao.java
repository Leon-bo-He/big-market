package cn.bobo.infrastructure.persistent.dao;

import cn.bobo.infrastructure.persistent.po.UserCreditOrder;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author BO HE
 */

@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserCreditOrderDao {
    void insert(UserCreditOrder userCreditOrderReq);
}
