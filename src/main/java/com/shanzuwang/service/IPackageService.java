package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.PackagesAddReq;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.PackageDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IPackageService extends IService<PackageDO> {
    public PageInfo<PackagesReq> ListOrders(Query query);

    public PackagesReq GetPackages(Integer id);

    public PackagesReq AddPackages(PackagesAddReq packagesAddReq);
}
