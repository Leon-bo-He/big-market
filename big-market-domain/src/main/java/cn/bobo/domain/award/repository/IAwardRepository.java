package cn.bobo.domain.award.repository;

import cn.bobo.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @author BO HE
 */
public interface IAwardRepository {
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
