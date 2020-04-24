package com.shanzuwang.bean.req.website;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@ApiModel(value ="CaseReqValue" ,description = "CaseReqDesc")
public class CaseReq {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 公司logo url
     */
    @JsonProperty(value = "company_logo")
    private String companyLogo;

    /**
     * 详情图片
     */
    private List<Object> images;

    /**
     * 案例标题
     */
    private String title;

    /**
     * 租赁类型： 短租、长租
     */
    @JsonProperty(value = "rent_type")
    private String rentType;

    /**
     * tag
     */
    private String tag;

    /**
     * 开始时间
     */
    @JsonProperty(value = "start_date")
    private Date startDate;

    /**
     * 结束租赁时间
     */
    @JsonProperty(value = "end_date")
    private Date endDate;

    /**
     * 描述
     */
    private String desc;

    private List<Object> extra;

    private Date createdAt;

    private Date updatedAt;


}
