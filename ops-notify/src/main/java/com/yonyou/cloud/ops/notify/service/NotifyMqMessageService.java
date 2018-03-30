package com.yonyou.cloud.ops.notify.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.ops.notify.consts.NotifyConsts;
import com.yonyou.cloud.ops.notify.entity.NotifyAppUrl;
import com.yonyou.cloud.ops.notify.entity.NotifyMqMessage;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.mapper.NotifyMqMessageMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public class NotifyMqMessageService  extends BaseService<Mapper<NotifyMqMessage>, NotifyMqMessage>{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NotifyMqMessageMapper notifyMqMessageMapper;
	
	
	@Transactional(rollbackFor= Exception.class)
    public void updateMqMessageByMeskey(String status,String msgKey){
		notifyMqMessageMapper.updateMqMessageByMsgkey(status, msgKey);
    }
}
