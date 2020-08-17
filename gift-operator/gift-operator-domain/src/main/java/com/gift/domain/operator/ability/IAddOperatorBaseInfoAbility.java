package com.gift.domain.operator.ability;

import com.gift.core.ability.IAbility;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.AddOperatorBaseInfoDTO;
import com.gift.domain.operator.dto.AddOperatorBaseInfoOutDTO;

/**
 * <p>添加用户基本信息</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className IAddoperatorBaseInfoAbility
 * @sine 2020/3/24 15:24
 */
public interface IAddOperatorBaseInfoAbility extends IAbility {

    String ID="com.gift.domain.operator.ability.IAddOperatorBaseInfoAbility";

    ResponseDTO<AddOperatorBaseInfoOutDTO> executeAbility(RequestDTO<AddOperatorBaseInfoDTO> requestDTO);
}
