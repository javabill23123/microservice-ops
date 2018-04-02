package com.youyou.microservice.auth.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.cloud.common.jwt.JwtHelper;
import com.yonyou.microservice.auth.client.exception.JwtIllegalArgumentException;
import com.yonyou.microservice.auth.client.exception.JwtSignatureException;
import com.yonyou.microservice.auth.client.exception.JwtTokenExpiredException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
@Service
public class AuthUtilService {

	@Value("${jwt.params}")
	private String jwtParams;

	@Value("${auth.user.pub-key.path}")
	private String pubKeyPath;
	
    public IJwtInfo getInfoFromToken(String token) throws Exception {
        try {
        	//动态读取接口返回的各个参数值
    		String[] paramNames=jwtParams.split(",");
            return JwtHelper.getInfoFromToken(token, pubKeyPath,paramNames);
        }catch (ExpiredJwtException ex){
            throw new JwtTokenExpiredException("User token expired!");
        }catch (SignatureException ex){
            throw new JwtSignatureException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new JwtIllegalArgumentException("User token is null or empty!");
        }
    }
}
