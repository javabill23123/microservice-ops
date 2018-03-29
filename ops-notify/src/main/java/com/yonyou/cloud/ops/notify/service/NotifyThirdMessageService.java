package com.yonyou.cloud.ops.notify.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;

import tk.mybatis.mapper.common.Mapper;

@Service
public class NotifyThirdMessageService  extends BaseService<Mapper<NotifyThirdMessage>, NotifyThirdMessage>{

	public List<NotifyThirdMessage> getNotifyMessage(String appId, String bizId) {
		return this.getNotifyMessage(appId, bizId);
	}
	
	@Transactional(rollbackFor= Exception.class)
    public void updateNotisyMessageByMeskey(String status,String msgKey){
    	this.updateNotisyMessageByMeskey(status, msgKey);
    }
}
