package com.yonyou.cloud.ops.alert.ops.alert.rest;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.ops.alert.ops.alert.biz.AlertInfoBiz;
import com.yonyou.cloud.ops.alert.ops.alert.entity.RuleGroup;
 
@RestController
@RequestMapping("/test")
public class testRest {
	@Autowired
	AlertInfoBiz alertInfoBiz;

	 @RequestMapping(value = "",method = RequestMethod.POST)
	 @ResponseBody
	public void goRedis(HttpServletRequest request, HttpServletResponse  response,@RequestBody RuleGroup ru) { 
		try {
			ServletInputStream aa=request.getInputStream();
			Enumeration<String> parameterNames = request.getParameterNames();
			String vv=	parameterNames.nextElement();
			String[] tt=request.getParameterValues(vv);
			String ii=request.getParameter(vv);
			System.out.println("aa"+request.getParameterValues(vv));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		alertInfoBiz.sendMail();
	}
}
