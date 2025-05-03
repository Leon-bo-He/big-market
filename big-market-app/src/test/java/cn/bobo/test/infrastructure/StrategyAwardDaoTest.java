package cn.bobo.test.infrastructure;

import cn.bobo.infrastructure.persistent.dao.IAwardDao;
import cn.bobo.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.bobo.infrastructure.persistent.po.Award;
import cn.bobo.infrastructure.persistent.po.StrategyAward;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Bo He
 * @Description: Strategy Award persistent layer test
 * @Date: 6/25/24
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyAwardDaoTest {

    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Test
    public void test_queryStrategyAwardList() {
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardList();
        log.info("StrategyAwards: {}", JSON.toJSONString(strategyAwards));
    }
}
