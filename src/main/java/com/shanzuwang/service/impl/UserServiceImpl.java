package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.UserQueryReq;
import com.shanzuwang.converter.UserConverter;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.dao.mapper.UserDao;
import com.shanzuwang.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-01-31
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements IUserService {

    @Override
    public PageInfo<UserDTO> getUserByPage(UserQueryReq queryReq) {
        Page<UserDO> page = new Page<>(queryReq.getPageNo(),queryReq.getPageSize());
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        if(queryReq.getId()!=null){
            queryWrapper.eq(UserDO::getId,queryReq.getId());
        }
        if(StringUtils.isNotBlank(queryReq.getName())){
            queryWrapper.like(UserDO::getName,queryReq.getName());
        }
        page(page,queryWrapper);
        List<UserDO> userDOList = page.getRecords();
        if(CollectionUtils.isEmpty(userDOList)){
            return new PageInfo<>(0,queryReq.getPageNo(),queryReq.getPageSize(),0);
        }
        return new PageInfo<>(page.getTotal(), UserConverter.convertDOToDTO(userDOList),
                queryReq.getPageNo(),queryReq.getPageSize(),page.getPages());
    }

    @Override
    public UserDTO login(String phone, String pwd) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getPhone,phone);
        UserDO user = getOne(queryWrapper);
        if(Optional.ofNullable(user).isPresent() && user.getPassword().equals(pwd)){
            return UserConverter.convertDOToDTO(user);
        }
        log.info("{}不存在或密码有误",phone);
        return null;
    }
}
