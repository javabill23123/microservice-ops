package com.yonyou.cloud.ops.notify.mq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.service.NotifyThirdMessageService;

import net.sf.json.JSONObject;

public class ThirdMessageListener extends AbstractConsumerListener<NotifyThirdMessage>{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private NotifyThirdMessageService notifyThirdMessageService;
	
	@Override
	public void handleMessage(NotifyThirdMessage mes){
		logger.info("--handleMessage,收到数据,data="+JSONObject.fromObject(mes).toString());
		notifyThirdMessageService.sendMessage(mes,false);
		logger.info("--handleMessage,处理结束");
	}

}
