package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.SpuReq;
import com.shanzuwang.bean.req.UserQueryReq;
import com.shanzuwang.converter.SpuConverter;
import com.shanzuwang.converter.UserConverter;
import com.shanzuwang.dao.dos.SpuDO;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.dao.mapper.SpuDao;
import com.shanzuwang.service.ISpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Slf4j
@Service
public class SpuServiceImpl extends ServiceImpl<SpuDao, SpuDO> implements ISpuService {


    /**
     * 分页查询商品列表
     *
     * @param spuReq 查询参数
     * @return 商品列表
     */
    @Override
    public PageInfo<SpuDto> getUserByPage(SpuReq spuReq) {
        Page<SpuDO> page = new Page<>(spuReq.getPageNo(),spuReq.getPageSize());
        LambdaQueryWrapper<SpuDO> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(spuReq.getName())){
            queryWrapper.like(SpuDO::getName,spuReq.getName());
        }
        page(page,queryWrapper);
        List<SpuDO> SpuDOList = page.getRecords();
        if(CollectionUtils.isEmpty(SpuDOList)){
            return new PageInfo<>(0,spuReq.getPageNo(),spuReq.getPageSize(),0);
        }
        return new PageInfo<SpuDto>(page.getTotal(),SpuConverter.convertDOToDTO(SpuDOList),
                spuReq.getPageNo(),spuReq.getPageSize(),page.getPages());
    }

    @Override
    public SpuDO getSpudo(SpuReq spuReq) {
        LambdaQueryWrapper<SpuDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SpuDO::getId,spuReq.getId());
        SpuDO spudo = getOne(queryWrapper);
        return spudo;
    }
}
