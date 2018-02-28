package com.youyou.microservice.auth.server.dynauth.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service("userAndPassword")
@DynHttpAuthHandler(type = "userAndPassword")
public class DynAuthHttpResultUserAndPasswordHandlerImpl implements DynAuthHttpResultHandler {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String REPBODY_USERNAME = "username";
	private static final String REPBODY_USERID = "userId";
	private static final String REPBODY_NAME = "name";
	private static final String REPBODY_DEALER_NAME = "dealerName";
	private static final String REPBODY_DEALER_CODE = "dealerCode";
	private static final String REPBODY_TELPHONE = "telPhone";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public JwtAuthenticationDataResponse handlerHttpResult(String repBody, String authCode) {
		logger.info("userAndPassword 逻辑处理http返回结果");
		JSONObject sk = new JSONObject(repBody);
		String jwt = "";
		String username = (String) sk.get(REPBODY_USERNAME);
		Long userId = (Long) sk.get(REPBODY_USERID);
		String name = (String) sk.get(REPBODY_NAME);
		String dealerName = (String) sk.get(REPBODY_DEALER_NAME);
		String dealerCode = (String) sk.get(REPBODY_DEALER_CODE);
		String telPhone = (String) sk.get(REPBODY_TELPHONE);
		if (userId == null || "".equals(userId)) {
			return new JwtAuthenticationDataResponse("", sk);
		}
		
		try {
			//直接根据用户信息返回jwt
			jwt = jwtTokenUtil.generateToken(new JwtInfo(username, userId.toString(), name,dealerCode,dealerName,telPhone,""));
		} catch (Exception e) {
			logger.error("生成jwt失败",e);
			return new JwtAuthenticationDataResponse("", sk);
		}
		return new JwtAuthenticationDataResponse(jwt, sk);
	}

}
