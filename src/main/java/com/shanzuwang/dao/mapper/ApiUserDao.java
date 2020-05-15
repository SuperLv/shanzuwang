package com.shanzuwang.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    public List<ApiUserbillReq> ListUserbill(Page<ApiUserDO> pageReq, @Param("apiUserbillReq") ApiUserbillReq apiUserbillReq);

}
