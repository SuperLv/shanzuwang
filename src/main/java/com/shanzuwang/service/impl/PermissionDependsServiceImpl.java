package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.PermissionDependsDO;
import com.shanzuwang.dao.mapper.PermissionDependsDao;
import com.shanzuwang.service.IPermissionDependsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限互相依赖表 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class PermissionDependsServiceImpl extends ServiceImpl<PermissionDependsDao, PermissionDependsDO> implements IPermissionDependsService {

}
