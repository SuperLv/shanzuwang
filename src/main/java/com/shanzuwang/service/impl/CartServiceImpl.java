package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.CartDO;
import com.shanzuwang.dao.mapper.CartDao;
import com.shanzuwang.service.ICartService;
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
public class CartServiceImpl extends ServiceImpl<CartDao, CartDO> implements ICartService {

}
