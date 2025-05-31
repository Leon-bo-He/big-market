package cn.bobo.trigger.api;
import cn.bobo.trigger.api.dto.*;
import cn.bobo.types.model.Response;

import java.util.List;

/**
 * @author BO HE
 */
public interface IRaffleStrategyService {

    /**
     * strategy assemble interface
     *
     * @param strategyId
     * @return true if success, false if fail
     */
    Response<Boolean> strategyArmory(Long strategyId);

    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    Response<List<RaffleStrategyRuleWeightResponseDTO>> queryRaffleStrategyRuleWeight(RaffleStrategyRuleWeightRequestDTO request);

    Response<RaffleStrategyResponseDTO> randomRaffle(RaffleStrategyRequestDTO requestDTO);


}
