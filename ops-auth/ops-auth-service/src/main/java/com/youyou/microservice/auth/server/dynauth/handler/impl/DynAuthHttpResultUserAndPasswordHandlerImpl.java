package com.youyou.microservice.auth.server.dynauth.handler.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.json.JSONObject;
import com.yonyou.cloud.common.jwt.IJwtInfo;
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
	private static final String REPBODY_KICKOUT = "kickOut";
	
	@Value("${jwt.params}")
	private String jwtParams;

    @Autowired
    private RedisTemplate redisTemplate;
	@Value("${redis.expire}")
    private int expire;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public JwtAuthenticationDataResponse handlerHttpResult(String repBody, String authCode) {
		logger.info("userAndPassword 逻辑处理http返回结果");
		JSONObject sk = new JSONObject(repBody);
		String jwt = "";
		String username = (String) sk.get(REPBODY_USERNAME);
		Integer userId = (Integer) sk.get(REPBODY_USERID);
		String name = (String) sk.get(REPBODY_NAME);
		String dealerName = (String) sk.get(REPBODY_DEALER_NAME);
		String dealerCode = (String) sk.get(REPBODY_DEALER_CODE);
		String telPhone = (String) sk.get(REPBODY_TELPHONE);
		//接口返回是否互踢参数
		Boolean kickOut=(Boolean) sk.get(REPBODY_KICKOUT);
		if(kickOut==null){
			kickOut=true;
		}
		//动态读取接口返回的各个参数值
		String[] paramNames=jwtParams.split(",");
		Map<String ,String> map=new HashMap(16);
		for(int i=0;i<paramNames.length;i++){
			String value = (String) sk.get(paramNames[i]);
			map.put(paramNames[i], value);
		}
		
		if (userId == null || "".equals(userId)) {
			return new JwtAuthenticationDataResponse("", sk);
		}
		
		try {
    		IJwtInfo info=new JwtInfo(username, userId.toString(), name,dealerCode,dealerName,telPhone,
					kickOut,map,"");
			//直接根据用户信息返回jwt
			jwt = jwtTokenUtil.generateToken(info);
    		//根据jwt缓存用户信息
    		redisTemplate.opsForValue().set(jwt, info, expire, TimeUnit.SECONDS); 
    		logger.info("--缓存信息jwt,"+jwt);
    		String loginName=info.getUniqueName();
    		//根据用户账号缓存有效的jwt，踢掉其它的jwt
    		redisTemplate.opsForValue().set(loginName, jwt, expire, TimeUnit.SECONDS); 
    		logger.info("--用登录账号缓存有效的jwt,loginName="+loginName);
		} catch (Exception e) {
			logger.error("生成jwt失败",e);
			return new JwtAuthenticationDataResponse("", sk);
		}
		return new JwtAuthenticationDataResponse(jwt, sk);
	}

}
