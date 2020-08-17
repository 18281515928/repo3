package com.gift.domain.operator.service.impl;


import com.gift.domain.infrastructure.ability.dto.RequestDTO;
import com.gift.domain.infrastructure.ability.dto.ResponseDTO;
import com.gift.domain.infrastructure.ability.dto.data.BaseReqHeaderData;
import com.gift.domain.infrastructure.ability.dto.data.GiftReqHeaderData;
import com.gift.domain.infrastructure.ability.dto.data.ReqHeaderData;
import com.gift.domain.operator.dto.*;
import com.gift.domain.operator.service.IOperatorDomain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * <p>todo</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className operatorDomainServiceTest
 * @sine 2020/3/24 14:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorDomainServiceTest {

    @Autowired
    IOperatorDomain operatorDomain;

    @Test
    public void testQueryoperatorBaseInfo(){
        QueryOperatorBaseInfoDTO queryCustomerBaseInfoDTO = new QueryOperatorBaseInfoDTO();
        queryCustomerBaseInfoDTO.setUifCstNo("cst001");
        queryCustomerBaseInfoDTO.setUifoperatorNo("operator001");
        //构造请求头
        String globalFlowNo = "2003171SDFDV00123456789123456789";
        String productCode = "queryOperatorBaseInfo";
        String eventCode = "112213";
        //组装请求报文
        RequestDTO<QueryOperatorBaseInfoDTO> requestDTO = new RequestDTO<>(queryCustomerBaseInfoDTO);
        ReqHeaderData reqHeaderData = getReqHeaderData(productCode);
        requestDTO.setReqHead(reqHeaderData);

        ResponseDTO<QueryOperatorBaseInfoOutDTO> responseDTO = operatorDomain.queryOperatorBaseInfo(requestDTO);
        Assert.assertEquals("0", responseDTO.getRespHeader().getErrorCode());
    }


    @Test
    public void testQueryAllOperator(){
        QueryAllOperatorDTO queryAllOperatorDTO = new QueryAllOperatorDTO();
        //构造请求头
        String globalFlowNo = "2003171SDFDV00123456789123456789";
        String productCode = "queryAllOperator";
        String eventCode = "112213";
        //组装请求报文
        RequestDTO<QueryAllOperatorDTO> requestDTO = new RequestDTO<>(queryAllOperatorDTO);
        ReqHeaderData reqHeaderData = getReqHeaderData(productCode);
        requestDTO.setReqHead(reqHeaderData);
        queryAllOperatorDTO.setUifCstno("cst001");
        ResponseDTO<QueryAllOperatorOutDTO> responseDTO = operatorDomain.queryALLOperator(requestDTO);
        Assert.assertEquals("0", responseDTO.getRespHeader().getErrorCode());
    }

    @Test
    public void testAddOperatorBaseInfo(){

        Random random = new Random();
        AddOperatorBaseInfoDTO operatorBaseInfoDTO = new AddOperatorBaseInfoDTO();
        operatorBaseInfoDTO.setUifCstno("cst001");
        operatorBaseInfoDTO.setUifUserno("operator00"+String.format("%02d",random.nextInt(99)));
        operatorBaseInfoDTO.setUifUsername("张三");
        operatorBaseInfoDTO.setUifStt("0");
        operatorBaseInfoDTO.setUifUsersex("0");
        operatorBaseInfoDTO.setUifDepartment("科技部");
        operatorBaseInfoDTO.setUifCerttype("110");
        operatorBaseInfoDTO.setUifCertno("310100199001020"+String.format("%03d",random.nextInt(999)));
        operatorBaseInfoDTO.setUifJobtitle("铜锣湾扛把子");
        operatorBaseInfoDTO.setUifEmail("11111@qq.com");
        operatorBaseInfoDTO.setUifRight("10000");
        operatorBaseInfoDTO.setUifAuthlevel("0");
        operatorBaseInfoDTO.setUifOpendate("20200519");
        operatorBaseInfoDTO.setUifCanceldate("");



        //构造请求头
        String globalFlowNo = "2003171SDFDV00123456789123456789";
        String productCode = "addOperatorBaseInfo";
        String eventCode = "112213";
        //组装请求报文
        RequestDTO<AddOperatorBaseInfoDTO> requestDTO = new RequestDTO<>(operatorBaseInfoDTO);
        ReqHeaderData reqHeaderData = getReqHeaderData(productCode);
        requestDTO.setReqHead(reqHeaderData);

        ResponseDTO<AddOperatorBaseInfoOutDTO> responseDTO = operatorDomain.addOperatorBaseInfo(requestDTO);
        Assert.assertEquals("0", responseDTO.getRespHeader().getErrorCode());
    }

    @Test
    public void testUpdateOperatorStatus(){
        UpdateOperatorStatusDTO updateOperatorStatusDTO = new UpdateOperatorStatusDTO();

        updateOperatorStatusDTO.setUifCstno("cst001");
        updateOperatorStatusDTO.setUifUserno("operator001");
        updateOperatorStatusDTO.setUifStt("1");//测试状态更新

        //构造请求头
        String globalFlowNo = "2003171SDFDV00123456789123456789";
        String productCode = "updateOperatorStatus";
        String eventCode = "112213";
        //组装请求报文
        RequestDTO<UpdateOperatorStatusDTO> requestDTO = new RequestDTO<>(updateOperatorStatusDTO);
        ReqHeaderData reqHeaderData = getReqHeaderData(productCode);
        requestDTO.setReqHead(reqHeaderData);

        ResponseDTO<UpdateOperatorStatusOutDTO> responseDTO = operatorDomain.updateOperatorStatus(requestDTO);
        Assert.assertEquals("0", responseDTO.getRespHeader().getErrorCode());

    }


    @Test
    public void testUpdateOperatorBaseInfo(){
        UpdateOperatorBaseInfoDTO updateOperatorBaseInfoDTO = new UpdateOperatorBaseInfoDTO();
        updateOperatorBaseInfoDTO.setUifCstno("cst001");
        updateOperatorBaseInfoDTO.setUifUserno("operator001");
        updateOperatorBaseInfoDTO.setUifUsername("zhangsan");
        updateOperatorBaseInfoDTO.setUifStt("1");//测试状态不更新
        updateOperatorBaseInfoDTO.setUifUsersex("1");
        updateOperatorBaseInfoDTO.setUifDepartment("纪检监察部");
        updateOperatorBaseInfoDTO.setUifCerttype("110");
        updateOperatorBaseInfoDTO.setUifCertno("111222333");


        //构造请求头
        String globalFlowNo = "2003171SDFDV00123456789123456789";
        String productCode = "updateOperatorBaseInfo";
        String eventCode = "112213";
        //组装请求报文
        RequestDTO<UpdateOperatorBaseInfoDTO> requestDTO = new RequestDTO<>(updateOperatorBaseInfoDTO);
        ReqHeaderData reqHeaderData = getReqHeaderData(productCode);
        requestDTO.setReqHead(reqHeaderData);

        ResponseDTO<UpdateOperatorBaseInfoOutDTO> responseDTO = operatorDomain.updateOperatorBaseInfo(requestDTO);
        Assert.assertEquals("0", responseDTO.getRespHeader().getErrorCode());

    }
    private ReqHeaderData getReqHeaderData(String scenarioCode) {
        String globalFlowNo = "2002031SDFDV00123456789012934512";
//        String scenarioCode = "channelRoleListQuery";
        String processCode = "operatorOp";
        ReqHeaderData reqHeaderData = new ReqHeaderData();
        BaseReqHeaderData baseReqHeaderData = new BaseReqHeaderData();
        GiftReqHeaderData giftReqHeaderData = new GiftReqHeaderData();
        giftReqHeaderData.setGlobalReqNo(globalFlowNo);
        giftReqHeaderData.setScenario(scenarioCode);
        giftReqHeaderData.setProcessCode(processCode);
        reqHeaderData.setGiftReqHeaderData(giftReqHeaderData);
        reqHeaderData.setBaseReqHeaderData(baseReqHeaderData);
        return reqHeaderData;
    }

}
