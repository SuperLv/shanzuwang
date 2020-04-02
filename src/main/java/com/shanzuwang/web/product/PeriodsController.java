package com.shanzuwang.web.product;

import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.PeriodsDO;
import com.shanzuwang.service.IPeriodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiWeijie
 * 20/04/02 15:08
 */
@Api(tags = "租期价格管理")
@Slf4j
@RestController
@RequestMapping("/periods")
public class PeriodsController {

    @Autowired
    IPeriodsService iPeriodsService;

    @ApiOperation("添加分期计划")
    @PostMapping("/inster")
    public ApiResult<PeriodsDO> AddPeriods(PeriodsDO periodsDO)
    {
        iPeriodsService.save(periodsDO);
        return ApiResult.success(periodsDO);
    }

    @ApiOperation("删除分期计划")
    @DeleteMapping("/delete/{id}")
    public ApiResult<Integer> DeletePeriods(@PathVariable Integer id)
    {
        iPeriodsService.removeById(id);
        System.out.println(id);
        return ApiResult.success(id);
    }

}
