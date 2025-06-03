package cn.bobo.test.domain.activity;

import cn.bobo.domain.activity.model.entity.SkuRechargeEntity;
import cn.bobo.domain.activity.model.entity.UnpaidActivityOrderEntity;
import cn.bobo.domain.activity.model.vo.OrderTradeTypeVO;
import cn.bobo.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.bobo.domain.activity.service.armory.IActivityArmory;
import cn.bobo.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityAccountQuotaServiceTest {


    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @Resource
    private IActivityArmory activityArmory;

    @Before
    public void setUp() {
        log.info("activity assemble：{}", activityArmory.assembleActivitySku(9011L));
    }

    @Test
    public void test_createSkuRechargeOrder_duplicate() {
        SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
        skuRechargeEntity.setUserId("bobo");
        skuRechargeEntity.setSku(9011L);
        // outBusinessNo 作为幂等仿重使用，同一个业务单号2次使用会抛出索引冲突 Duplicate entry '700091009111' for key 'uq_out_business_no' 确保唯一性。
        skuRechargeEntity.setOutBusinessNo("700091009119");
        skuRechargeEntity.setOrderTradeType(OrderTradeTypeVO.REBATE_NO_PAY_TRADE);
        UnpaidActivityOrderEntity order = raffleActivityAccountQuotaService.createOrder(skuRechargeEntity);
        log.info("Test results：{}", JSON.toJSONString(order));
    }

    @Test
    public void test_createSkuRechargeOrder() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            try {
                SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
                skuRechargeEntity.setUserId("bobo");
                skuRechargeEntity.setSku(9011L);
                // outBusinessNo 作为幂等仿重使用，同一个业务单号2次使用会抛出索引冲突 Duplicate entry '700091009111' for key 'uq_out_business_no' 确保唯一性。
                skuRechargeEntity.setOutBusinessNo(RandomStringUtils.randomNumeric(12));
                skuRechargeEntity.setOrderTradeType(OrderTradeTypeVO.REBATE_NO_PAY_TRADE);
                UnpaidActivityOrderEntity order = raffleActivityAccountQuotaService.createOrder(skuRechargeEntity);
                log.info("Test results：{}", JSON.toJSONString(order));
            } catch (AppException e) {
                log.warn(e.getInfo());
            }
        }

        new CountDownLatch(1).await();
    }

    @Test
    public void test_credit_pay_trade() {
        SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
        skuRechargeEntity.setUserId("bobo");
        skuRechargeEntity.setSku(9011L);
        // outBusinessNo 作为幂等仿重使用，同一个业务单号2次使用会抛出索引冲突 Duplicate entry '700091009111' for key 'uq_out_business_no' 确保唯一性。
        skuRechargeEntity.setOutBusinessNo("70009240609002");
        skuRechargeEntity.setOrderTradeType(OrderTradeTypeVO.CREDIT_PAY_TRADE);
        raffleActivityAccountQuotaService.createOrder(skuRechargeEntity)
        log.info("Test results：{}", JSON.toJSONString(order));
    }

}
