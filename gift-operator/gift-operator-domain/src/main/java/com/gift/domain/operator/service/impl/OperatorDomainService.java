package com.gift.domain.operator.service.impl;

import com.gift.core.api.model.BizProduct;
import com.gift.core.api.model.BizScenario;
import com.gift.core.api.model.RequestObject;
import com.gift.core.component.BusinessManager;
import com.gift.core.utils.SpringContextUtils;
import com.gift.domain.infrastructure.ability.IDomainParamCheckAbility;
import com.gift.domain.infrastructure.ability.dto.DomainRequestParameterCheckDTO;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.infrastructure.ability.dto.data.RespHeaderData;
import com.gift.domain.infrastructure.util.DomainServiceUtil;
import com.gift.domain.operator.ability.*;
import com.gift.domain.operator.dto.*;
import com.gift.domain.operator.error.ErrorCodeMsgEnum;
import com.gift.domain.operator.service.IOperatorDomain;
import org.springframework.stereotype.Component;

/**
 * <p>域服务</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className operatorDomainService
 * @sine 2020/3/24 10:43
 */
@Component
public class OperatorDomainService implements IOperatorDomain {

    @Override
    public ResponseDTO<QueryAllOperatorOutDTO> queryALLOperator(RequestDTO<QueryAllOperatorDTO> requestDTO) {
        //定义响应报文
        ResponseDTO<QueryAllOperatorOutDTO> result = new ResponseDTO<>();
        //定义响应报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        BusinessManager manager = SpringContextUtils.getBean(BusinessManager.class);
        //通过两码一号构建产品对象
        BizScenario bizProduct = DomainServiceUtil.createScenarioObj(requestDTO);
        //创建RequestObject对象
        RequestObject abilityReqObj = new RequestObject(DOMAIN_CODE, bizProduct);

        try {
            //查找能力实现
            IQueryAllOperatorAbility ability = manager.findAbility(IQueryAllOperatorAbility.ID, IQueryAllOperatorAbility.class, abilityReqObj);
            result = ability.executeAbility(requestDTO);
            DomainServiceUtil.updateResHeaderData(RespHeaderData, result.getRespHeader().getErrorCode(), result.getRespHeader().getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), ErrorCodeMsgEnum.FAIL.msg());
            result.setRespBody(null);
        }
        result.setRespHeader(RespHeaderData);

        return result;
    }

    @Override
    public ResponseDTO<QueryOperatorBaseInfoOutDTO> queryOperatorBaseInfo(RequestDTO<QueryOperatorBaseInfoDTO> requestDTO) {
        //定义响应报文
        ResponseDTO<QueryOperatorBaseInfoOutDTO> result = new ResponseDTO<>();
        //定义响应报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        BusinessManager manager = SpringContextUtils.getBean(BusinessManager.class);
        //通过两码一号构建产品对象
        BizScenario bizProduct = DomainServiceUtil.createScenarioObj(requestDTO);
        //创建RequestObject对象
        RequestObject abilityReqObj = new RequestObject(DOMAIN_CODE, bizProduct);

        try {
            //查找能力实现
            IQueryOperatorBaseInfoAbility ability = manager.findAbility(IQueryOperatorBaseInfoAbility.ID, IQueryOperatorBaseInfoAbility.class, abilityReqObj);
            result = ability.executeAbility(requestDTO);
            DomainServiceUtil.updateResHeaderData(RespHeaderData, result.getRespHeader().getErrorCode(), result.getRespHeader().getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), ErrorCodeMsgEnum.FAIL.msg());
            result.setRespBody(null);
        }
        result.setRespHeader(RespHeaderData);

        return result;
    }

    @Override
    public ResponseDTO<AddOperatorBaseInfoOutDTO> addOperatorBaseInfo(RequestDTO<AddOperatorBaseInfoDTO> requestDTO) {
        //定义响应报文
        ResponseDTO<AddOperatorBaseInfoOutDTO> result = new ResponseDTO<>();
        //定义响应报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        BusinessManager manager = SpringContextUtils.getBean(BusinessManager.class);
        //通过两码一号构建产品对象
        BizScenario bizProduct = DomainServiceUtil.createScenarioObj(requestDTO);
        //创建RequestObject对象
        RequestObject abilityReqObj = new RequestObject(DOMAIN_CODE, bizProduct);
        try {
            //参数检查
            IDomainParamCheckAbility paramCheckAbility = manager.findAbility(IDomainParamCheckAbility.ID, IDomainParamCheckAbility.class, abilityReqObj);
            DomainRequestParameterCheckDTO parameterCheckDTO = paramCheckAbility.requestParameterCheckBeforeOpt(requestDTO, DOMAIN_CODE);
            if(!"0".equals(parameterCheckDTO.getCode())){
                DomainServiceUtil.updateResHeaderData(RespHeaderData, parameterCheckDTO.getCode(),parameterCheckDTO.getMsg());
                result.setRespHeader(RespHeaderData);
                return result;
            }
            //查找能力实现
            IAddOperatorBaseInfoAbility ability = manager.findAbility(IAddOperatorBaseInfoAbility.ID, IAddOperatorBaseInfoAbility.class, abilityReqObj);
            result = ability.executeAbility(requestDTO);
            DomainServiceUtil.updateResHeaderData(RespHeaderData, result.getRespHeader().getErrorCode(), result.getRespHeader().getErrorMsg());

        } catch (Exception e) {
            e.printStackTrace();
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), ErrorCodeMsgEnum.FAIL.msg());
            result.setRespBody(null);
        }
        result.setRespHeader(RespHeaderData);

        return result;
    }



    @Override
    public ResponseDTO<UpdateOperatorBaseInfoOutDTO> updateOperatorBaseInfo(RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO) {
        //定义响应报文
        ResponseDTO<UpdateOperatorBaseInfoOutDTO> result = new ResponseDTO<>();
        //定义响应报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        BusinessManager manager = SpringContextUtils.getBean(BusinessManager.class);
        //通过两码一号构建产品对象
        BizScenario bizProduct = DomainServiceUtil.createScenarioObj(requestDTO);
        //创建RequestObject对象
        RequestObject abilityReqObj = new RequestObject(DOMAIN_CODE, bizProduct);
        UpdateOperatorBaseInfoDTO reqBody = requestDTO.getReqBody();
        try {
            //查找能力实现
            IUpdateOperatorBaseInfoAbility ability = manager.findAbility(IUpdateOperatorBaseInfoAbility.ID, IUpdateOperatorBaseInfoAbility.class, abilityReqObj);
            result = ability.executeAbility(requestDTO);
            //保存流水--1为新增用户流水
            DomainServiceUtil.updateResHeaderData(RespHeaderData, result.getRespHeader().getErrorCode(), result.getRespHeader().getErrorMsg());

        } catch (Exception e) {
            e.printStackTrace();
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), ErrorCodeMsgEnum.FAIL.msg());
            result.setRespBody(null);
        }
        result.setRespHeader(RespHeaderData);

        return result;
    }

    @Override
    public ResponseDTO<UpdateOperatorStatusOutDTO> updateOperatorStatus(RequestDTO<UpdateOperatorStatusDTO> requestDTO) {
        //定义响应报文
        ResponseDTO<UpdateOperatorStatusOutDTO> result = new ResponseDTO<>();
        //定义响应报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        BusinessManager manager = SpringContextUtils.getBean(BusinessManager.class);
        //通过两码一号构建产品对象
        BizScenario bizProduct = DomainServiceUtil.createScenarioObj(requestDTO);
        //创建RequestObject对象
        RequestObject abilityReqObj = new RequestObject(DOMAIN_CODE, bizProduct);
        UpdateOperatorStatusDTO reqBody = requestDTO.getReqBody();
        try {
            //查找能力实现
            IUpdateOperatorStatusAbility ability = manager.findAbility(IUpdateOperatorStatusAbility.ID, IUpdateOperatorStatusAbility.class, abilityReqObj);
            result = ability.executeAbility(requestDTO);
            //保存流水--1为新增用户流水
            DomainServiceUtil.updateResHeaderData(RespHeaderData, result.getRespHeader().getErrorCode(), result.getRespHeader().getErrorMsg());

        } catch (Exception e) {
            e.printStackTrace();
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), ErrorCodeMsgEnum.FAIL.msg());
            result.setRespBody(null);
        }
        result.setRespHeader(RespHeaderData);

        return result;
    }
}
