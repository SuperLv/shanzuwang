package com.shanzuwang.web.product;

import com.alibaba.fastjson.JSON;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.SpuDTO;
import com.shanzuwang.bean.req.product.FilterReq;
import com.shanzuwang.bean.req.product.SpuAddReq;
import com.shanzuwang.bean.req.product.SpuQueryRep;
import com.shanzuwang.bean.req.product.SpuReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.SpuDO;
import com.shanzuwang.service.ISpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiWeijie
 * 20/04/02 14:21
 */
@Api(tags = "商品管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class SpuController {
    @Autowired
    ISpuService iSpuService;

    @ApiOperation("添加商品")
    @PostMapping("/spus")
    public ApiResult<SpuDO>  AddSpu(@RequestBody SpuAddReq spuAddReq)
    {
        return ApiResult.success(iSpuService.addSpudo(spuAddReq));
    }

    @ApiOperation("修改商品")
    @PatchMapping("/spus/{id}")
    public ApiResult<SpuDO>  UpdateSpu(@PathVariable Integer id,@RequestBody SpuReq spuReq)
    {
        spuReq.setId(id);
        return ApiResult.success(iSpuService.updateSpudo(spuReq));
    }

    @ApiOperation("商品列表")
    @GetMapping("/spus")
    public ApiResult<PageInfo<SpuDTO>> ListSpu(SpuQueryRep spuQueryRep)
    {
        SpuQueryRep spuReq=new SpuQueryRep();
        spuReq.setPageNo(spuQueryRep.getPageNo());
        spuReq.setPageSize(spuQueryRep.getPageSize());
        String filter=spuQueryRep.getFilter();
        if (filter!=null&&filter!=""){
            FilterReq filterReq= JSON.parseObject(filter,FilterReq.class);
            spuReq.setName(filterReq.getConditions()[0][2]);
        }
        return ApiResult.success(iSpuService.getSpuByPage(spuReq));
    }

    @ApiOperation("查询商品")
    @GetMapping("/spus/{id}")
    public ApiResult<SpuDTO> getSpu(@PathVariable Integer id)
    {
        return ApiResult.success(iSpuService.getSpudo(id));
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/spus/{id}")
    public ApiResult<SpuDO>  DeleteSpu(@PathVariable Integer id)
    {
        SpuDO spuDO=new SpuDO();
        spuDO.setId(id);
        spuDO.setStatus("delete");
        iSpuService.updateById(spuDO);
        return  ApiResult.success(spuDO);
    }

}
