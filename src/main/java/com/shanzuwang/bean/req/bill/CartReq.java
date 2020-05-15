package com.shanzuwang.bean.req.bill;

import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.CartDO;
import com.shanzuwang.dao.dos.PeriodsDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/13 15:33
 */
@Data
@ApiModel(value = "CartReq",description = "CartReqDesc")
public class CartReq extends CartDO implements Serializable {
     private PeriodsDO period;

     private SkuQueryReq product;
}
