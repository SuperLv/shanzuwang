package com.shanzuwang.bean.req.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.ProductPropertyDO;
import lombok.Data;

/**
 * Created by Hw
 * 20/04/09 18:28
 */
@Data
public class Properties {

    private Integer id;

    private String type;

    @JsonProperty(value = "actor_id")
    private Integer actorId;

    @JsonProperty(value = "target_id")
    private Integer targetId;

    private String[] value;

    private String name;
}
