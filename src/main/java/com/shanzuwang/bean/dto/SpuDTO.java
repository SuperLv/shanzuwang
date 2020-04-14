package com.shanzuwang.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by LiWeijie
 * 20/04/02 16:06
 */
@ApiModel(value ="SpuDTOValue" ,description = "SpuDTODesc")
@Data
public class SpuDTO implements Serializable {

    private Integer id;

    /**
     * 商户ID
     */
    private String projectId;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建日期
     */
    private Date createdAt;

    /**
     * 更新日期
     */
    private Date updatedAt;


    /**
     * online, offline, delete
     */
    private String status;

    /**品牌名*/
    private  String cateName;

    private int[] path;

    private List<ProductPropertyDTO> properties;

}
