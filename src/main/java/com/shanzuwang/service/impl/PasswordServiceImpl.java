package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.PasswordDO;
import com.shanzuwang.dao.mapper.PasswordDao;
import com.shanzuwang.service.IPasswordService;
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
public class PasswordServiceImpl extends ServiceImpl<PasswordDao, PasswordDO> implements IPasswordService {

}
