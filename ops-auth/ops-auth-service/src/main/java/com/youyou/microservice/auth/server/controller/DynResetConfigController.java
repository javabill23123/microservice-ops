package com.youyou.microservice.auth.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.mapper.AuthProviderMapper;

@RestController
@RequestMapping("dynController")
public class DynResetConfigController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SimpleUrlHandlerMapping mapping;
	@Autowired
	private AuthProviderMapper service;

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
	public Map mapReset() {
		List<AuthProvider> list = service.selectAuthProviders();
		DynController bean = new DynController();
		bean.setProviders(list);
		Map<String, Object> urlMap = new HashMap<String,Object>(list.size());
		for (AuthProvider i : list) {
			logger.debug("重新加载第三方认证代理的url:"+i.getSrcUrl()+" 类型:"+i.getAcceptType());
			urlMap.put(i.getSrcUrl(), bean);
		}
		mapping.setUrlMap(urlMap);
		mapping.initApplicationContext();
    	Map<String,String> map=new HashMap();
    	map.put("message", "ok");
		return map;
	}
    
    @RequestMapping(value = "demoUser", method = RequestMethod.POST)
	public String demoUser() {
    	String data="{\"username\":\"test\",\"userId\":\"2005\",\"name\":\"testName\"}";
    	logger.info("--demoUser,"+data);
    	return data;
    }
}
