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
import com.gift.domain.operator.ability.IUpdateOperatorStatusAbility;
import com.gift.domain.operator.constants.Constant;
import com.gift.domain.operator.dto.UpdateOperatorStatusDTO;
import com.gift.domain.operator.dto.UpdateOperatorStatusOutDTO;
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
 * <p>更新用户状态的能力实现</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className UpdateoperatorStatusAbilityImpl
 * @sine 2020/3/24 15:56
 */
@Slf4j
public class UpdateOperatorStatusAbilityImpl extends AbstractAbility implements IUpdateOperatorStatusAbility {
    @Override
    public ResponseDTO<UpdateOperatorStatusOutDTO> executeAbility(RequestDTO<UpdateOperatorStatusDTO> requestDTO) {
        //构造结果对象
        ResponseDTO<UpdateOperatorStatusOutDTO> result = new ResponseDTO<>();
        //报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);

        UpdateOperatorStatusDTO reqBody = requestDTO.getReqBody();
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
                    .set(StringUtils.hasText(reqBody.getUifStt()), CuOperatorInf::getUifStt, reqBody.getUifStt());//用户状态

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

        log.info("更新用户状态的能力实现-能力实现,result={}", result);
        return result;
    }

    private CuOperatorinfFlow getCuOperatorinfFlow(RequestDTO<UpdateOperatorStatusDTO> requestDTO, UpdateOperatorStatusDTO reqBody, SequenceFactory sequenceFactory) {
        ReqHeaderData headerData = requestDTO.getReqHead();
        CuOperatorinfFlow cuOperatorInfFlow = new CuOperatorinfFlow();
        cuOperatorInfFlow.setUffFlowno(sequenceFactory.getSegmentDateId(Constant.OPERATOR_FLOW));
        cuOperatorInfFlow.setUffOpttype("2");//变更类型1.新增 2.更新用户状态3.更新用户基本信息（含权限）
        cuOperatorInfFlow.setUffOpttime(DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        cuOperatorInfFlow.setUffSysflowno(headerData.getGiftReqHeaderData().getSysReqNo());
        cuOperatorInfFlow.setUffGloflowno(headerData.getGiftReqHeaderData().getGlobalReqNo());
        cuOperatorInfFlow.setUffCstno(reqBody.getUifCstno());
        cuOperatorInfFlow.setUffUserno(reqBody.getUifUserno());
        cuOperatorInfFlow.setUffStt(reqBody.getUifStt());
        if ("1".equals(reqBody.getUifStt())) {
            cuOperatorInfFlow.setUffCanceldate(DateUtil.format(LocalDateTime.now(), "yyyyMMdd"));
        }

        return cuOperatorInfFlow;
    }
}
