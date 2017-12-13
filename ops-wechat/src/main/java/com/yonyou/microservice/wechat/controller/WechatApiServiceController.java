package com.yonyou.microservice.wechat.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.wechat.common.ApiVersionConsts;
import com.yonyou.microservice.wechat.service.TokenService;


@RestController
@RequestMapping(value = ApiVersionConsts.BASE_PATH+ApiVersionConsts.VERSION+"/wechatApiService" )
public class WechatApiServiceController {
	@Autowired
	private TokenService tokenService;
    @Autowired
    private RabbitTemplate rabbitTemplate;


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

	
}
