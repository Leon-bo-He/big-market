package cn.bobo.infrastructure.persistent.dao;

import cn.bobo.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Bo He
 * @Description: Sweepstake strategy award details - probability, quantity, etc. DAO
 * @Date: 6/25/24
 */

@Mapper
public interface IStrategyAwardDao {

    List<StrategyAward> queryStrategyAwardList();
}
