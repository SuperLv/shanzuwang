package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.CartDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hw
 * 20/05/18 13:37
 */
@Data
public class PackagesAddReq implements Serializable {

    @JsonProperty(value = "address_id")
    private  Integer addressId;

    private String comment;

    private List<CartDO> orders;

    @JsonProperty(value = "cart_ids")
    private Integer[] cartIds;

}
