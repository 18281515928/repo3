package com.gift.domain.operator.service;

import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.*;

/**
 * <p>用户域</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className IoperatorDomain
 * @sine 2020/3/24 10:45
 */
public interface IOperatorDomain {

    String DOMAIN_CODE = "gift-operator";

    /**
     * 查询企业所有用户
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<QueryAllOperatorOutDTO> queryALLOperator(RequestDTO<QueryAllOperatorDTO> requestDTO);

    /**
     * 查询用户基本信息
     * @param requestDTO
     * @return
     */
    ResponseDTO<QueryOperatorBaseInfoOutDTO> queryOperatorBaseInfo(RequestDTO<QueryOperatorBaseInfoDTO> requestDTO);


    /**
     * 添加用户基本信息
     * @param requestDTO
     * @return
     */
    ResponseDTO<AddOperatorBaseInfoOutDTO> addOperatorBaseInfo(RequestDTO<AddOperatorBaseInfoDTO> requestDTO);

    /**
     * 更新用户基本信息
     * @param requestDTO
     * @return
     */
    ResponseDTO<UpdateOperatorBaseInfoOutDTO> updateOperatorBaseInfo(RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO);

    /**
     * 更新用户状态
     * @param requestDTO
     * @return
     */
    ResponseDTO<UpdateOperatorStatusOutDTO> updateOperatorStatus(RequestDTO<UpdateOperatorStatusDTO> requestDTO);




}
