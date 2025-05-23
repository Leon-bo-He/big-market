package cn.bobo.domain.activity.service;

import cn.bobo.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * @author BO HE
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity {

    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }

}
