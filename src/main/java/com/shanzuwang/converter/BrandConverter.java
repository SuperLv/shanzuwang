package com.shanzuwang.converter;

import com.shanzuwang.bean.dto.BrandDTO;
import com.shanzuwang.dao.dos.BrandDO;
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
    public static BrandDTO convertDOToDTO(BrandDO brandDO){
        BrandDTO brandDTO = new BrandDTO();
        BeanUtils.copyProperties(brandDO, brandDTO);
        return brandDTO;
    }

    public static List<BrandDTO> convertDOToDTO(List<BrandDO> brandDO){
        if(CollectionUtils.isEmpty(brandDO)){
            return Collections.emptyList();
        }
        return brandDO.stream().map(BrandConverter::convertDOToDTO).collect(Collectors.toList());
    }
}
