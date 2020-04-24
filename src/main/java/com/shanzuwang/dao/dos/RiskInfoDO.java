package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shanzuwang.dao.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("risk_info")
public class RiskInfoDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    /**
     * 免押额度
     */
    private Float deposit;

    /**
     * 已用免押额度
     */
    private Float depositUsed;

    /**
     * 临时免押额度
     */
    private Float tempDeposit;

    /**
     * 临时额度已用
     */
    private Float tempDepositUsed;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司电话
     */
    private String companyTel;

    /**
     * 营业执照号码
     */
    private String bizLicenseNum;

    /**
     * 营业执照照片链接
     */
    private String bizLicenseImgurl;

    /**
     * 公司所在城市
     */
    private String bizCity;

    /**
     * 办公地址
     */
    private String bizAddress;

    /**
     * 企业邮箱
     */
    private String email;

    /**
     * 企业官网
     */
    private String webSite;

    /**
     * 法人姓名
     */
    private String legalPersonName;

    /**
     * 法人手机号
     */
    private String legalPersonPhone;

    /**
     * 法人身份证号
     */
    private String legalPersonIdcard;

    /**
     * 法人身份证正面
     */
    private String idcardImgA;

    /**
     * 法人身份证反面
     */
    private String idcardImgB;

    /**
     * 负责人姓名
     */
    private String manager;

    /**
     * 负责人身份证号
     */
    private String managerIdcard;

    /**
     * 负责人手机号
     */
    private String managerPhone;

    /**
     * 补充信息
     */
    private String extra;

    /**
     * 审核备注
     */
    private String comment;

    /**
     * wait, pass, reject
     */
    private String status;

    private Date createdAt;

    private Date updatedAt;


}
