package com.yonyou.cloud.ops.notify.mq.callback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.core.dto.ConsumerDto;
import com.yonyou.cloud.mom.core.store.callback.ConsumerStoreDbCallback;
import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;
import com.yonyou.cloud.ops.notify.consts.NotifyConsts;
import com.yonyou.cloud.ops.notify.entity.NotifyMqMessage;
import com.yonyou.cloud.ops.notify.mapper.NotifyMqMessageMapper;
import com.yonyou.cloud.ops.notify.service.NotifyMqMessageService;

@Component
@Transactional
public class ConsumerCallbackImpl implements ConsumerStoreDbCallback{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	NotifyMqMessageMapper notifyMqMessageMapper;
	@Autowired
	NotifyMqMessageService notifyMqMessageService;
	
	 /**
	  * 根据msgkey判断消息是否存在{true：存在,false:不存在}
	  */
	@Override
	public boolean exist(String msgKey) throws StoreDBCallbackException {
		logger.info("--检查消息是否存在,msgKey="+msgKey);
		List<NotifyMqMessage> list=notifyMqMessageMapper.getMqMessageByMsgkey(msgKey);
		if(list!=null&&list.size()==1) {
			return true;
		}
		return false;
	}
	/**
	 * 保存接受到的信息
	 */
	@Override
	public void saveMsgData(String msgKey, String data, String exchange, String routerKey,
			String consumerClassName, String bizClassName) throws StoreDBCallbackException {
		logger.info("--MQ消费者,保存消息,msgKey="+msgKey+",data="+data+",routerKey="+routerKey);
//		JSONObject jObject=JSONObject.fromObject(data);

		List<NotifyMqMessage> list=notifyMqMessageMapper.getMqMessageByMsgkey(msgKey);
		if(list!=null&&list.size()==0) {
			NotifyMqMessage msg = new NotifyMqMessage();
			msg.setMsgKey(msgKey);
			msg.setStatus(NotifyConsts.MQ_STATUS_NEW);
			msg.setBizClassName(bizClassName);
			msg.setConsumerClassName(consumerClassName);
			msg.setCrt_time(new Date());
			msg.setData(data);
			msg.setExchange(exchange);
			msg.setRouterKey(routerKey);
			notifyMqMessageMapper.insert(msg);
		}
	}

	/**
	 * 消息消费成功后相关操作
	 */
	@Override
	public void updateMsgSuccess(String msgKey) throws StoreDBCallbackException {
		logger.info("--MQ消费者,更新状态为成功,msgKey="+msgKey);
		//更新消息状态为发送成功
		notifyMqMessageService.updateMqMessageByMeskey(NotifyConsts.MQ_STATUS_SUCCESS,
				msgKey);
	}

	/**
	 * 消息消费失败后相关操作
	 */
	@Override
	public void updateMsgFaild(String msgKey) throws StoreDBCallbackException {

		logger.info("--MQ消费者,更新状态为失败,msgKey="+msgKey);
		//更新消息状态为发送成功
		notifyMqMessageService.updateMqMessageByMeskey(NotifyConsts.MQ_STATUS_FAIL,
				msgKey);
		
	}

	/**
	 * 查询需要重新消费的信息
	 */
	@Override
	public List<ConsumerDto> selectReConsumerList(Integer status) {
		List<ConsumerDto> dtolist=new ArrayList<>();
		NotifyMqMessage msg = new NotifyMqMessage();
		msg.setStatus(NotifyConsts.MQ_STATUS_FAIL);
		List<NotifyMqMessage> list=notifyMqMessageMapper.select(msg);
		for(NotifyMqMessage msgs:list) {
			ConsumerDto dto=new ConsumerDto();
			BeanUtils.copyProperties( msgs,dto);  
			dtolist.add(dto);
		}
		return dtolist;
//		return null;
	}
	
	/**
	 * 查询单条需要重新消费的信息
	 */
	@Override
	public ConsumerDto getReConsumerDto(String msgKey) {
		NotifyMqMessage msgs = notifyMqMessageMapper.selectByPrimaryKey(msgKey);
		ConsumerDto dto = new ConsumerDto();
		BeanUtils.copyProperties(msgs, dto);
		return dto;
//		return null;
	}
}
