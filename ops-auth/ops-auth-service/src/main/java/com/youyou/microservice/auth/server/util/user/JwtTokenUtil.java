package com.youyou.microservice.auth.server.util.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.common.jwt.IJWTInfo;
import com.yonyou.cloud.common.jwt.JWTHelper;

/**
 *  @author joy
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Value("${jwt.pri-key.path}")
    private String priKeyPath;
    @Value("${jwt.pub-key.path}")
    private String pubKeyPath;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo,priKeyPath,expire);
    }

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token,pubKeyPath);
    }


}
