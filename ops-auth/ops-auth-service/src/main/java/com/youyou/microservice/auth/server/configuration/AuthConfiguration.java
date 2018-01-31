package com.youyou.microservice.auth.server.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.yonyou.cloud.common.exception.GlobalExceptionHandler;
import com.youyou.microservice.auth.server.controller.DynController;
import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.mapper.AuthProviderMapper;

/**
 * @author joy
 */
@Configuration
public class AuthConfiguration {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public GlobalExceptionHandler getGlobalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	/**
	 * 配置动态controller会handler的url
	 * 
	 * @param mapping
	 * @param service
	 * @return
	 */
	@Bean
	public DynController dynController(SimpleUrlHandlerMapping mapping, AuthProviderMapper service) {
		List<AuthProvider> list = service.selectAuthProviders();
		DynController bean = new DynController();
		bean.setProviders(list);
		Map<String, Object> urlMap = new HashMap<String,Object>(list.size());
		for (AuthProvider i : list) {
			logger.debug("加载第三方认证代理的url:"+i.getSrcUrl()+" 类型:"+i.getAcceptType());
			urlMap.put(i.getSrcUrl(), bean);
		}
		mapping.setUrlMap(urlMap);
		mapping.initApplicationContext();
		return bean;
	}
}
