package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.UserVcodeDO;
import com.shanzuwang.dao.mapper.UserVcodeDao;
import com.shanzuwang.service.IUserVcodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户验证码 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class UserVcodeServiceImpl extends ServiceImpl<UserVcodeDao, UserVcodeDO> implements IUserVcodeService {

}
