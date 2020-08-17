package com.gift.domain.operator.ability;

import com.gift.core.ability.IAbility;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.UpdateOperatorBaseInfoDTO;
import com.gift.domain.operator.dto.UpdateOperatorBaseInfoOutDTO;

/**
 * <p>更新用户基本信息</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className IUpdateoperatorBaseInfoAbility
 * @sine 2020/3/24 15:34
 */
public interface IUpdateOperatorBaseInfoAbility extends IAbility {

    String ID="com.gift.domain.operator.ability.IUpdateOperatorBaseInfoAbility";

    ResponseDTO<UpdateOperatorBaseInfoOutDTO> executeAbility(RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO);
}
