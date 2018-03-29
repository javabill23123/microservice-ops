package com.yonyou.cloud.ops.notify.mq.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;
import com.yonyou.cloud.ops.notify.entity.NotifyAppUrl;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.service.NotifyAppUrlService;
import com.yonyou.cloud.ops.notify.service.NotifyThirdMessageService;

import net.sf.json.JSONObject;

public class ThirdMessageListener extends AbstractConsumerListener<NotifyThirdMessage>{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private NotifyThirdMessageService notifyThirdMessageService;
	
	@Override
	public void handleMessage(NotifyThirdMessage mes)  {
		logger.info("--handleMessage,data="+JSONObject.fromObject(mes).toString());
		notifyThirdMessageService.sendMessage(mes);
	}

}
