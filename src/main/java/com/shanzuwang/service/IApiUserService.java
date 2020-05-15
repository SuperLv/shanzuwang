package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IApiUserService extends IService<ApiUserDO> {

    public PageInfo<ApiUserDO>  ListClients(PageReq pageReq);

    public ApiUserbillReq ListApiUsers(String id);

}
