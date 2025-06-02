package cn.bobo.domain.award.service;

import cn.bobo.domain.award.model.entity.DispatchAwardEntity;
import cn.bobo.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author BO HE
 */
public interface IAwardService {

    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);

    void dispatchAward(DispatchAwardEntity dispatchAwardEntity);
}
