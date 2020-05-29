package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.bean.req.user.ApiUserQueryReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ApiUserDO GetApiUsers(ApiUserQueryReq apiUserQueryReq);

}
