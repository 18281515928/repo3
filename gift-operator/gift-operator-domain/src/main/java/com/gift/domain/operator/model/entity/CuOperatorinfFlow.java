package com.gift.domain.operator.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息变更流水表
 * </p>
 *
 * @author yangquan
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CuOperatorinfFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("UFF_FLOWNO")
    private String uffFlowno;

    /**
     * 变更类型1.新增 2.更新用户状态3.更新用户基本信息（含权限）
     */
    @TableField("UFF_OPTTYPE")
    private String uffOpttype;

    /**
     * 变更时间
     */
    @TableField("UFF_OPTTIME")
    private String uffOpttime;

    /**
     * 系统流水号
     */
    @TableField("UFF_SYSFLOWNO")
    private String uffSysflowno;

    /**
     * 全局流水号
     */
    @TableField("UFF_GLOFLOWNO")
    private String uffGloflowno;

    /**
     * 企业客户号
     */
    @TableField("UFF_CSTNO")
    private String uffCstno;

    /**
     * 用户编号
     */
    @TableField("UFF_USERNO")
    private String uffUserno;

    /**
     * 姓名
     */
    @TableField("UFF_USERNAME")
    private String uffUsername;

    /**
     * 用户状态0： 正常 1：已注销（全渠道）
     */
    @TableField("UFF_STT")
    private String uffStt;

    /**
     * 性别0：男，1：女
     */
    @TableField("UFF_USERSEX")
    private String uffUsersex;

    /**
     * 工作部门
     */
    @TableField("UFF_DEPARTMENT")
    private String uffDepartment;

    /**
     * 证件类型 110=居民身份证，121=士兵证，122=军官证，123=文职干部证，132=警官证，136=军人、武装警察身份证，140=户口簿，150=外国护照，151=外国人永久居留证，160=港澳居民来往内地通行证，162=台湾居民来往大陆通行证，190=中国机动车驾驶证，191=社会保障卡，193=外国居民身份证，194=工作证，195=中国护照，199=其它证件
     */
    @TableField("UFF_CERTTYPE")
    private String uffCerttype;

    /**
     * 证件号码
     */
    @TableField("UFF_CERTNO")
    private String uffCertno;

    /**
     * 职位
     */
    @TableField("UFF_JOBTITLE")
    private String uffJobtitle;

    /**
     * 电子邮件
     */
    @TableField("UFF_EMAIL")
    private String uffEmail;

    /**
     * 用户权限（10位二进制：录入、复核、发送、管理、强制授权。如：10000表示只有录入权限）
     */
    @TableField("UFF_RIGHT")
    private String uffRight;

    /**
     * 授权级别（0-5）
     */
    @TableField("UFF_AUTHLEVEL")
    private String uffAuthlevel;

    /**
     * 操作员开户日期
     */
    @TableField("UFF_OPENDATE")
    private String uffOpendate;

    /**
     * 操作员销户日期
     */
    @TableField("UFF_CANCELDATE")
    private String uffCanceldate;


}
