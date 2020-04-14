package com.shanzuwang.bean.req.product;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/04/07 15:51
 */
@Data
public class FilterReq implements Serializable {

    private  String type;

    private  String[][] conditions;
}
