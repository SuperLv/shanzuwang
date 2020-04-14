package com.shanzuwang.bean.req.product;

import com.shanzuwang.dao.dos.BrandDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/04/08 18:00
 */

@Data
@ApiModel(value ="propertiesValue" ,description = "propertiesDesc")
public class ProductPropertyDOReq implements Serializable {

    public Integer id;

    private BrandDO value;
}
