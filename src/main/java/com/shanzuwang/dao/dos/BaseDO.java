package com.shanzuwang.dao.dos;

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
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;
}
