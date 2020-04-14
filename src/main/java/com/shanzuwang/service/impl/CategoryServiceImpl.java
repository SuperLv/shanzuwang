package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanzuwang.bean.req.product.CategoryReq;
import com.shanzuwang.bean.req.product.PropertyAddRep;
import com.shanzuwang.dao.dos.CategoryDO;
import com.shanzuwang.dao.dos.PropertyDO;
import com.shanzuwang.dao.mapper.CategoryDao;
import com.shanzuwang.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.IPropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    IPropertyService iPropertyService;

    @Override
    public List<CategoryReq> ListCategory() {
        List<CategoryReq> categoryReqs=new ArrayList<>();
        List<CategoryDO> categoryDOS=iCategoryService.list();
        for(CategoryDO c:categoryDOS){
            CategoryReq categoryReq=new CategoryReq();
             BeanUtils.copyProperties(c,categoryReq);
             categoryReq.setPath(inta(c.getPath()));
             categoryReqs.add(categoryReq);
        }
        return categoryReqs;
    }

    @Override
    public CategoryReq getCategory(Integer id) {
        CategoryDO categoryDO=iCategoryService.getById(id);
        CategoryReq categoryReq=new CategoryReq();
        BeanUtils.copyProperties(categoryDO,categoryReq);
        categoryReq.setPath(inta(categoryDO.getPath()));
        return categoryReq;
    }

    @Override
    public CategoryReq AddCategory(CategoryReq categoryReq) {
        CategoryDO categoryDO=new CategoryDO();
        BeanUtils.copyProperties(categoryReq,categoryDO);
        categoryDO.setPath(ints(categoryReq.getPath()));
        iCategoryService.save(categoryDO);
        categoryReq.setId(categoryDO.getId());
        return categoryReq;
    }

    @Override
    public CategoryReq UpdateCategory(Integer id, CategoryReq categoryReq) {
        int[] a=categoryReq.getPath();
        categoryReq.setId(id);
        CategoryDO categoryDO=new CategoryDO();
        BeanUtils.copyProperties(categoryReq,categoryDO);
        categoryDO.setPath(ints(categoryReq.getPath()));
        iCategoryService.updateById(categoryDO);
        return categoryReq;
    }

    public  int[] inta(String val)
    {
        String ima=val.substring(1,val.length()-1);
        String str[] = ima.split(",");
        int array[] = new int[str.length];
        if (str[0].length()>0){
            for(int i=0;i<str.length;i++) {
                array[i] = Integer.parseInt(str[i]);
            }
        }
        return array;
    }

    public  String ints(int[] a)
    {
        String path="";
        for (int i=0;i<a.length;i++){
            if (i!=a.length-1){
                path=path+a[i]+",";
            }else {
                path=path+a[i];
            }
        }
        path="["+path+"]";
        return path;
    }
}
