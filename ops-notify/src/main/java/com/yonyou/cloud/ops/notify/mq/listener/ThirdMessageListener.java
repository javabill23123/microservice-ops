package com.yonyou.cloud.ops.notify.mq.listener;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;
import com.yonyou.cloud.ops.notify.consts.NotifyConsts;
import com.yonyou.cloud.ops.notify.entity.NotifyAppUrl;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.mapper.NotifyAppUrlMapper;
import com.yonyou.cloud.ops.notify.service.NotifyThirdMessageService;

@Component
public class ThirdMessageListener extends AbstractConsumerListener<NotifyThirdMessage>{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private NotifyThirdMessageService notifyThirdMessageService;
	
	@Autowired
	private NotifyAppUrlMapper notifyAppUrlMapper;


	private RestTemplate restTemplate;
	
	@Override
	public void handleMessage(NotifyThirdMessage mes)  {
		String url=mes.getNotifyUrl();
		String appId=mes.getAppId();
		List<NotifyAppUrl> list=notifyAppUrlMapper.getAppUrl(appId);
		String contentType="";
		if(url==null || "".equals(url)){
			if(list.size()==1){
				url=list.get(0).getUrl();
				contentType=list.get(0).getContentType();
			}else{
				logger.error("error:app has no callbak url or size>1");
				return;
			}
		}
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
		HttpEntity<String> entity = new HttpEntity<String>(mes.getData(), headers);
		// 5.exchage
		ResponseEntity<String> rr = restTemplate.exchange(url+"?bizId="+mes.getBizId(), hm, entity, String.class);
		String repBody = rr.getBody();
		//返回内容包含 1001002 代表执行成功，更新消息状态
		if(repBody!=null && !"".equals(repBody) && repBody.contains(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS)){
			notifyThirdMessageService.updateNotisyMessageByMeskey(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS,
					mes.getMsgKey());
		}
	}

}
