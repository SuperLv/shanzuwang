package com.shanzuwang.util;

import com.shanzuwang.bean.dto.UserDTO;

/**
 * Created by Floki on 2017/9/29.
 */
public interface CommonDataService {

    /**
     * 将当前登录用户信息缓存到 Redis
     *
     * @param userDTO 当前登录用户信息
     */
    void putCurrentUserDataToRedis(UserDTO userDTO);

    /**
     * 从 Redis 中移除当前用户数据
     *
     * @param token 缓存当前用户数据的key
     */
    void removeCurrentUserDataFromRedis(String token);

    /**
     * 从 Redis 中获取当前用户数据
     *
     * @param token 缓存当前用户数据的key
     * @return 返回当前用户数据
     */
    UserDTO getCurrentUserDataFromRedis(String token);
}
