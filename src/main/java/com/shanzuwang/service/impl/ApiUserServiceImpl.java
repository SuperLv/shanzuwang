package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.dao.dos.ShoppingAddressDO;
import com.shanzuwang.dao.mapper.ApiUserDao;
import com.shanzuwang.service.IApiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.IBillService;
import com.shanzuwang.service.IShoppingAddressService;
import org.springframework.beans.BeanUtils;
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
    ApiUserDao apiUserDao;
    @Autowired
    IApiUserService iApiUserService;
    @Autowired
    IBillService iBillService;
    @Autowired
    IShoppingAddressService iShoppingAddressService;

    @Override
    public PageInfo<ApiUserDO> ListClients(PageReq pageReq) {
        Page<ApiUserDO> page=new Page<>(pageReq.getPageNo(),pageReq.getPageSize());
        page(page);
        List<ApiUserDO> apiUserDOS=page.getRecords();
        return new PageInfo<>(page.getTotal(),apiUserDOS,pageReq.getPageNo(),pageReq.getPageSize(),page.getPages());
    }

    @Override
    public ApiUserbillReq ListApiUsers(String id) {
        ApiUserDO apiUserDO=iApiUserService.getById(id);
        ApiUserbillReq apiUserbillReq=new ApiUserbillReq();
        BeanUtils.copyProperties(apiUserDO,apiUserbillReq);
        //shopping_address
        LambdaQueryWrapper<ShoppingAddressDO> shoppingAddressWrapper=new LambdaQueryWrapper<>();
        shoppingAddressWrapper.eq(ShoppingAddressDO::getUserId,id);
        apiUserbillReq.setShoppingAddressDO(iShoppingAddressService.list(shoppingAddressWrapper));
        return apiUserbillReq;
    }

}
