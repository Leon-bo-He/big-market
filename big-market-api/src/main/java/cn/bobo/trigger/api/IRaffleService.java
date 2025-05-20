package cn.bobo.trigger.api;
import cn.bobo.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.bobo.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.bobo.trigger.api.dto.RaffleRequestDTO;
import cn.bobo.trigger.api.dto.RaffleResponseDTO;
import cn.bobo.types.model.Response;

import java.util.List;

/**
 * @author BO HE
 */
public interface IRaffleService {

    /**
     * strategy assemble interface
     *
     * @param strategyId
     * @return true if success, false if fail
     */
    Response<Boolean> strategyArmory(Long strategyId);

    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO requestDTO);


}
