package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.OrderDO;
import com.shanzuwang.dao.mapper.OrderDao;
import com.shanzuwang.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
