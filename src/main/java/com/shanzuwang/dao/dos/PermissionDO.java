package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shanzuwang.dao.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("permission")
public class PermissionDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 分类(前端,后端等)
     */
    private String category;

    /**
     * 服务(用户,应用等)
     */
    private String service;

    /**
     * 类型(功能,url,标签等)
     */
    private String type;

    /**
     * 权限名
     */
    private String name;

    /**
     * 父权限id
     */
    private String parent;

    /**
     * 权限描述
     */
    private String description;


}
