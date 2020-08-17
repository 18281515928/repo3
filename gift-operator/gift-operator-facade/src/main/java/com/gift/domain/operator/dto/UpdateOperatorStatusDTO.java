package com.gift.domain.operator.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>用户状态更新入参实体</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className UpdateoperatorStatusDTO
 * @sine 2020/3/24 11:00
 */
@Data
public class UpdateOperatorStatusDTO  implements Serializable {


    private static final long serialVersionUID = 456855091308488223L;
    /**
     * 企业客户号
     */
    private String uifCstno;

    /**
     * 用户编号
     */
    private String uifUserno;

    /**
     * 用户状态0： 正常 1：已注销（全渠道）
     */
    private String uifStt;


}
