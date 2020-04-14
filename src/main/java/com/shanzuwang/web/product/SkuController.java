package com.shanzuwang.web.product;

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

    @ApiOperation("sku列表")
    @GetMapping("/spus/{id}/skus")
    public ApiResult<List<SkuQueryReq>> ListSku(@PathVariable Integer id)
    {
        return ApiResult.success(iSkuService.ListSkus(id));
    }

    @ApiOperation("sku查找")
    @GetMapping("/spus/{skuid}/skus/{spuid}")
    public ApiResult<SkuQueryReq> getSku(@PathVariable Integer spuid,@PathVariable Integer skuid)
    {
        return ApiResult.success(iSkuService.getSku(spuid,skuid));
    }


}
