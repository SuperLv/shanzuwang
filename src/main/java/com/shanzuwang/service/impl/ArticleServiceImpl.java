package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.req.website.ArticleReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.dao.dos.ArticleDO;
import com.shanzuwang.dao.dos.CategoryDO;
import com.shanzuwang.dao.mapper.ArticleDao;
import com.shanzuwang.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.ICategoryService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleDO> implements IArticleService {
    @Autowired
    IArticleService iArticleService;
    @Autowired
    ICategoryService iCategoryService;

    @Override
    public List<ArticleReq> ListArticle(PageReq pageReq) {
        Page<ArticleDO> page = new Page<>(pageReq.getPageNo(),pageReq.getPageSize());
        LambdaQueryWrapper<ArticleDO> queryWrapper = new LambdaQueryWrapper<>();
        page(page,queryWrapper);
        List<ArticleDO> articleDOS = page.getRecords();
        List<ArticleReq> articleReqs=new  ArrayList<>();
        for (ArticleDO a:articleDOS){
            ArticleReq articleReq=new ArticleReq();
            BeanUtils.copyProperties(a,articleReq);
            articleReq.setCateName(iCategoryService.getById(a.getCateId()).getName());
            articleReqs.add(articleReq);
        }
        return articleReqs;
    }

    @Override
    public ArticleReq getArticle(Integer id) {
        ArticleDO articleDO=iArticleService.getById(id);
        ArticleReq articleReq=new ArticleReq();
        BeanUtils.copyProperties(articleDO,articleReq);
        CategoryDO categoryDO=iCategoryService.getById(articleDO.getCateId());
        articleReq.setPath(CategoryServiceImpl.inta(categoryDO.getPath()));
        articleReq.setCateName(categoryDO.getName());
        return articleReq;
    }


}
