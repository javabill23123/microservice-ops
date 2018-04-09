package com.youyou.microservice.auth.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.alibaba.fastjson.util.IOUtils;
import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.mapper.AuthProviderMapper;
import com.youyou.microservice.auth.server.util.client.ReflectionUtils;
/**
 * 动态配置的url更新接口，供ops-task定时调用
 * @author joy
 *
 */
@RestController
@RequestMapping("dynController")
public class DynResetConfigController {
	private static final String CONST_DYNCONTROLLER="DynController";
	private static final String CONST_HANDLERMAP="handlerMap";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SimpleUrlHandlerMapping mapping;
	@Autowired
	private AuthProviderMapper service;
	@Autowired
	DynController bean;

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
	public Map mapReset() {
		List<AuthProvider> list = service.selectAuthProviders();
		bean.setProviders(list);
		Map<String, Object> urlMap = new HashMap<String,Object>(list.size());
//		Map<String, ?> oldMap=mapping.getUrlMap();
		for (AuthProvider i : list) {
			logger.debug("重新加载第三方认证代理的url:"+i.getSrcUrl()+" 类型:"+i.getAcceptType());
//			if(oldMap.get(i.getSrcUrl())==null){
				urlMap.put(i.getSrcUrl(), bean);
//			}
		}
		if(urlMap.size()>0){
			List<String> tl=new ArrayList();
			Map<String, Object> map=(Map<String, Object>)ReflectionUtils.getFieldValue(mapping, CONST_HANDLERMAP);
			map.keySet().forEach(key->{
				if(map.get(key).toString().contains(CONST_DYNCONTROLLER)){
					tl.add(key);
				}
			});
			for(String key:tl){
				map.remove(key);
			}
			mapping.setUrlMap(urlMap);
			mapping.initApplicationContext();
		}
    	Map<String,String> map=new HashMap(16);
    	map.put("message", "ok");
		return map;
	}
    
    @RequestMapping(value = "api/demoUser", method = RequestMethod.POST)
	public String demoUser(HttpServletRequest p0) throws IOException {//, @RequestBody String body
    	String data="{\"username\":\"test\",\"userId\":1512345,\"name\":\"testName111111111111\",\"dealerName\":\"tes23423424sdfsdfsfsddfsfdsfsfsdfsddftName\",\"dealerCode\":\"testCode\",\"telPhone\":\"1111144333222342\"}";
    	logger.info("--demoUser,"+data);
    	return data;
    }
    
    @RequestMapping(value = "api/demoUser", method = RequestMethod.GET)
	public String demoUser2() {
    	String data="{\"username\":\"test\",\"userId\":150345,\"name\":\"testName111111111111\",\"dealerName\":\"tes23423424sdfsdfsfsddfsfdsfsfsdfsddftName\",\"dealerCode\":\"testCode\",\"telPhone\":\"1111144333222342\",\"openid\":\"openid123456\"}";
    	data="{\"birthday\":\"\",\"sex\":\"null\",\"status\":\"0\",\"dealerName\":\"0\",\"passWord\":\"27008a2f874037cc555da69711258e92\",\"username\":\"13817482782\",\"address\":\"null\",\"telPhone\":\"13817482782\",\"description\":\"\",\"userId\":177847,\"name\":\"夏银龙\",\"dealerCode\":\"31A20\",\"resultCode\":\"1\",\"communtiyFlag\":\"0\",\"uuid\":\"181829\",\"openid\":\"openid123456\",\"kickOut\":false}";
    	logger.info("--demoUser,"+data);
    	return data;
    }
    
    @RequestMapping(value = "demoHeader", method = RequestMethod.GET)
	public String demoUser3(HttpServletRequest p0) {
    	String data="";
    	StringBuilder sb=new StringBuilder();
    	Enumeration<String> heads=p0.getHeaderNames();
    	while (heads.hasMoreElements()){
    		String name=heads.nextElement();
//    		data=data+name+"="+p0.getHeader(name)+",";
    		sb.append(name+"="+p0.getHeader(name)+",");
    	}
    	return sb.toString();
//    	return data;
    }
    
    @RequestMapping(value = "demoHeader", method = RequestMethod.POST)
	public String demoUser4(HttpServletRequest p0) {
    	String data="";
    	StringBuilder sb=new StringBuilder();
    	Enumeration<String> heads=p0.getHeaderNames();
    	while (heads.hasMoreElements()){
    		String name=heads.nextElement();
    		sb.append(name+"="+p0.getHeader(name)+",");
//    		data=data+name+"="+p0.getHeader(name)+",";
    	}
    	logger.info(sb.toString());
		String body="";
		String tmp="";
    	StringBuilder sb2=new StringBuilder();
		Enumeration<String> map=p0.getParameterNames();
		while ( map.hasMoreElements() ){
			String name=map.nextElement();
			String[] values=p0.getParameterValues(name);
			for(int i=0 ;i<values.length;i++){
//				body=body+tmp+name+"="+values[i];
	    		sb2.append(tmp+name+"="+values[i]);
				tmp="&";
			}
		}
    	logger.info(sb2.toString());
    	return data;
    }
}
