package com.yonyou.cloud.ops.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.yonyou.cloud.ops.notify.entity.NotifyMqMessage;

import net.sf.json.JSONObject;

@SpringBootApplication
@EnableSwagger2Doc
@EnableDiscoveryClient
public class NotifyApplication {

	public static void main(String[] args) {
//		JSONObject jObject=JSONObject.fromObject("{\"appId\":\"abc11\",\"bizId\":\"5566\"}");
//		NotifyThirdMessage msg = (NotifyThirdMessage)JSONObject.toBean(jObject,NotifyThirdMessage.class);
//		System.out.println(msg.getAppId());
		SpringApplication.run(NotifyApplication.class, args);
	}
}
