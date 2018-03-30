package com.yonyou.cloud.ops.notify.util;

import java.nio.charset.Charset;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {

	private RestTemplate restTemplate;
	

	public String sendMessage(String url,String contentType,String bizId,String data){
		// 组装 http 请求
		// 1.charset
		if (restTemplate == null) {
			StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
			restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
		}
		// 2.http type
		HttpMethod hm = HttpMethod.POST;
		// 4. 请求body
		HttpHeaders headers = new HttpHeaders();
		if(contentType!=null && contentType.contains("-form")){
		    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		}
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		// 5.exchage
		ResponseEntity<String> rr = restTemplate.exchange(url+"?bizId="+bizId, hm, entity, String.class);
		String repBody = rr.getBody();
		return repBody;
	}
}
