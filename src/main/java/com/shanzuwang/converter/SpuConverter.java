package com.shanzuwang.converter;

import com.shanzuwang.bean.dto.SpuDTO;
import com.shanzuwang.dao.dos.SpuDO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiWeijie
 * 20/04/02 16:05
 */
public class SpuConverter {
    /**
     * 转换DO对象为DTO对象
     * @param spuDO
     * @return
     */
    public static SpuDTO convertDOToDTO(SpuDO spuDO){
        SpuDTO spuDTO = new SpuDTO();
        BeanUtils.copyProperties(spuDO,spuDTO);
        return spuDTO;
    }

    public static List<SpuDTO> convertDOToDTO(List<SpuDO> spuDO){
        if(CollectionUtils.isEmpty(spuDO)){
            return Collections.emptyList();
        }
        return spuDO.stream().map(SpuConverter::convertDOToDTO).collect(Collectors.toList());
    }
}
