package com.shanzuwang.bean.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求参数
 * @author lv
 */
@Data
public class PageReq implements Serializable {

    private static final long serialVersionUID = 398191218293040778L;

    private Integer pageNo = 1;

    private Integer pageSize = 10;

}
