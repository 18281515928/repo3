package com.gift.domain.operator.ability;

import com.gift.core.ability.IAbility;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.UpdateOperatorStatusDTO;
import com.gift.domain.operator.dto.UpdateOperatorStatusOutDTO;

/**
 * <p>修改用户状态</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className IUpdateoperatorStatusAbility
 * @sine 2020/3/24 15:53
 */
public interface IUpdateOperatorStatusAbility extends IAbility {

    String ID="com.gift.domain.operator.ability.IUpdateOperatorStatusAbility";

    ResponseDTO<UpdateOperatorStatusOutDTO> executeAbility(RequestDTO<UpdateOperatorStatusDTO> requestDTO);
}
