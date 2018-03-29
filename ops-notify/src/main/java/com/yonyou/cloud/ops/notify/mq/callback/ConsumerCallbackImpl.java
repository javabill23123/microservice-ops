package com.yonyou.cloud.ops.notify.mq.callback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.core.dto.ConsumerDto;
import com.yonyou.cloud.mom.core.store.callback.ConsumerStoreDbCallback;
import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;
import com.yonyou.cloud.ops.notify.consts.NotifyConsts;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.mapper.NotifyThirdMessageMapper;
import com.yonyou.cloud.ops.notify.service.NotifyThirdMessageService;

import net.sf.json.JSONObject;

@Component
@Transactional
public class ConsumerCallbackImpl implements ConsumerStoreDbCallback{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	NotifyThirdMessageMapper notifyThirdMessageMapper;
	@Autowired
	NotifyThirdMessageService notifyThirdMessageService;
	
	 /**
	  * 根据msgkey判断消息是否存在{true：存在,false:不存在}
	  */
	@Override
	public boolean exist(String msgKey) throws StoreDBCallbackException {
		logger.info("--检查消息是否存在,msgKey="+msgKey);
		List<NotifyThirdMessage> list=notifyThirdMessageMapper.getNotifyMessageByKey(msgKey);
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
		JSONObject jObject=JSONObject.fromObject(data);
		NotifyThirdMessage msg = (NotifyThirdMessage)JSONObject.toBean(jObject,NotifyThirdMessage.class);
		msg.setMsgKey(msgKey);
		msg.setStatus(NotifyConsts.NOTIFY_STATUS_NEW);

		List<NotifyThirdMessage> list=notifyThirdMessageMapper.getNotifyMessageByKey(msgKey);
		if(list!=null&&list.size()==0) {
			notifyThirdMessageMapper.insert(msg);
		}
	}

	/**
	 * 消息消费成功后相关操作
	 */
	@Override
	public void updateMsgSuccess(String msgKey) throws StoreDBCallbackException {
		logger.info("--MQ消费者,更新状态为成功,msgKey="+msgKey);
		//更新消息状态为发送成功
		notifyThirdMessageService.updateNotisyMessageByMeskey(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS,
				msgKey);
	}

	/**
	 * 消息消费失败后相关操作
	 */
	@Override
	public void updateMsgFaild(String msgKey) throws StoreDBCallbackException {
//		ConsumerMsg msg = new ConsumerMsg();
//		msg.setMsgKey(msgKey);
//		msg.setStatus(102);
//		consumerMsgMapper.updateByPrimaryKeySelective(msg);
		
	}

	/**
	 * 查询需要重新消费的信息
	 */
	@Override
	public List<ConsumerDto> selectReConsumerList(Integer status) {
//		List<ConsumerDto> dtolist=new ArrayList<>();
//		ConsumerMsg msg = new ConsumerMsg();
//		msg.setStatus(status);
//		List<ConsumerMsg> list=consumerMsgMapper.select(msg);
//		for(ConsumerMsg msgs:list) {
//			ConsumerDto dto=new ConsumerDto();
//			BeanUtils.copyProperties( msgs,dto);  
//			dtolist.add(dto);
//		}
//		return dtolist;
		return null;
	}
	
	/**
	 * 查询单条需要重新消费的信息
	 */
	@Override
	public ConsumerDto getReConsumerDto(String msgKey) {
//		ConsumerMsg msgs = consumerMsgMapper.selectByPrimaryKey(msgKey);
//		ConsumerDto dto = new ConsumerDto();
//		BeanUtils.copyProperties(msgs, dto);
//		return dto;
		return null;
	}
}
