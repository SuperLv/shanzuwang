package com.shanzuwang.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * jwt辅助类
 *
 * @author lv
 * @since 2020/2/2
 */
@Slf4j
public class JwtUtil {
    /**
     * 加密key
     */
    private static String jwtSecret;
    /**
     * 过期时间.单位秒
     */
    private static Integer expiration;
    /**
     * 默认的加密key
     */
    private static final String DEFAULT_JWT_SECRET = "jwt_token";

    /**
     * 获取jwt 加密key
     * @return jwt 加密key
     */
    private static String getJwtSecret(){
        if(StringUtils.isNotBlank(jwtSecret)){
            return jwtSecret;
        }
        jwtSecret = SpringContextHolder.getPropertiesFromSpringEnv("jwt.secret");
        if(StringUtils.isBlank(jwtSecret)){
            log.warn("---未设置jwt secret，使用默认的");
            jwtSecret = DEFAULT_JWT_SECRET;
        }
        return jwtSecret;
    }

    /**
     * 获取jwt 过期时间
     * @return
     */
    private static Integer getExpiration(){
        if(expiration == null){
            String expirationStr =  SpringContextHolder.getPropertiesFromSpringEnv("jwt.expiration");
            if(StringUtils.isBlank(expirationStr)){
                log.warn("---未设置jwt 过期时间，默认不过期");
                expiration = -1;
            }else{
                expiration = Integer.valueOf(expirationStr);
            }
        }
        return expiration;
    }

    /**
     * 生成jwt token
     * @param param 写入到token中的参数信息
     * @return jwt token
     */
    public static String encode(Map<String, Object> param) {
        //生成签名部分
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, getJwtSecret());
        jwtBuilder = jwtBuilder.setClaims(param);
        Integer expiration = getExpiration();
        if(expiration > 0){
            //设置过期时间
            Calendar c = Calendar.getInstance();
            c.add(Calendar.SECOND, expiration);
            jwtBuilder = jwtBuilder.setExpiration(c.getTime());
        }
        //获取生成的token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 根据token获取token中的参数信息
     * @param token jwt token
     * @return 写入到token中的参数信息
     */
    public static Map<String, Object> decode(String token){
        //可能抛出SignatureException异常表示token非法, ExpiredJwtException异常表示token过期
        return  Jwts.parser().setSigningKey(getJwtSecret()).parseClaimsJws(token).getBody();
    }

}
