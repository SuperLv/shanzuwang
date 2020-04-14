package com.shanzuwang.converter;

import com.shanzuwang.bean.dto.SpuDTO;
import com.shanzuwang.dao.dos.SpuDO;
import org.springframework.beans.BeanUtils;

/**
 * Created by Hw
 * 20/04/09 14:48
 */
public class SkuConverter {

    public static SpuDTO convertDOToDTO(SpuDO spuDO){
        SpuDTO spuDTO = new SpuDTO();
        BeanUtils.copyProperties(spuDO,spuDTO);
        return spuDTO;
    }
}
