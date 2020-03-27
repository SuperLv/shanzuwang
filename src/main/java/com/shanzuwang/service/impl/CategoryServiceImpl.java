package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.CategoryDO;
import com.shanzuwang.dao.mapper.CategoryDao;
import com.shanzuwang.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryDO> implements ICategoryService {

}
