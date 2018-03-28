package com.yonyou.cloud.ops.notify.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.service.NotifyThirdMessageService;

public class NotifyThirdMessageController extends BaseController<NotifyThirdMessageService, NotifyThirdMessage>{

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private NotifyThirdMessageService notifyThirdMessageService;
	@RequestMapping(value="/notify/thirdapi/message/{appId}/{bizId}",method=RequestMethod.GET)
	@YcApi
	public RestResultResponse<NotifyThirdMessage> sendNotification(@RequestParam("appId") String appId,
			@RequestParam("bizId") String bizId)
			throws Exception {
		logger.info("--NotifyThirdMessageController,appId="+appId+",bizId="+bizId);
		List<NotifyThirdMessage> list=notifyThirdMessageService.getNotifyMessage(appId, bizId);
		logger.info("--NotifyThirdMessageController,message size="+list.size());
		return new RestResultResponse<>().success(true).data(list);
	}
}
