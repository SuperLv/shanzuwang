package com.shanzuwang.service;

import com.shanzuwang.bean.req.website.ArticleReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.dao.dos.ArticleDO;
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
public interface IArticleService extends IService<ArticleDO> {

    public List<ArticleReq>  ListArticle(PageReq pageReq);

    public ArticleReq getArticle(Integer id);

}
