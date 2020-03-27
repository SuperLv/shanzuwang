package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.ArticleDO;
import com.shanzuwang.dao.mapper.ArticleDao;
import com.shanzuwang.service.IArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleDO> implements IArticleService {

}
