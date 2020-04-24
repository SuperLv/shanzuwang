package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableField;
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
@Accessors(chain = true)
@TableName("`case`")
public class CaseDO {

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
    private String companyLogo;

    /**
     * 详情图片
     */
    private String images;

    /**
     * 案例标题
     */
    private String title;

    /**
     * 租赁类型： 短租、长租
     */
    private String rentType;

    /**
     * tag
     */
    private String tag;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束租赁时间
     */
    private Date endDate;

    /**
     * 描述
     */
    @TableField(value ="`desc`" )
    private String desc;

    private String extra;

    private Date createdAt;

    private Date updatedAt;


}
