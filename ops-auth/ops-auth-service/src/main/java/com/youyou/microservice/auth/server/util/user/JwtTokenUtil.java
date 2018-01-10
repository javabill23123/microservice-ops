package com.youyou.microservice.auth.server.util.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.cloud.common.jwt.JwtHelper;
import com.youyou.microservice.auth.server.service.impl.AuthServiceImpl;

/**
 *  @author joy
 */
@Component
public class JwtTokenUtil {
	private static Logger logger=Logger.getLogger(JwtTokenUtil.class);

    @Value("${jwt.expire}")
    private int expire;
    @Value("${jwt.pri-key.path}")
    private String priKeyPath;
    @Value("${jwt.pub-key.path}")
    private String pubKeyPath;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    public String generateToken(IJwtInfo jwtInfo) throws Exception {
    	logger.info("--generateToken,expire="+expire);
        return JwtHelper.generateToken(jwtInfo,priKeyPath,expire);
    }

    public IJwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token,pubKeyPath);
    }


}
