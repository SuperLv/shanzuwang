package com.shanzuwang.service;

import com.shanzuwang.bean.req.product.CategoryReq;
import com.shanzuwang.bean.req.product.PropertyAddRep;
import com.shanzuwang.dao.dos.CategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface ICategoryService extends IService<CategoryDO> {

    public List<CategoryReq> ListCategory();

    public CategoryReq getCategory(Integer id);

    public CategoryReq AddCategory(CategoryReq categoryReq);

    public CategoryReq UpdateCategory(Integer id,CategoryReq categoryReq);

    public List<CategoryReq>  getCategoriesTree();


}
