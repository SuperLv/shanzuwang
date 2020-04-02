package com.shanzuwang.converter;

import com.shanzuwang.bean.dto.BrandDto;
import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.SpuDO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiWeijie
 * 20/04/02 16:44
 */

public class BrandConverter {
    /**
     * 转换DO对象为DTO对象
     * @param brandDO
     * @return
     */
    public static BrandDto convertDOToDTO(BrandDO brandDO){
        BrandDto brandDto = new BrandDto();
        BeanUtils.copyProperties(brandDO,brandDto);
        return brandDto;
    }

    public static List<BrandDto> convertDOToDTO(List<BrandDO> brandDO){
        if(CollectionUtils.isEmpty(brandDO)){
            return Collections.emptyList();
        }
        return brandDO.stream().map(BrandConverter::convertDOToDTO).collect(Collectors.toList());
    }
}
