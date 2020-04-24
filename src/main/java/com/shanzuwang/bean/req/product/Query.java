package com.shanzuwang.bean.req.product;

import com.shanzuwang.bean.req.PageReq;
import lombok.Data;

/**
 * Created by Hw
 * 20/04/20 17:49
 */
@Data
public class Query extends PageReq {
    private  String sorting;

    private  String filter;
}
