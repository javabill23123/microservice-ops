package com.youyou.microservice.auth.server.dynauth.handler;

import com.youyou.microservice.auth.server.util.user.JwtAuthenticationDataResponse;
/**
 * 调用第三方接口返回数据处理接口
 * @author joy
 *
 */
public interface DynAuthHttpResultHandler {
	
	/**
	 * 处理http返回结果
	 * @author joy
	 * @param repBody http返回的String结果
	 * @param authCode request请求来的认证用的凭证
	 * @return
	 */
	public JwtAuthenticationDataResponse handlerHttpResult(String repBody,String authCode);

}
