package com.shanzuwang.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.dto.SpuDTO;
import com.shanzuwang.bean.req.product.SpuQueryRep;
import com.shanzuwang.dao.dos.SpuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Repository
public interface SpuDao extends BaseMapper<SpuDO> {

    List<SpuDTO> findList(Page<SpuDO> page, @Param("spuReq") SpuQueryRep spuReq);
}
