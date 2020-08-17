package com.gift.domain.operator.ability.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gift.core.ability.AbstractAbility;
import com.gift.core.utils.SpringContextUtils;
import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.infrastructure.ability.dto.data.RespHeaderData;
import com.gift.domain.infrastructure.util.DomainServiceUtil;
import com.gift.domain.operator.ability.IQueryAllOperatorAbility;
import com.gift.domain.operator.dto.CuOperator;
import com.gift.domain.operator.dto.QueryAllOperatorDTO;
import com.gift.domain.operator.dto.QueryAllOperatorOutDTO;
import com.gift.domain.operator.error.ErrorCodeMsgEnum;
import com.gift.domain.operator.model.entity.CuOperatorInf;
import com.gift.domain.operator.model.service.ICuOperatorInfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>分页查询所有的用户信息列表的能力实现</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className QueryAlloperatorAbilityImpl
 * @sine 2020/3/24 14:56
 */
@Slf4j
public class QueryAllOperatorAbilityImpl extends AbstractAbility implements IQueryAllOperatorAbility {
    @Override
    public ResponseDTO<QueryAllOperatorOutDTO> executeAbility(RequestDTO<QueryAllOperatorDTO> requestDTO) {
        ResponseDTO<QueryAllOperatorOutDTO> result = new ResponseDTO<>();
        //报文头
        RespHeaderData RespHeaderData = DomainServiceUtil.createResHeaderData(requestDTO);
        //获取报文体--查询参数
        QueryAllOperatorDTO reqBody = requestDTO.getReqBody();
        try {
            //获取DAO
            ICuOperatorInfService cuCstInfService = SpringContextUtils.getBean(ICuOperatorInfService.class);
            LambdaQueryWrapper<CuOperatorInf> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            //构造查询条件
            lambdaQueryWrapper
                    .like(StringUtils.hasText(reqBody.getUifCstno()), CuOperatorInf::getUifCstno, reqBody.getUifCstno())//客户号模糊查询
                    .like(StringUtils.hasText(reqBody.getUifUserno()), CuOperatorInf::getUifUserno, reqBody.getUifUserno())//用户编号
                    .like(StringUtils.hasText(reqBody.getUifUsername()), CuOperatorInf::getUifUsername, reqBody.getUifUsername())//中文名称模糊查询
                    .eq(StringUtils.hasText(reqBody.getUifStt()), CuOperatorInf::getUifStt, reqBody.getUifStt())//用户状态查询
                    .eq(StringUtils.hasText(reqBody.getUifUsersex()), CuOperatorInf::getUifUsersex, reqBody.getUifUsersex())// 用户性别查询
                    .like(StringUtils.hasText(reqBody.getUifDepartment()), CuOperatorInf::getUifDepartment, reqBody.getUifDepartment())//部门查询
                    .eq(StringUtils.hasText(reqBody.getUifCerttype()), CuOperatorInf::getUifCerttype, reqBody.getUifCerttype())//证件类型
                    .eq(StringUtils.hasText(reqBody.getUifCertno()), CuOperatorInf::getUifCertno, reqBody.getUifCertno())
                    .like(StringUtils.hasText(reqBody.getUifJobtitle()), CuOperatorInf::getUifJobtitle, reqBody.getUifJobtitle())
                    .like(StringUtils.hasText(reqBody.getUifEmail()), CuOperatorInf::getUifEmail, reqBody.getUifEmail())//证件号码查询
                    .like(StringUtils.hasText(reqBody.getUifRight()),CuOperatorInf::getUifRight,reqBody.getUifRight());//用户权限（10位二进制：查询、录入、授权、复核、管理。如：10000表示只有录入权限）

            List<CuOperatorInf> cuCstInfs = cuCstInfService.list(lambdaQueryWrapper);
            //处理分页数据
            RespHeaderData.setErrorCode(ErrorCodeMsgEnum.SUCCESS.code());
            RespHeaderData.setErrorMsg(ErrorCodeMsgEnum.SUCCESS.msg());
            result.setRespHeader(RespHeaderData);


            List<CuOperator> list = new ArrayList<>();
            cuCstInfs.forEach(it -> {
                CuOperator cuoperator = new CuOperator();
                BeanUtils.copyProperties(it, cuoperator);
                list.add(cuoperator);
            });
            QueryAllOperatorOutDTO queryCustomerListOutDTO = new QueryAllOperatorOutDTO();
            queryCustomerListOutDTO.setCuoperatorList(list);
            result.setRespBody(queryCustomerListOutDTO);

        } catch (Exception e) {
            DomainServiceUtil.transferUndeclaredThrowableException(e, RespHeaderData, ErrorCodeMsgEnum.FAIL.code(), ErrorCodeMsgEnum.FAIL.msg());
        }
        result.setRespHeader(RespHeaderData);


        log.info("分页查询所有的用户信息列表-能力实现,result={}", result);
        return result;
    }
}
