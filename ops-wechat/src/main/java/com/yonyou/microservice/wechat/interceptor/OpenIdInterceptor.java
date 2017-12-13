/*
* Copyright 2016 Yonyou Auto Information Technology（Shanghai） Co., Ltd. All Rights Reserved.
*
* This software is published under the terms of the YONYOU Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : marketing-wechat-open-api
*
* @File name : ComponentWebPageAuthInterceptor.java
*
* @Author : LiuJun
*
* @Date : 2016年12月7日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年12月7日    LiuJun    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.yonyou.microservice.wechat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yonyou.cloud.common.jwt.JWTHelper;
import com.yonyou.cloud.common.jwt.JWTInfo;
import com.yonyou.cloud.common.jwt.StringHelper;
import com.yonyou.cloud.common.vo.user.UserInfo;
import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;
import com.yonyou.microservice.wechat.exception.WechatException;
import com.yonyou.microservice.wechat.service.MenuUrlService;
import com.yonyou.microservice.wechat.service.TokenService;
import com.yonyou.microservice.wechat.util.copy.CookieUtil;


/**
*
* @author LiuJun
* 代公众号发起网页授权-拦截器
* @date 2016年12月7日
*/
public class OpenIdInterceptor implements HandlerInterceptor{
    
    private Logger logger=Logger.getLogger(OpenIdInterceptor.class);
    @Value("${jwt.expire:30}")
    private int expire;
    @Value("${jwt.pri-key.path}")
    private String priKeyPath;
    @Value("${openid.user.map-code}")
    private String userMapCode;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenService tokenService;
    @Autowired
    private MenuUrlService menuUrlService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        
        String code  = request.getParameter("code");
//        String appid  = request.getParameter("appid");
        logger.info("------receivecode:"+code+",url="+request.getRequestURL()+",uri="+request.getRequestURI());
        if(request.getRequestURI().contains("/error"))
        	return true;
        //测试token工具url
        if(request.getRequestURI().contains("parsetoken"))
        	return true;
        if(request.getRequestURI().contains("redirect"))
        	return true;
        //微信验证、事件回调接口
        if(request.getRequestURI().contains("wechat/callback"))
            	return true;
        //网关做认证页面，把认证信息写入cookie
        if(!StringHelper.isNullOrEmpty(code)){
        	writeWechatCookiesInfo(code,"",request,response);
            
        }else{ //业务请求没有经过zuul，而是在网关的controller转发，这时basecontroller中提供从cookie取身份的方法
        	String openId=CookieUtil.getOpenid(request);
        	if(StringHelper.isNullOrEmpty(openId)){
        		logger.info("拦截了cookie中没有openId的请求");
        		return false;
        	}
        	return true;
        }
        
        return true;
    }

    /**
    *
    * @author LiuJun
    * @date 2016年12月7日
    * @param request
    * @param response
    * @param handler
    * @param modelAndView
    * @throws Exception
    * (non-Javadoc)
    * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
    */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }

    /**
    *
    * @author LiuJun
    * @date 2016年12月7日
    * @param request
    * @param response
    * @param handler
    * @param ex
    * @throws Exception
    * (non-Javadoc)
    * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
    */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    public void writeWechatCookiesInfo(String code, String appid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	logger.info("writeWechatCookiesInfo is entry,code=" + code + ";appid=" + appid);
    	try{
    		String uri=request.getRequestURI();
    		String[] us=uri.split("/");
    		String serviceNo=us[us.length-2];
    		String openId = tokenService.getOpenidByCode(serviceNo,code);
        	if(StringHelper.isNullOrEmpty(openId)) {
            	logger.info("gateway-wechat,openid is null="+openId);
        		return;
        	}
    		MenuUrlInfo m=menuUrlService.getMenuUrl(userMapCode);
    		if(m==null)
    			throw WechatException.WECHAT_USER_URL_NOT_FIND;
    		String url=m.getUrl()+"?openid="+openId;
    		ResponseEntity<UserInfo> r=restTemplate.getForEntity(url, UserInfo.class);
        	JWTInfo jwtInfo=new JWTInfo(r.getBody().getUsername(), r.getBody().getId() + "", r.getBody().getName());
        	String jwt=JWTHelper.generateToken(jwtInfo,priKeyPath,expire);
        	logger.info("--gateway-wechat,openid="+openId);
    		logger.info("--write cookie jwt="+jwt);
    		CookieUtil.writeOpenid(jwt, response);
    	}catch(Exception e){
    		
    	}
    }    
}
