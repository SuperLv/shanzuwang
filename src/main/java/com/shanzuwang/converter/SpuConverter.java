package com.shanzuwang.converter;

import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.dao.dos.SpuDO;
import com.shanzuwang.dao.dos.UserDO;
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
    public static SpuDto  convertDOToDTO(SpuDO spuDO){
        SpuDto spuDTO = new SpuDto();
        BeanUtils.copyProperties(spuDO,spuDTO);
        return spuDTO;
    }

    public static List<SpuDto> convertDOToDTO(List<SpuDO> spuDO){
        if(CollectionUtils.isEmpty(spuDO)){
            return Collections.emptyList();
        }
        return spuDO.stream().map(SpuConverter::convertDOToDTO).collect(Collectors.toList());
    }
}
