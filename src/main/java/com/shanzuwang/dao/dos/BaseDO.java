package com.shanzuwang.dao.dos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象基类
 *
 * @author lv
 * @since 2020/1/31
 */
@Data
public class BaseDO implements Serializable {
    /**
     * 创建时间
     */
    @JsonProperty(value = "created_at")
    private Date createdAt;

    /**
     * 修改时间
     */
    @JsonProperty(value = "updated_at")
    private Date updatedAt;
}
