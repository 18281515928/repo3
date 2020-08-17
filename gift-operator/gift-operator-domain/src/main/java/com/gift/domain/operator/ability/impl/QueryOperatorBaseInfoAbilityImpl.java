package com.gift.domain.operator.ability.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gift.core.ability.AbstractAbility;
import com.gift.core.utils.SpringContextUtils;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.infrastructure.ability.dto.data.RespHeaderData;
import com.gift.domain.infrastructure.util.DomainServiceUtil;
import com.gift.domain.operator.ability.IQueryOperatorBaseInfoAbility;
import com.gift.domain.operator.dto.QueryOperatorBaseInfoDTO;
import com.gift.domain.operator.dto.QueryOperatorBaseInfoOutDTO;
import com.gift.domain.operator.error.ErrorCodeMsgEnum;
import com.gift.domain.operator.model.entity.CuOperatorInf;
import com.gift.domain.operator.model.service.ICuOperatorInfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * <p>查询用户基本信息能力实现</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className QueryoperatorBaseInfoAbilityImpl
 * @sine 2020/3/24 11:18
 */
@Slf4j
@Component
public class QueryOperatorBaseInfoAbilityImpl extends AbstractAbility implements IQueryOperatorBaseInfoAbility {



    @Override
    public ResponseDTO<QueryOperatorBaseInfoOutDTO> executeAbility(RequestDTO<QueryOperatorBaseInfoDTO> requestDTO) {

       //构造结果对象
        ResponseDTO<QueryOperatorBaseInfoOutDTO> result = new ResponseDTO<>();
        //报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        //报文体
        QueryOperatorBaseInfoDTO reqBody = requestDTO.getReqBody();

        try {
            //获取DAO
            ICuOperatorInfService cuoperatorInfService = SpringContextUtils.getBean(ICuOperatorInfService.class);
            //查询体
            LambdaUpdateWrapper<CuOperatorInf> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(CuOperatorInf::getUifCstno,reqBody.getUifCstNo())//客户号
                    .eq(CuOperatorInf::getUifUserno,reqBody.getUifoperatorNo());//用户编号

            //查询某个客户号下面得用户编号
            CuOperatorInf operatorInf = cuoperatorInfService.getOne(lambdaUpdateWrapper);
            RespHeaderData.setErrorCode(ErrorCodeMsgEnum.SUCCESS.code());
            if (operatorInf == null) {
                RespHeaderData.setErrorMsg(ErrorCodeMsgEnum.operator_NOT_EXIST.msg());
            } else {
                RespHeaderData.setErrorMsg(ErrorCodeMsgEnum.SUCCESS.msg());
                QueryOperatorBaseInfoOutDTO queryOperatorBaseInfoOutDTO = new QueryOperatorBaseInfoOutDTO();
                BeanUtils.copyProperties(operatorInf, queryOperatorBaseInfoOutDTO);
                result.setRespBody(queryOperatorBaseInfoOutDTO);
            }
        } catch (Exception e) {
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), e.getMessage());
        }
        result.setRespHeader(RespHeaderData);


        log.info("查询用户基本信息-能力实现,result={}", result);
        return result;
    }
}
