package com.gift.domain.operator.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 企业操作员信息表
 * </p>
 *
 * @author yangquan
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CuOperatorInf implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业客户号
     */
    @TableId("UIF_CSTNO")
    private String uifCstno;

    /**
     * 用户编号
     */
    @TableField("UIF_USERNO")
    private String uifUserno;

    /**
     * 姓名
     */
    @TableField("UIF_USERNAME")
    private String uifUsername;

    /**
     * 用户状态0： 正常 1：已注销（全渠道）
     */
    @TableField("UIF_STT")
    private String uifStt;

    /**
     * 性别0：男，1：女
     */
    @TableField("UIF_USERSEX")
    private String uifUsersex;

    /**
     * 工作部门
     */
    @TableField("UIF_DEPARTMENT")
    private String uifDepartment;

    /**
     * 证件类型 110=居民身份证，121=士兵证，122=军官证，123=文职干部证，132=警官证，136=军人、武装警察身份证，140=户口簿，150=外国护照，151=外国人永久居留证，160=港澳居民来往内地通行证，162=台湾居民来往大陆通行证，190=中国机动车驾驶证，191=社会保障卡，193=外国居民身份证，194=工作证，195=中国护照，199=其它证件
     */
    @TableField("UIF_CERTTYPE")
    private String uifCerttype;

    /**
     * 证件号码
     */
    @TableField("UIF_CERTNO")
    private String uifCertno;

    /**
     * 职位
     */
    @TableField("UIF_JOBTITLE")
    private String uifJobtitle;

    /**
     * 电子邮件
     */
    @TableField("UIF_EMAIL")
    private String uifEmail;

    /**
     * 用户权限（10位二进制：录入、复核、发送、管理、强制授权。如：10000表示只有录入权限）
     */
    @TableField("UIF_RIGHT")
    private String uifRight;

    /**
     * 授权级别（0-5）
     */
    @TableField("UIF_AUTHLEVEL")
    private String uifAuthlevel;

    /**
     * 操作员开户日期
     */
    @TableField("UIF_OPENDATE")
    private String uifOpendate;

    /**
     * 操作员销户日期
     */
    @TableField("UIF_CANCELDATE")
    private String uifCanceldate;
    /**
     * 手机号
     */
    @TableField("UIF_PHONE")
    private String uifPhone;


}
