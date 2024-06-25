package cn.bobo.infrastructure.persistent.dao;

import cn.bobo.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Bo He
 * @Description: Sweepstakes strategy DAO
 * @Date: 6/25/24
 */

@Mapper
public interface IStrategyDao {

    List<Strategy> queryStrategyList();
}
