package com.gift.domain.operator.ability;

import com.gift.core.ability.IAbility;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.QueryOperatorBaseInfoDTO;
import com.gift.domain.operator.dto.QueryOperatorBaseInfoOutDTO;

/**
 * <p>查询用户基本信息能力</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className IQueryoperatorBaseInfoAbility
 * @sine 2020/3/24 11:14
 */
public interface IQueryOperatorBaseInfoAbility extends IAbility {

    String ID="com.gift.domain.operator.ability.IQueryOperatorBaseInfoAbility";

    ResponseDTO<QueryOperatorBaseInfoOutDTO> executeAbility(RequestDTO<QueryOperatorBaseInfoDTO> requestDTO);
}
