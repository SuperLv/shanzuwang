package com.shanzuwang.bean.req.bill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BillDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/04/28 16:16
 */
@Data
@ApiModel(value = "UserbillReq",description = "UserbillReqDesc")
public class UserbillReq {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户名
     */
    @JsonProperty(value = "user_name")
    private String userName;

    /**
     * 公司名称
     */
    @JsonProperty(value = "company_name")
    private String companyName;

    /**
     * 真实姓名
     */
    @JsonProperty(value = "real_name")
    private String realName;

    private Date deadline;

    private String  invoice_id;

    @JsonProperty(value = "invoice_status")
    private String invoiceStatus;
    /**
     * 昵称
     */
    @JsonProperty(value = "nick_name")
    private String nickName;


    /**
     * 头像地址
     */
    private String avatar;

    private List<BillDO> bills;

    @JsonProperty(value = "create_time")
    private Date createTime;

    @JsonProperty(value = "wechat_open_id")
    private String wechatOpenId;

    @JsonProperty(value = "alipay_user_id")
    private String alipayUserId;
}

