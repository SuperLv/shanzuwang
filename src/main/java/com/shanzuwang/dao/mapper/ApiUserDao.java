package com.shanzuwang.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.req.bill.UserbillReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanzuwang.dao.dos.SpuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface ApiUserDao extends BaseMapper<ApiUserDO> {

    public List<UserbillReq> ListUserbill(Page<ApiUserDO> pageReq, @Param("userbillReq") UserbillReq userbillReq);

}
