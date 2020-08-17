package com.gift.domain.operator.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>查询用户基本信息</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className QueryoperatorBaseInfoDTO
 * @sine 2020/3/24 10:52
 */
@Data
public class QueryOperatorBaseInfoDTO  implements Serializable {

    private static final long serialVersionUID = 3709326175978194773L;
    /**
     * 企业客户号
     */
    private String uifCstNo;
    /**
     * 用户编号
     */
    private String uifoperatorNo;
}
