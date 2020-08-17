package com.gift.domain.operator.controller;

import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.operator.dto.*;
import com.gift.domain.operator.service.IOperatorDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>用户域--应用服务</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className operatorController
 * @sine 2020/3/24 11:52
 */
@RestController
public class OperatorController {

    @Autowired
    IOperatorDomain operatorDomain;


    @PostMapping("/queryoperatorBaseInfo")
    public ResponseDTO<QueryOperatorBaseInfoOutDTO> queryOperatorBaseInfo(@RequestBody RequestDTO<QueryOperatorBaseInfoDTO> requestDTO){
        return operatorDomain.queryOperatorBaseInfo(requestDTO);
    }

    @PostMapping("/queryAllOperator")
    public ResponseDTO<QueryAllOperatorOutDTO> queryAllOperator(@RequestBody RequestDTO<QueryAllOperatorDTO> requestDTO){
        return operatorDomain.queryALLOperator(requestDTO);
    }

    @PostMapping("/addOperatorBaseInfo")
    public ResponseDTO<AddOperatorBaseInfoOutDTO> addOperatorBaseInfo(@RequestBody RequestDTO<AddOperatorBaseInfoDTO> requestDTO){
        return operatorDomain.addOperatorBaseInfo(requestDTO);
    }

    @PostMapping("/updateOperatorBaseInfo")
    public ResponseDTO<UpdateOperatorBaseInfoOutDTO> updateOperatorBaseInfo(@RequestBody RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO){
        return operatorDomain.updateOperatorBaseInfo(requestDTO);
    }

    @PostMapping("/updateOperatorStatus")
    public ResponseDTO<UpdateOperatorStatusOutDTO> updateOperatorStatus(@RequestBody RequestDTO<UpdateOperatorStatusDTO> requestDTO){
        return operatorDomain.updateOperatorStatus(requestDTO);
    }
}
