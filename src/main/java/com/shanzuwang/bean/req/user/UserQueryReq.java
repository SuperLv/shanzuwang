package com.shanzuwang.bean.req.user;

import com.shanzuwang.bean.req.PageReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 用户查询请求参数
 *
 * @author lv
 * @since 2020/1/31
 */
@ApiModel(value ="userQueryValue" ,description = "userQueryDesc")
@Data
public class UserQueryReq extends PageReq {
    /**
     * 用户id
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "id有误")
    private Integer id;
    /**
     * 用户姓名
     */
    @Length(max = 20,message = "用户姓名最多支持20个字符")
    private String name;
}
