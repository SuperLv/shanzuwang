package com.shanzuwang.web.product;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.BrandDTO;
import com.shanzuwang.bean.req.product.BrandAddReq;
import com.shanzuwang.bean.req.product.BrandQueryReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.service.IBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiWeijie
 * 20/04/01 16:48
 */
@Api(tags = "品牌管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class BrandController {
    @Autowired
    IBrandService iBrandService;

    @ApiOperation("新增品牌")
    @PostMapping("/brands")
    public ApiResult<BrandDO> AddBrand(@RequestBody BrandAddReq brandAddReq)
    {
        BrandDO brandDO=new BrandDO();
        BeanUtils.copyProperties(brandAddReq,brandDO);
        iBrandService.save(brandDO);
        log.info("brand:{}brand:{}", brandAddReq.toString());
        return ApiResult.success(brandDO);
    }

    @ApiOperation("修改品牌")
    @PatchMapping("/brands/{id}")
    public  ApiResult<BrandDO> UpdateBrand(@PathVariable Integer id,@RequestBody BrandDO brandDO)
    {
        iBrandService.updateById(brandDO);
        return  ApiResult.success(brandDO);
    }

    @ApiOperation("品牌列表")
    @GetMapping("/brands")
    public  ApiResult<PageInfo<BrandDTO>> ListBrand(PageReq pageReq)
    {
        BrandQueryReq brandQueryReq =new BrandQueryReq();
        brandQueryReq.setPageNo(pageReq.getPageNo());
        brandQueryReq.setPageSize(pageReq.getPageSize());
        return ApiResult.success(iBrandService.getUserByPage(brandQueryReq));
    }

    @ApiOperation("查询品牌")
    @GetMapping("/brands/{id}")
    public  ApiResult<BrandDO> GetBrand(@PathVariable Integer id)
    {
        return ApiResult.success(iBrandService.getBranddo(id));
    }


    @ApiOperation("删除品牌")
    @DeleteMapping("/brands/{id}")
    public  ApiResult<Integer> DeleteBrand(@PathVariable Integer id)
    {
        iBrandService.removeById(id);
        return  ApiResult.success(id);
    }
}
