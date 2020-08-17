package com.gift.domain.operator.ability.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gift.core.ability.AbstractAbility;
import com.gift.core.utils.SpringContextUtils;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.infrastructure.ability.dto.data.ReqHeaderData;
import com.gift.domain.infrastructure.ability.dto.data.RespHeaderData;
import com.gift.domain.infrastructure.util.DomainServiceUtil;
import com.gift.domain.operator.ability.IUpdateOperatorBaseInfoAbility;
import com.gift.domain.operator.constants.Constant;
import com.gift.domain.operator.dto.UpdateOperatorBaseInfoDTO;
import com.gift.domain.operator.dto.UpdateOperatorBaseInfoOutDTO;
import com.gift.domain.operator.error.ErrorCodeMsgEnum;
import com.gift.domain.operator.model.entity.CuOperatorInf;
import com.gift.domain.operator.model.entity.CuOperatorinfFlow;
import com.gift.domain.operator.model.service.ICuOperatorInfService;
import com.gift.domain.operator.model.service.ICuOperatorinfFlowService;
import com.gift.domain.sequence.factory.SequenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>更新用户基本信息能力实现</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className UpdateoperatorBaseInfoAbilityImpl
 * @sine 2020/3/24 15:38
 */
@Slf4j
public class UpdateOperatorBaseInfoAbilityImpl extends AbstractAbility implements IUpdateOperatorBaseInfoAbility {
    @Override
    public ResponseDTO<UpdateOperatorBaseInfoOutDTO> executeAbility(RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO) {
        //构造结果对象
        ResponseDTO<UpdateOperatorBaseInfoOutDTO> result = new ResponseDTO<>();
        //报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);

        UpdateOperatorBaseInfoDTO reqBody = requestDTO.getReqBody();
        try {
            //获取DAO
            ICuOperatorInfService cuoperatorInfService = SpringContextUtils.getBean(ICuOperatorInfService.class);
            SequenceFactory sequenceFactory = SpringContextUtils.getBean(SequenceFactory.class);
            ICuOperatorinfFlowService cuOperatorInfFlowService = SpringContextUtils.getBean(ICuOperatorinfFlowService.class);

            LambdaUpdateWrapper<CuOperatorInf> lambdaQueryWrapper = new LambdaUpdateWrapper<>();

            //构造修改条件
            lambdaQueryWrapper
                    .eq(CuOperatorInf::getUifCstno, reqBody.getUifCstno())
                    .eq(CuOperatorInf::getUifUserno, reqBody.getUifUserno())
                    .set(StringUtils.hasText(reqBody.getUifUsername()), CuOperatorInf::getUifUsername, reqBody.getUifUsername())//用户姓名
                    .set(StringUtils.hasText(reqBody.getUifUsersex()), CuOperatorInf::getUifUsersex, reqBody.getUifUsersex())//用户性别
                    .set(StringUtils.hasText(reqBody.getUifDepartment()), CuOperatorInf::getUifDepartment, reqBody.getUifDepartment())//所属部门
                    .set(StringUtils.hasText(reqBody.getUifCerttype()), CuOperatorInf::getUifCerttype, reqBody.getUifCerttype())//证件类型
                    .set(StringUtils.hasText(reqBody.getUifCertno()), CuOperatorInf::getUifCertno, reqBody.getUifCertno())//证件号码
                    .set(StringUtils.hasText(reqBody.getUifJobtitle()), CuOperatorInf::getUifJobtitle, reqBody.getUifJobtitle())//职位
                    .set(StringUtils.hasText(reqBody.getUifEmail()), CuOperatorInf::getUifEmail, reqBody.getUifEmail())//电子邮件
                    .set(StringUtils.hasText(reqBody.getUifRight()), CuOperatorInf::getUifRight, reqBody.getUifRight())//用户权限
                    .set(StringUtils.hasText(reqBody.getUifAuthlevel()), CuOperatorInf::getUifAuthlevel, reqBody.getUifAuthlevel())//授权级别（0-5）
                    .set(StringUtils.hasText(reqBody.getUifPhone()), CuOperatorInf::getUifPhone, reqBody.getUifPhone());//手机号

            boolean update = cuoperatorInfService.update(lambdaQueryWrapper);
            RespHeaderData.setErrorCode(ErrorCodeMsgEnum.SUCCESS.code());
            if (update) {
                //更新操作流水
                CuOperatorinfFlow cuOperatorInfFlow = getCuOperatorinfFlow(requestDTO, reqBody, sequenceFactory);
                cuOperatorInfFlowService.save(cuOperatorInfFlow);
                RespHeaderData.setErrorMsg(ErrorCodeMsgEnum.SUCCESS.msg());
            } else {
                RespHeaderData.setErrorMsg("更新失败");
            }
        } catch (Exception e) {
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), e.getMessage());
        }
        result.setRespHeader(RespHeaderData);

        log.info("更新用户基本信息能力实现-能力实现,result={}", result);
        return result;
    }
    private CuOperatorinfFlow getCuOperatorinfFlow(RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO, UpdateOperatorBaseInfoDTO reqBody, SequenceFactory sequenceFactory) {
        ReqHeaderData headerData = requestDTO.getReqHead();
        CuOperatorinfFlow cuOperatorInfFlow = new CuOperatorinfFlow();
        cuOperatorInfFlow.setUffFlowno(sequenceFactory.getSegmentDateId(Constant.OPERATOR_FLOW));
        cuOperatorInfFlow.setUffOpttype("3");//变更类型1.新增 2.更新用户状态3.更新用户基本信息（含权限）
        cuOperatorInfFlow.setUffOpttime(DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        cuOperatorInfFlow.setUffSysflowno(headerData.getGiftReqHeaderData().getSysReqNo());
        cuOperatorInfFlow.setUffGloflowno(headerData.getGiftReqHeaderData().getGlobalReqNo());
        cuOperatorInfFlow.setUffCstno(reqBody.getUifCstno());
        cuOperatorInfFlow.setUffUserno(reqBody.getUifUserno());
        cuOperatorInfFlow.setUffUsername(reqBody.getUifUsername());
//        cuOperatorInfFlow.setUffStt("0");
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
