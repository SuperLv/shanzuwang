package com.shanzuwang.util;

import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * Created by Floki on 2017/9/29.
 */
@Service(value = "commonDataService")
public class CommonDataServiceManager implements CommonDataService {

    public static String ACCESS_TOKEN = "AccessToken:{0}";

    @Autowired
    private  RedisUtil redisUtil;

    /**
     * 将当前登录用户信息缓存到 Redis
     *
     * @param userDTO 当前登录用户信息
     */
    @Override
    public void putCurrentUserDataToRedis(ApiUserbillReq userDTO) {
        if (null == userDTO || StringUtils.isEmpty(userDTO.getToken())) {
            return;
        }
        String key = MessageFormat.format(ACCESS_TOKEN,userDTO.getToken());
        redisUtil.set(key,userDTO);
        redisUtil.expire(key, 60L * 60 * 8);
        //  redisUtil.expire(key, 10L);
    }

    @Override
    public void removeCurrentUserDataFromRedis(String token) {
        String key = MessageFormat.format(ACCESS_TOKEN, token);
        redisUtil.delKey(key);
    }

    @Override
    public ApiUserbillReq getCurrentUserDataFromRedis(String token) {
        String key = MessageFormat.format(ACCESS_TOKEN, token);
        ApiUserbillReq s= (ApiUserbillReq) redisUtil.get(key);
        return s;
    }

}
