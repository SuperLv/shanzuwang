package com.shanzuwang.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.dao.dos.PackageDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface PackageDao extends BaseMapper<PackageDO> {

    public List<PackagesReq>  ListPackagesReq(Page<PackagesReq> page, @Param("share")String share,@Param("status") String status);

    public PackagesReq GetPackagesReq(Integer id);

}
