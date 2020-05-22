package com.shanzuwang.service.impl;

import com.shanzuwang.config.pay.AlipayReq;
import com.shanzuwang.dao.dos.AdminUserDO;
import com.shanzuwang.dao.mapper.AdminUserDao;
import com.shanzuwang.service.IAdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员用户 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUserDO> implements IAdminUserService {
}
