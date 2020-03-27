package com.shanzuwang.converter;

import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.dao.dos.UserDO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *用户信息转换器
 *
 * @author lv
 * @since 2020/2/1
 */
public class UserConverter {

    /**
     * 转换用户信息DO对象为DTO对象
     * @param userDO
     * @return
     */
    public static UserDTO convertDOToDTO(UserDO userDO){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO,userDTO);
        return userDTO;
    }

    public static List<UserDTO> convertDOToDTO(List<UserDO> userDOs){
        if(CollectionUtils.isEmpty(userDOs)){
            return Collections.emptyList();
        }
        return userDOs.stream().map(UserConverter::convertDOToDTO).collect(Collectors.toList());
    }
}
