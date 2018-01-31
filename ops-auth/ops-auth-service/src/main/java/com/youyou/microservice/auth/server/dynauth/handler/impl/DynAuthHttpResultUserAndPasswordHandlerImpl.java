package com.youyou.microservice.auth.server.dynauth.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.json.JSONObject;
import com.yonyou.cloud.common.jwt.JwtInfo;
import com.youyou.microservice.auth.server.dynauth.handler.DynAuthHttpResultHandler;
import com.youyou.microservice.auth.server.dynauth.handler.DynHttpAuthHandler;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationDataResponse;
import com.youyou.microservice.auth.server.util.user.JwtTokenUtil;

/**
 * 处理http返回结果 userAndPassword 类型由外系统自行校验
 * 
 * @author BENJAMIN
 *
 */
@Service
@DynHttpAuthHandler(type = "userAndPassword")
public class DynAuthHttpResultUserAndPasswordHandlerImpl implements DynAuthHttpResultHandler {

	private static final String REPBODY_USERNAME = "username";
	private static final String REPBODY_USERID = "userId";
	private static final String REPBODY_NAME = "name";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public JwtAuthenticationDataResponse handlerHttpResult(String repBody, String authCode) {
		JSONObject sk = new JSONObject(repBody);
		String jwt = "";
		String username = (String) sk.get(REPBODY_USERNAME);
		String userId = (String) sk.get(REPBODY_USERID);
		String name = (String) sk.get(REPBODY_NAME);
		if (userId == null || "".equals(userId)) {
			return new JwtAuthenticationDataResponse("", repBody);
		}
		
		try {
			//直接根据用户信息返回jwt
			jwt = jwtTokenUtil.generateToken(new JwtInfo(username, userId, name));
		} catch (Exception e) {
			return new JwtAuthenticationDataResponse("", repBody);
		}
		return new JwtAuthenticationDataResponse(jwt, repBody);
	}

}
