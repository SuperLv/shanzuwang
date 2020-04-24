package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.dao.mapper.ApiUserDao;
import com.shanzuwang.service.IApiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class ApiUserServiceImpl extends ServiceImpl<ApiUserDao, ApiUserDO> implements IApiUserService {

    @Autowired
    IApiUserService iApiUserService;

    @Override
    public PageInfo<ApiUserDO> ListClients(PageReq pageReq) {
        Page<ApiUserDO> page=new Page<>(pageReq.getPageNo(),pageReq.getPageSize());
        page(page);
        List<ApiUserDO> apiUserDOS=page.getRecords();
        return new PageInfo<>(page.getTotal(),apiUserDOS,pageReq.getPageNo(),pageReq.getPageSize(),page.getPages());
    }
}
