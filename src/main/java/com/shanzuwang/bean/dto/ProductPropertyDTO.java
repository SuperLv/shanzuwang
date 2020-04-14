package com.shanzuwang.bean.dto;

import com.shanzuwang.dao.dos.BrandDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/04/08 14:53
 */
@ApiModel(value ="ProductPropertyDTOValue" ,description = "ProductPropertyDTODesc")
@Data
public class ProductPropertyDTO implements Serializable {

    private Integer actorId;

    private Integer id;

    private  String name;

    private Integer targetId;

    private String type;

    private String val;

    private BrandDO value;
}
