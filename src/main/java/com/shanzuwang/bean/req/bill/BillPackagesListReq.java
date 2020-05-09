package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hw
 * 20/05/09 11:00
 */
@Data
public class BillPackagesListReq implements Serializable {

    @JsonProperty(value = "bill_packages_list")
    private List<BillPackagesReq> billPackagesReqs;

    private List<Object> packages;

}
