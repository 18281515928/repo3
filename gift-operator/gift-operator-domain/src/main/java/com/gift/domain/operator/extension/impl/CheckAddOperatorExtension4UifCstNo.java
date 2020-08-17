package com.gift.domain.operator.extension.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gift.core.utils.SpringContextUtils;
import com.gift.domain.infrastructure.ability.dto.DomainRequestParameterCheckDTO;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.data.RespHeaderData;
import com.gift.domain.infrastructure.ability.extension.IDomainParamCheckExtensionPoint;
import com.gift.domain.infrastructure.util.DomainServiceUtil;
import com.gift.domain.operator.dto.AddOperatorBaseInfoDTO;
import com.gift.domain.operator.error.ErrorCodeMsgEnum;
import com.gift.domain.operator.model.entity.CuOperatorInf;
import com.gift.domain.operator.model.service.ICuOperatorInfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * <p>检查用户存在--身份信息</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className CheckAddoperatorExtensionImpl
 * @sine 2020/3/30 10:22
 */
@Slf4j
public class CheckAddOperatorExtension4UifCstNo implements IDomainParamCheckExtensionPoint {
    @Override
    public <T> DomainRequestParameterCheckDTO requestParameterCheckBeforeOpt(RequestDTO<T> requestDTO) {

        AddOperatorBaseInfoDTO reqBody = (AddOperatorBaseInfoDTO) requestDTO.getReqBody();
        DomainRequestParameterCheckDTO responseDTO = new DomainRequestParameterCheckDTO();
        //获取DAO
        ICuOperatorInfService cuoperatorInfService = SpringContextUtils.getBean(ICuOperatorInfService.class);
        //默认检查通过
        responseDTO.setCode(ErrorCodeMsgEnum.SUCCESS.code());
        responseDTO.setMsg(ErrorCodeMsgEnum.SUCCESS.msg());
        try {
            //查询体
            LambdaUpdateWrapper<CuOperatorInf> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(StringUtils.hasText(reqBody.getUifCstno()), CuOperatorInf::getUifCstno, reqBody.getUifCstno())
            .eq(StringUtils.hasText(reqBody.getUifUserno()), CuOperatorInf::getUifUserno,reqBody.getUifUserno());//身份证类型
            //查询某个客户号下面得用户编号
            CuOperatorInf operatorInf = cuoperatorInfService.getOne(lambdaUpdateWrapper);
            if(operatorInf!=null){
                responseDTO.setCode(ErrorCodeMsgEnum.OPERATOR_ALREADY_EXIST.code());
                responseDTO.setMsg(ErrorCodeMsgEnum.OPERATOR_ALREADY_EXIST.msg());
            }

        }catch (Exception e){
//            DomainServiceUtil.transferUndeclaredThrowableException(e, new RespHeaderData(), ErrorCodeMsgEnum.FAIL.code(), e.getMessage());
            e.printStackTrace();
            responseDTO.setCode(ErrorCodeMsgEnum.FAIL.code());
            responseDTO.setMsg(e.getMessage());
        }


        log.info("参数检查域能力-查询用户基本信息扩展点-根据企业客户号查询,responseDTO={}", responseDTO);
        return responseDTO;
    }
}
