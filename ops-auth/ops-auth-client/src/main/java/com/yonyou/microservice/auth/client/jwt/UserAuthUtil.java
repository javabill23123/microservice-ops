package com.yonyou.microservice.auth.client.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.cloud.common.jwt.JwtHelper;
import com.yonyou.microservice.auth.client.config.UserAuthConfig;
import com.yonyou.microservice.auth.client.exception.JwtIllegalArgumentException;
import com.yonyou.microservice.auth.client.exception.JwtSignatureException;
import com.yonyou.microservice.auth.client.exception.JwtTokenExpiredException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * @author joy
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;
    public IJwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, userAuthConfig.getPubKeyPath());
        }catch (ExpiredJwtException ex){
            throw new JwtTokenExpiredException("User token expired!");
        }catch (SignatureException ex){
            throw new JwtSignatureException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new JwtIllegalArgumentException("User token is null or empty!");
        }
    }
}
