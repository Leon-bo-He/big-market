package cn.bobo.domain.award.service.dispatch.impl;

import cn.bobo.domain.award.model.aggregate.GiveOutPrizesAggregate;
import cn.bobo.domain.award.model.entity.DispatchAwardEntity;
import cn.bobo.domain.award.model.entity.UserAwardRecordEntity;
import cn.bobo.domain.award.model.entity.UserCreditAwardEntity;
import cn.bobo.domain.award.model.vo.AwardStateVO;
import cn.bobo.domain.award.repository.IAwardRepository;
import cn.bobo.domain.award.service.dispatch.IDispatchAward;
import cn.bobo.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author BO HE
 */

@Component("user_credit_random")
public class UserCreditRandomAward implements IDispatchAward {

    @Resource
    private IAwardRepository repository;

    @Override
    public void giveOutPrizes(DispatchAwardEntity dispatchAwardEntity) {
        Integer awardId = dispatchAwardEntity.getAwardId();
        String awardConfig = dispatchAwardEntity.getAwardConfig();
        if (StringUtils.isBlank(awardConfig)) {
            awardConfig = repository.queryAwardConfig(awardId);
        }

        String[] creditRange = awardConfig.split(Constants.COMMA);
        if (creditRange.length != 2) {
            throw new RuntimeException("award_config 「" + awardConfig + "」is not a range value. such as 1,100");
        }

        BigDecimal creditAmount = generateRandom(new BigDecimal(creditRange[0]), new BigDecimal(creditRange[1]));

        UserAwardRecordEntity userAwardRecordEntity = GiveOutPrizesAggregate.buildDispatchUserAwardRecordEntity(
                dispatchAwardEntity.getUserId(),
                dispatchAwardEntity.getOrderId(),
                dispatchAwardEntity.getAwardId(),
                AwardStateVO.COMPLETED
        );

        UserCreditAwardEntity userCreditAwardEntity = GiveOutPrizesAggregate.buildUserCreditAwardEntity(dispatchAwardEntity.getUserId(), creditAmount);

        GiveOutPrizesAggregate giveOutPrizesAggregate = new GiveOutPrizesAggregate();
        giveOutPrizesAggregate.setUserId(dispatchAwardEntity.getUserId());
        giveOutPrizesAggregate.setUserAwardRecordEntity(userAwardRecordEntity);
        giveOutPrizesAggregate.setUserCreditAwardEntity(userCreditAwardEntity);

        repository.saveGiveOutPrizesAggregate(giveOutPrizesAggregate);
    }

    private BigDecimal generateRandom(BigDecimal min, BigDecimal max) {
        if (min.equals(max)) return min;
        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.round(new MathContext(3));
    }
}
