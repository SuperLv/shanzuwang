package com.shanzuwang.bean.req.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Hw
 * 20/04/20 17:31
 */
@Data
@ApiModel(value = "RiskReq",description = "RiskReqDesc")
public class RiskReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonProperty(value = "user_id")
    private String userId;

    /**
     * 免押额度
     */
    private Float deposit;

    /**
     * 已用免押额度
     */
    @JsonProperty(value = "deposit_used")
    private Float depositUsed;

    /**
     * 临时免押额度
     */
    @JsonProperty(value = "temp_deposit")
    private Float tempDeposit;

    /**
     * 临时额度已用
     */
    @JsonProperty(value = "temp_deposit_used")
    private Float tempDepositUsed;

    /**
     * 公司名称
     */
    @JsonProperty(value = "company_name")
    private String companyName;

    /**
     * 公司电话
     */
    @JsonProperty(value = "company_tel")
    private String companyTel;

    /**
     * 营业执照号码
     */
    @JsonProperty(value = "biz_license_num")
    private String bizLicenseNum;

    /**
     * 营业执照照片链接
     */
    @JsonProperty(value = "biz_license_imgurl")
    private String bizLicenseImgurl;

    /**
     * 公司所在城市
     */
    @JsonProperty(value = "biz_city")
    private String bizCity;

    /**
     * 办公地址
     */
    @JsonProperty(value = "biz_address")
    private String bizAddress;

    /**
     * 企业邮箱
     */
    private String email;

    /**
     * 企业官网
     */
    @JsonProperty(value = "web_site")
    private String webSite;

    /**
     * 法人姓名
     */
    @JsonProperty(value = "legal_person_name")
    private String legalPersonName;

    /**
     * 法人手机号
     */
    @JsonProperty(value = "legal_person_phone")
    private String legalPersonPhone;

    /**
     * 法人身份证号
     */
    @JsonProperty(value = "legal_person_idcard")
    private String legalPersonIdcard;

    /**
     * 法人身份证正面
     */
    @JsonProperty(value = "idcard_img_a")
    private String idcardImgA;

    /**
     * 法人身份证反面
     */
    @JsonProperty(value = "idcard_img_b")
    private String idcardImgB;

    /**
     * 负责人姓名
     */
    private String manager;

    /**
     * 负责人身份证号
     */
    @JsonProperty(value = "manager_idcard")
    private String managerIdcard;

    /**
     * 负责人手机号
     */
    @JsonProperty(value = "manager_phone")
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

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updatedAt;

}
