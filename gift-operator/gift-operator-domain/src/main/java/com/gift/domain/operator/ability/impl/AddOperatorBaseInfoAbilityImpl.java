package com.gift.domain.operator.ability.impl;

import cn.hutool.core.date.DateUtil;
import com.gift.core.ability.AbstractAbility;
import com.gift.core.utils.SpringContextUtils;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.infrastructure.ability.dto.data.ReqHeaderData;
import com.gift.domain.infrastructure.ability.dto.data.RespHeaderData;
import com.gift.domain.infrastructure.util.DomainServiceUtil;
import com.gift.domain.operator.ability.IAddOperatorBaseInfoAbility;
import com.gift.domain.operator.constants.Constant;
import com.gift.domain.operator.dto.AddOperatorBaseInfoDTO;
import com.gift.domain.operator.dto.AddOperatorBaseInfoOutDTO;
import com.gift.domain.operator.error.ErrorCodeMsgEnum;
import com.gift.domain.operator.model.entity.CuOperatorInf;
import com.gift.domain.operator.model.entity.CuOperatorinfFlow;
import com.gift.domain.operator.model.service.ICuOperatorInfService;
import com.gift.domain.operator.model.service.ICuOperatorinfFlowService;
import com.gift.domain.sequence.factory.SequenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * <p>添加用户基本信息能力实现</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className AddoperatoroperatorBaseInfoAbilityImpl
 * @sine 2020/3/24 15:26
 */
@Slf4j
public class AddOperatorBaseInfoAbilityImpl extends AbstractAbility implements IAddOperatorBaseInfoAbility {
    @Override
    public ResponseDTO<AddOperatorBaseInfoOutDTO> executeAbility(RequestDTO<AddOperatorBaseInfoDTO> requestDTO) {
        //构造结果对象
        ResponseDTO<AddOperatorBaseInfoOutDTO> result = new ResponseDTO<>();
        //报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        //获取报文体
        AddOperatorBaseInfoDTO reqBody = requestDTO.getReqBody();



        try {
            //用户基本信息DAO
            ICuOperatorInfService cuoperatorInfService = SpringContextUtils.getBean(ICuOperatorInfService.class);
            //序列
            SequenceFactory sequenceFactory = SpringContextUtils.getBean(SequenceFactory.class);
            //用户基本信息变更流水DAO
            ICuOperatorinfFlowService cuOperatorInfFlowService = SpringContextUtils.getBean(ICuOperatorinfFlowService.class);
            CuOperatorInf cuoperatorInf = new CuOperatorInf();



            BeanUtils.copyProperties(reqBody, cuoperatorInf);

            cuoperatorInf.setUifStt("0");//用户状态0： 正常 1：已注销（全渠道）
            cuoperatorInf.setUifOpendate(DateUtil.format(LocalDateTime.now(), "yyyyMMdd"));
            //添加用户信息
            boolean save = cuoperatorInfService.save(cuoperatorInf);
            if (!save) {
                RespHeaderData.setErrorMsg(ErrorCodeMsgEnum.FAIL.msg());
                RespHeaderData.setErrorCode(ErrorCodeMsgEnum.FAIL.code());
            } else {
                CuOperatorinfFlow cuOperatorInfFlow = getCuOperatorinfFlow(requestDTO, reqBody, sequenceFactory);
                cuOperatorInfFlowService.save(cuOperatorInfFlow);
                RespHeaderData.setErrorMsg(ErrorCodeMsgEnum.SUCCESS.msg());
                RespHeaderData.setErrorCode(ErrorCodeMsgEnum.SUCCESS.code());
            }
        } catch (Exception e) {
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), e.getMessage());
        }
        result.setRespHeader(RespHeaderData);

        log.info("添加用户基本信息-能力实现,result={}", result);
        return result;
    }

    private CuOperatorinfFlow getCuOperatorinfFlow(RequestDTO<AddOperatorBaseInfoDTO> requestDTO, AddOperatorBaseInfoDTO reqBody, SequenceFactory sequenceFactory) {
        ReqHeaderData headerData = requestDTO.getReqHead();
        CuOperatorinfFlow cuOperatorInfFlow = new CuOperatorinfFlow();
        cuOperatorInfFlow.setUffFlowno(sequenceFactory.getSegmentDateId(Constant.OPERATOR_FLOW));
        cuOperatorInfFlow.setUffOpttype("1");//变更类型1.新增 2.更新用户状态3.更新用户基本信息（含权限）
        cuOperatorInfFlow.setUffOpttime(DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        cuOperatorInfFlow.setUffSysflowno(headerData.getGiftReqHeaderData().getSysReqNo());
        cuOperatorInfFlow.setUffGloflowno(headerData.getGiftReqHeaderData().getGlobalReqNo());
        cuOperatorInfFlow.setUffCstno(reqBody.getUifCstno());
        cuOperatorInfFlow.setUffUserno(reqBody.getUifUserno());
        cuOperatorInfFlow.setUffUsername(reqBody.getUifUsername());
        cuOperatorInfFlow.setUffStt("0");
        cuOperatorInfFlow.setUffUsersex(reqBody.getUifUsersex());
        cuOperatorInfFlow.setUffDepartment(reqBody.getUifDepartment());
        cuOperatorInfFlow.setUffCerttype(reqBody.getUifCerttype());
        cuOperatorInfFlow.setUffCertno(reqBody.getUifCertno());
        cuOperatorInfFlow.setUffJobtitle(reqBody.getUifJobtitle());
        cuOperatorInfFlow.setUffEmail(reqBody.getUifEmail());
        cuOperatorInfFlow.setUffRight(reqBody.getUifRight());
        cuOperatorInfFlow.setUffAuthlevel(reqBody.getUifAuthlevel());
        cuOperatorInfFlow.setUffOpendate(DateUtil.format(LocalDateTime.now(), "yyyyMMdd"));
        cuOperatorInfFlow.setUffCanceldate(reqBody.getUifCanceldate());
        return cuOperatorInfFlow;
    }


}
