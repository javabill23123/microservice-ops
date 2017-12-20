package com.youyou.microservice.auth.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.xiaoleilu.hutool.json.JSONObject;
import com.yonyou.cloud.common.jwt.JWTInfo;
import com.youyou.microservice.auth.server.entity.AuthProvider;
import com.youyou.microservice.auth.server.util.user.JwtTokenUtil;

public class DynController implements Controller{
	private static Logger logger=Logger.getLogger(DynController.class);
	public static final String ACCEPT_USER="user";
	public static final String ACCEPT_USER_PASSWORD="userAndPassword";
	private List<AuthProvider> providers;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//	@Autowired
	private RestTemplate restTemplate;

	private AuthProvider getService(String name){
		for(AuthProvider i:providers){
			if(name.contains(i.getSrcUrl())){
				return i;
			}
		}
		return null;
	}
	private String getBody(HttpServletRequest request) {

			BufferedReader br;
			String str, wholeStr = "";
			try {
				br = request.getReader();
				while((str = br.readLine()) != null){
				wholeStr += str;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return wholeStr;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest p0, HttpServletResponse p1) throws Exception {
		logger.info("--DynController.handleRequest");
		if(restTemplate==null){
			StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));  
	        restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();  
		}
		String tmp=p0.getQueryString();
		String param="";
		if(tmp!=null && !"".equals(tmp)){
			param="?"+tmp;
		}
		String uri=p0.getRequestURI();
		logger.info("--DynController,uri="+uri);
		AuthProvider pInfo=this.getService(uri);
		if(pInfo!=null && !"".equals(pInfo.getAuthService())){
			logger.info("--DynController,service="+pInfo.getAuthService());
			
			String method=p0.getMethod();
			HttpMethod hm;
			if("GET".equals(method)){
				hm=HttpMethod.GET;
			}else{
				hm=HttpMethod.POST;
			}
			String body=this.getBody(p0);
//			JSONObject rBody=new JSONObject(body);
			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(body, headers);
			ResponseEntity<String> r=restTemplate.exchange(pInfo.getAuthService()+param, hm, entity, String.class);
			logger.info("--restTemplate,response="+r.getBody());
			JSONObject sk=new JSONObject(r.getBody());
	        String token = "{\"token\":\"";
	        String passWord=(String)sk.get("passWord");
	        String username=(String)sk.get("username");
	        String userId=(String)sk.get("userId");
	        String name=(String)sk.get("name");
	        if(userId==null || "".equals(userId)){
				logger.error("--DynController,login error");
	        	p1.getOutputStream().write((r.getBody()).getBytes());
	        	return null;
	        }
			if(pInfo.getAcceptType().equals(ACCEPT_USER)){
		        if (encoder.matches(passWord, passWord)) {
		            token = token+jwtTokenUtil.generateToken(new JWTInfo(username, userId, name));
		        }
			}else{
				token = token+jwtTokenUtil.generateToken(new JWTInfo(username, userId, name));
			}
			token=token+"\"}";
			p1.getOutputStream().write(token.getBytes());
		}
		return null;
	}

	public List<AuthProvider> getProviders() {
		return providers;
	}

	public void setProviders(List<AuthProvider> providers) {
		this.providers = providers;
	}

}
