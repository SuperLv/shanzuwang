package com.shanzuwang.web.product;

import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.SkuDO;
import com.shanzuwang.service.ISkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiWeijie
 * 20/04/02 14:41
 */
@Api(tags = "SKU管理")
@RestController
@Slf4j
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    ISkuService iSkuService;

    @ApiOperation("新增sku")
    @PostMapping("/inster")
    public ApiResult<SkuDO> AddSku(@RequestBody SkuDO skuDO)
    {
        iSkuService.save(skuDO);
        return ApiResult.success(skuDO);
    }

    @ApiOperation("修改sku")
    @PostMapping("/update")
    public ApiResult<SkuDO> UpdateSku(@RequestBody SkuDO skuDO)
    {
        iSkuService.updateById(skuDO);
        return ApiResult.success(skuDO);
    }
}
