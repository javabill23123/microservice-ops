package com.youyou.microservice.auth.server.dynauth;

import javax.servlet.http.HttpServletRequest;

import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationDataResponse;

public interface DynAuthService {
	
	public JwtAuthenticationDataResponse auth(HttpServletRequest request,AuthProvider pInfo); 

}
