package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.OrderDO;
import com.shanzuwang.dao.mapper.OrderDao;
import com.shanzuwang.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderDO> implements IOrderService {

    @Autowired
    IOrderService iOrderService;

}
