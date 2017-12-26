package com.youyou.microservice.auth.server.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.yonyou.cloud.common.exception.GlobalExceptionHandler;
import com.yonyou.microservice.gate.common.vo.user.AuthProviderInfo;
import com.youyou.microservice.auth.server.controller.DynController;
import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.mapper.AuthProviderMapper;

/**
 *  @author joy
 */
@Configuration
public class AuthConfiguration {

	 @Bean
	 public  RestTemplate restTemplate(){
		 return new RestTemplate(); 
	 }
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    public DynController dynController(SimpleUrlHandlerMapping mapping2,AuthProviderMapper service){
    	List<AuthProvider> list=service.selectAuthProviders();
    	DynController bean=new DynController();
    	bean.setProviders(list);
    	Map<String,Object> urlMap=new HashMap(list.size());
    	for(AuthProvider i:list){
        	urlMap.put(i.getSrcUrl(), bean);
    	}
    	mapping2.setUrlMap(urlMap);
    	mapping2.initApplicationContext();
    	return bean;
    }
}
