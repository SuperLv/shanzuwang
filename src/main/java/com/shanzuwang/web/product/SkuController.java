package com.shanzuwang.web.product;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.ISkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiWeijie
 * 20/04/02 14:41
 */
@Api(tags = "SKU管理")
@RestController
@Slf4j
@RequestMapping("/api")
public class SkuController {

    @Autowired
    ISkuService iSkuService;

    @ApiOperation("新增sku")
    @PostMapping("/spus/{spuid}/skus")
    public ApiResult<SkuQueryReq> AddSku(@PathVariable Integer spuid,@RequestBody SkuQueryReq skuQueryReq)
    {
        return ApiResult.success(iSkuService.AddSku(spuid,skuQueryReq));
    }

    @ApiOperation("修改sku")
    @PatchMapping("/spus/{skuid}/skus/{spuid}")
    public ApiResult<SkuQueryReq> UpdateSku(@PathVariable Integer spuid,@PathVariable Integer skuid,@RequestBody SkuQueryReq skuQueryReq)
    {
        return ApiResult.success(iSkuService.UpdateSku(spuid,skuid,skuQueryReq));
    }

    @ApiOperation("根据spu查询sku列表")
    @GetMapping("/spus/{id}/skus")
    public ApiResult<List<SkuQueryReq>> ListSpuSku(@PathVariable Integer id)
    {
        return ApiResult.success(iSkuService.ListSpuSkus(id));
    }

    @ApiOperation("sku查找(后台)")
    @GetMapping("/spus/{skuid}/skus/{spuid}")
    public ApiResult<SkuQueryReq>   GetSku(@PathVariable Integer spuid,@PathVariable Integer skuid)
    {
        return ApiResult.success(iSkuService.getSku(spuid,skuid));
    }


    @ApiOperation("首页sku列表查询")
    @GetMapping("/skus")
    public PageInfo<SkuQueryReq> ListSkus(Query query)
    {
        return iSkuService.ListSkus(query);
    }

    @ApiOperation("sku查找(官网)")
    @GetMapping("/skus/{id}")
    public ApiResult<SkuQueryReq> GetSkus(@PathVariable Integer id)
    {
        return ApiResult.success(iSkuService.getSku(null,id));
    }

}
