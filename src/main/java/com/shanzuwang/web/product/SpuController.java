package com.shanzuwang.web.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.bean.req.SpuReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.SpuDO;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.service.ISpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiWeijie
 * 20/04/02 14:21
 */
@Api(tags = "商品管理")
@Slf4j
@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    ISpuService iSpuService;

    @ApiOperation("添加商品")
    @PostMapping("/inster")
    public ApiResult<SpuDO>  AddSpu(SpuDO spuDO)
    {
        iSpuService.save(spuDO);
        return ApiResult.success(spuDO);
    }

    @ApiOperation("修改商品")
    @PostMapping("/update")
    public ApiResult<SpuDO>  UpdateSpu(SpuDO spuDO)
    {
        iSpuService.updateById(spuDO);
        return ApiResult.success(spuDO);
    }

    @ApiOperation("商品列表")
    @GetMapping("/list")
    public ApiResult<PageInfo<SpuDto>> ListSpuByPage(SpuReq spuReq)
    {
        return ApiResult.success(iSpuService.getUserByPage(spuReq));
    }

    @ApiOperation("查询商品")
    @PostMapping("/get")
    public ApiResult<SpuDO> getSpuByPage(@RequestBody SpuReq spuReq)
    {
        return ApiResult.success(iSpuService.getSpudo(spuReq));
    }

    @ApiOperation("删除商品")
    @PostMapping("/delete")
    public ApiResult<SpuDO>  DeleteSpu(Integer id)
    {
        SpuDO spuDO=new SpuDO();
        spuDO.setId(id);
        spuDO.setStatus("delete");
        iSpuService.updateById(spuDO);
        return  ApiResult.success(spuDO);
    }
}
