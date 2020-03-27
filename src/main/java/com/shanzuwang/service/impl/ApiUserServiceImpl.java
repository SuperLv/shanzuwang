package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.dao.mapper.ApiUserDao;
import com.shanzuwang.service.IApiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
