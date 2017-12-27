package com.yonyou.microservice.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.wechat.common.ApiVersionConsts;
import com.yonyou.microservice.wechat.service.TokenService;
import com.yonyou.microservice.wechat.service.WechatMessageService;

import net.sf.json.JSONObject;

/**
 * 
 * @author Richard
 *
 */
@RestController
@RequestMapping(value = ApiVersionConsts.BASE_PATH+ApiVersionConsts.VERSION+"/wechatApiService" )
public class WechatApiServiceController {
    private static Logger logger = Logger.getLogger(WechatApiServiceController.class);
	@Autowired
	private TokenService tokenService;
    @Autowired
    private WechatMessageService wechatMessageService;


	@YcApi
	@RequestMapping(value = "/getOpenid/{serviceNo}/{code}", method = RequestMethod.GET,consumes = "application/json;UTF-8")
	public RestResultResponse getOpenidByCode(@PathVariable("serviceNo") String serviceNo,
			@PathVariable("code") String code) {
		String d=tokenService.getOpenidByCode(serviceNo,code);
		return new RestResultResponse<String>().success(true).data(d);
	}

	@YcApi
	@RequestMapping(value = "/getToken/{serviceNo}", method = RequestMethod.GET,consumes = "application/json;UTF-8")
	public RestResultResponse getToken(@PathVariable("serviceNo") String serviceNo) {
		String d=tokenService.getAccessToken(serviceNo);
		return new RestResultResponse<String>().success(true).data(d);
	}


	@RequestMapping(value = "/genToken/{serviceNo}", method = RequestMethod.GET)
	@ResponseBody
	public String genToken(@PathVariable("serviceNo") String serviceNo) {
		Map<String,String> map =new HashMap(10);
		String tmp=tokenService.genAccessToken(1,serviceNo);
		map.put("token", tmp);
		map.put("name", "success");
		logger.info("--genToken:"+tmp);
		JSONObject obj=JSONObject.fromObject(map);
		return obj.toString();
	}
    
	@RequestMapping(value = "/wechat-push/api/v1/user/{serviceNo}/{openid}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserByopenid(@PathVariable("serviceNo") String serviceNo,
			@PathVariable("openid") String openid) {
		return wechatMessageService.getUser(serviceNo,openid);
	}
}
