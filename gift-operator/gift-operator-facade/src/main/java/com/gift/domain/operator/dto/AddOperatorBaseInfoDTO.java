package com.gift.domain.operator.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>新增用户信息</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className AddoperatorBaseInfoDTO
 * @sine 2020/3/24 10:55
 */
@Data
public class AddOperatorBaseInfoDTO implements Serializable {

    private static final long serialVersionUID = 6602276592050701386L;
    /**
     * 企业客户号
     */
    private String uifCstno;

    /**
     * 用户编号
     */
    private String uifUserno;

    /**
     * 姓名
     */
    private String uifUsername;

    /**
     * 用户状态0： 正常 1：已注销（全渠道）
     */
    private String uifStt;

    /**
     * 性别0：男，1：女
     */
    private String uifUsersex;

    /**
     * 工作部门
     */
    private String uifDepartment;

    /**
     * 证件类型 110=居民身份证，121=士兵证，122=军官证，123=文职干部证，132=警官证，136=军人、武装警察身份证，140=户口簿，150=外国护照，151=外国人永久居留证，160=港澳居民来往内地通行证，162=台湾居民来往大陆通行证，190=中国机动车驾驶证，191=社会保障卡，193=外国居民身份证，194=工作证，195=中国护照，199=其它证件
     */
    private String uifCerttype;

    /**
     * 证件号码
     */
    private String uifCertno;

    /**
     * 职位
     */
    private String uifJobtitle;

    /**
     * 电子邮件
     */
    private String uifEmail;

    /**
     * 用户权限（10位二进制：录入、复核、发送、管理、强制授权。如：10000表示只有录入权限）
     */
    private String uifRight;

    /**
     * 授权级别（0-5）
     */
    private String uifAuthlevel;

    /**
     * 操作员开户日期
     */
    private String uifOpendate;

    /**
     * 操作员销户日期
     */
    private String uifCanceldate;
    /**
     * 手机号
     */
    private String uifPhone;
}
