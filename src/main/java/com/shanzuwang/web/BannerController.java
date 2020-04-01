package com.shanzuwang.web;

import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.BannerAddReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.config.annotation.Authority;
import com.shanzuwang.config.annotation.User;
import com.shanzuwang.config.result.CommonCode;
import com.shanzuwang.config.result.Result;
import com.shanzuwang.config.result.ResultGenerator;
import com.shanzuwang.dao.dos.BannerDO;
import com.shanzuwang.service.IBannerService;
import com.shanzuwang.util.CommonDataService;
import com.shanzuwang.util.CommonDataServiceManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "广告管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class BannerController {
    @Autowired
    private IBannerService bannerService;
    @Autowired
    private CommonDataService commonDataService;

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
    @GetMapping("/bannersd/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "access-token", value = "token", paramType = "header", dataType = "String", required = true )
    })
    @Authority
    public Result getBanner(String bannid, @ApiIgnore @User UserDTO currentUser){
        if(currentUser == null){
            return ResultGenerator.genFailResult(CommonCode.SERVICE_ERROR,"未登录！",null);
        }
        log.info("banner_id:{}",bannid);
        return null;
    }

    @ApiOperation("修改banner")
    @PatchMapping("/banners/{id}")
    public ApiResult<BannerDO> updateBanner(@PathVariable Integer id){
        log.info("banner_id:{}",id);
        return null;
    }

    @ApiOperation("删除banner")
    @DeleteMapping("/banners/{id}")
    public ApiResult<BannerDO> delBanner(@PathVariable Integer id){
        log.info("banner_id:{}",id);
        return null;
    }

}
