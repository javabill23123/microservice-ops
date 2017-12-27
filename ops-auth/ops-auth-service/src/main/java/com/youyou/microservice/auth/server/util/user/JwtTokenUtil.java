package com.youyou.microservice.auth.server.util.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.cloud.common.jwt.JwtHelper;

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

    public String generateToken(IJwtInfo jwtInfo) throws Exception {
        return JwtHelper.generateToken(jwtInfo,priKeyPath,expire);
    }

    public IJwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token,pubKeyPath);
    }


}
