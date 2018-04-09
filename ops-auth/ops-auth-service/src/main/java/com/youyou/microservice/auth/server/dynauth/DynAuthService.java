package com.youyou.microservice.auth.server.dynauth;

import javax.servlet.http.HttpServletRequest;

import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationDataResponse;
/**
 * 动态认证服务接口
 * @author joy
 *
 */
public interface DynAuthService {
	/**
	 * 动态认证服务接口
	 * @param request
	 * @param pInfo
	 * @return 返回jwt相关信息
	 */
	public JwtAuthenticationDataResponse auth(HttpServletRequest request,AuthProvider pInfo); 

}
