package com.shanzuwang.web;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.BannerDTO;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.BannerAddReq;
import com.shanzuwang.bean.req.UserQueryReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.BannerDO;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.service.IBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "广告管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class BannerController {
    @Autowired
    private IBannerService bannerService;

    @ApiOperation("banner查询")
    @GetMapping("/banners")
    public ApiResult<List<BannerDO>> getBanners(String type){
        if(type==null||type.equals("banner"))
            return ApiResult.success(bannerService.list());
        return ApiResult.error();
    }

    @ApiOperation("添加banner")
    @PostMapping("/banners")
    public ApiResult<BannerDO> AddBanners(@Valid  @RequestBody BannerAddReq bannerAddReq){
        BannerDO bannerDO = new BannerDO();
        BeanUtils.copyProperties(bannerAddReq,bannerDO);
        bannerService.save(bannerDO);
        log.info("banner:{}新增banner:{}",bannerAddReq.toString());
        return ApiResult.success(bannerDO);
    }

    @ApiOperation("添加banner")
    @GetMapping("/banners/{id}")
    public ApiResult<BannerDO> getBanner(@PathVariable Integer id){
        log.info("banner_id:{}",id);
        return null;
    }

    @ApiOperation("修改banner")
    @PatchMapping("/banners/{id}")
    public ApiResult<BannerDO> updateBanner(@PathVariable Integer id){
        log.info("banner_id:{}",id);
        return null;
    }

    @ApiOperation("添加banner")
    @DeleteMapping("/banners/{id}")
    public ApiResult<BannerDO> delBanner(@PathVariable Integer id){
        log.info("banner_id:{}",id);
        return null;
    }

}
