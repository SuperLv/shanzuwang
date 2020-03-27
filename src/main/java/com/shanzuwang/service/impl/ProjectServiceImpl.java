package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.ProjectDO;
import com.shanzuwang.dao.mapper.ProjectDao;
import com.shanzuwang.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectDO> implements IProjectService {

}
