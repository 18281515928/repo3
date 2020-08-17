package com.gift.domain.operator.ability;

import com.gift.core.ability.IAbility;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.QueryAllOperatorDTO;
import com.gift.domain.operator.dto.QueryAllOperatorOutDTO;

/**
 * <p>查询所有用户信息列表的能力</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className IQueryAlloperatorAbility
 * @sine 2020/3/24 14:51
 */
public interface IQueryAllOperatorAbility extends IAbility {

    String ID="com.gift.domain.operator.ability.IQueryAllOperatorAbility";

    ResponseDTO<QueryAllOperatorOutDTO> executeAbility(RequestDTO<QueryAllOperatorDTO> requestDTO);
}
