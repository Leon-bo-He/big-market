package cn.bobo.infrastructure.persistent.dao;

import cn.bobo.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Bo He
 * @Description: Award form Dao
 * @Date: 6/25/24
 */

@Mapper
public interface IAwardDao {

    List<Award> queryAwardList();
}
