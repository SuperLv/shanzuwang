package com.shanzuwang.web.bill;

import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.bill.CartReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.config.annotation.User;
import com.shanzuwang.dao.dos.CartDO;
import com.shanzuwang.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hw
 * 20/05/13 15:03
 */
@Api(tags = "购物车管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    ICartService iCartService;

    @ApiOperation("购物车列表")
    @GetMapping("/")
//    @ApiImplicitParams({
//            @ApiImplicitParam( name = "access-token", value = "token", paramType = "header", dataType = "String", required = true )
//    })
//    @User UserDTO currentUser
    public ApiResult<List<CartReq>> ListCart(String userid)
    {
        return ApiResult.success(iCartService.ListCarS(userid));
    }

    @ApiOperation("添加购物车")
    @PostMapping("/carts")
    public ApiResult<CartDO> AddCart(@RequestBody CartDO cartDO)
    {
        return ApiResult.success(iCartService.AddCart(cartDO));
    }

    @ApiOperation("修改数量")
    @PatchMapping("/carts/{id}")
    public ApiResult<CartDO> UpdateCart(@PathVariable Integer id,Integer product_num)
    {
        CartDO cartDO=iCartService.getById(id);
        cartDO.setId(id);
        cartDO.setProductNum(cartDO.getProductNum()+product_num);
        iCartService.updateById(cartDO);
        return ApiResult.success(cartDO);
    }

    @ApiOperation("删除")
    @DeleteMapping("/carts/{id}")
    public void DeleteCart(@PathVariable Integer id)
    {
        iCartService.removeById(id);
    }
}
