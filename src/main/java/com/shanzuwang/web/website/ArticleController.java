package com.shanzuwang.web.website;

import com.shanzuwang.bean.req.website.ArticleReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.ArticleDO;
import com.shanzuwang.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/04/14 18:22
 */
@Api(tags = "文章管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    IArticleService iArticleService;


    @ApiOperation("添加文章")
    @PostMapping("/articles")
    public ApiResult<ArticleDO> AddArticle(@RequestBody ArticleDO articleDO)
    {
        articleDO.setCreatedAt(new Date());
        iArticleService.save(articleDO);
        return ApiResult.success(articleDO);
    }

    @ApiOperation("修改文章")
    @PatchMapping("/articles/{id}")
    public ApiResult<ArticleDO> getArticle(@PathVariable Integer id,@RequestBody ArticleDO articleDO)
    {
        articleDO.setId(id);
        articleDO.setCreatedAt(new Date());
        iArticleService.updateById(articleDO);
        return ApiResult.success(articleDO);
    }

    @ApiOperation("文章列表")
    @GetMapping("/articles")
    public  ApiResult<List<ArticleReq>> ListArtcle(PageReq pageReq)
    {
        return  ApiResult.success(iArticleService.ListArticle(pageReq));
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/articles/{id}")
    public ApiResult DeleteArtcle(@PathVariable Integer id)
    {
        iArticleService.removeById(id);
        return ApiResult.success(null);
    }


    @ApiOperation("查询文章")
    @GetMapping("articles/{id}")
    public ApiResult<ArticleReq> getArticle(@PathVariable Integer id)
    {
         return ApiResult.success(iArticleService.getArticle(id));
    }


}
