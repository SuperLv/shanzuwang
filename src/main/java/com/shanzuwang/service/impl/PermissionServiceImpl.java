package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.PermissionDO;
import com.shanzuwang.dao.mapper.PermissionDao;
import com.shanzuwang.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionDO> implements IPermissionService {

}
