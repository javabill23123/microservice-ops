package com.youyou.microservice.auth.server.dynauth.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xiaoleilu.hutool.json.JSONObject;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.youyou.microservice.auth.server.dynauth.DynAuth;
import com.youyou.microservice.auth.server.dynauth.DynAuthService;
import com.youyou.microservice.auth.server.dynauth.handler.DynAuthHttpResultHanderFactory;
import com.youyou.microservice.auth.server.dynauth.handler.DynAuthHttpResultHandler;
import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationDataResponse;

/**
 * 自定义认证
 * http外部认证方式
 * 
 * @author BENJAMIN
 *
 */
@Service("dynAuthServiceHttpImpl")
@DynAuth(type = "http")
public class DynAuthServiceHttpImpl implements DynAuthService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private RestTemplate restTemplate;

	private static final String HTTP_GET = "GET";

	private static final String AUTH_CODE = "authCode";

	public JwtAuthenticationDataResponse auth(HttpServletRequest request, AuthProvider pInfo) {
		// 获取请求的信息 url后的参数 uri post/get body信息
		String queryString = request.getQueryString();
		String uri = request.getRequestURI();
		String method = request.getMethod();
		String body = this.getBody(request);
		String authCode = request.getParameter(AUTH_CODE);
		logger.info("--DynController,uri=" + uri);

		// 组装 http 请求
		// 1.charset
		if (restTemplate == null) {
			StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
			restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
		}
		// 2.http type
		HttpMethod hm = HTTP_GET.equals(method) ? HttpMethod.GET : HttpMethod.POST;
		// 3. param
		String param = "?" + queryString;
		// 4. 请求body
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		// 5.exchage
		ResponseEntity<String> rr = restTemplate.exchange(pInfo.getAuthService() + param, hm, entity, String.class);
		String repBody = rr.getBody();
		// 6.处理认证结果
		logger.info("--restTemplate,response=" + repBody);
		DynAuthHttpResultHanderFactory httpResultHandlerFactory = DynAuthHttpResultHanderFactory.getInstance();
		DynAuthHttpResultHandler httpResultHandler = httpResultHandlerFactory.createHandler(pInfo);
		return httpResultHandler.handlerHttpResult(repBody, authCode);
	}

	/**
	 * 获取request中body的值
	 * 
	 * @param request
	 * @return
	 */
	private String getBody(HttpServletRequest request) {
		BufferedReader br;
		String str, wholeStr = "";
		try {
			br = request.getReader();
			while ((str = br.readLine()) != null) {
				wholeStr += str;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return wholeStr;
	}

}
