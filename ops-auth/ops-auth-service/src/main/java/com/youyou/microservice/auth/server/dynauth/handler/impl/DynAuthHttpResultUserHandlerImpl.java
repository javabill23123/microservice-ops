package com.youyou.microservice.auth.server.dynauth.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.json.JSONObject;
import com.yonyou.cloud.common.jwt.JwtInfo;
import com.youyou.microservice.auth.server.dynauth.handler.DynAuthHttpResultHandler;
import com.youyou.microservice.auth.server.dynauth.handler.DynHttpAuthHandler;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationDataResponse;
import com.youyou.microservice.auth.server.util.user.JwtTokenUtil;

/**
 * 处理http返回结果
 * 
 * user类型 需要ops系统进行检验密码是否正确
 * 
 * @author BENJAMIN
 *
 */
@Service
@DynHttpAuthHandler(type = "user")
public class DynAuthHttpResultUserHandlerImpl implements DynAuthHttpResultHandler {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	private static final String REPBODY_PASSWORD = "passWord";
	private static final String REPBODY_USERNAME = "username";
	private static final String REPBODY_USERID = "userId";
	private static final String REPBODY_NAME = "name";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public JwtAuthenticationDataResponse handlerHttpResult(String repBody, String authCode) {
		JSONObject sk = new JSONObject(repBody);
		String jwt = "";
		String passWord = (String) sk.get(REPBODY_PASSWORD);
		String username = (String) sk.get(REPBODY_USERNAME);
		String userId = (String) sk.get(REPBODY_USERID);
		String name = (String) sk.get(REPBODY_NAME);
		if (userId == null || "".equals(userId)) {
			return new JwtAuthenticationDataResponse("", repBody);
		}
		// user类型 需要判断来的凭证和respbody中的password是否一致
		if (encoder.matches(authCode, passWord)) {
			try {
				jwt = jwtTokenUtil.generateToken(new JwtInfo(username, userId, name));
			} catch (Exception e) {
				return new JwtAuthenticationDataResponse("", repBody);
			}
		}
		return new JwtAuthenticationDataResponse(jwt, repBody);

	}

}
