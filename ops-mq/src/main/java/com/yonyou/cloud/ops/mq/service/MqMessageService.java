package com.yonyou.cloud.ops.mq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.service.BaseEsService;
import com.yonyou.cloud.ops.mq.common.MqMessageStatus;
import com.yonyou.cloud.ops.mq.common.MqMessageType;
import com.yonyou.cloud.ops.mq.common.MqOpsConstant;
import com.yonyou.cloud.ops.mq.entity.MqMessage;

@Service
public class MqMessageService extends BaseEsService<MqMessage>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void save (MqMessage mqMessage, MqMessageType mqMessageType) throws Exception{
		
		MqMessage oldMessage = selectOne(MqOpsConstant.INDEX, "msgKey:" + mqMessage.getMsgKey());
		
		switch (mqMessageType) {
		case PRODUCER:
			if(Boolean.valueOf(mqMessage.getSuccess())){
				mqMessage.setProduceSuccessTime(mqMessage.getOccurTime());
				mqMessage.setProduceStatus(MqMessageStatus.PRODUCED.name());
			}else{
				mqMessage.setProduceFailTimes(mqMessage.getConsumeFailTimes() + 1);
				mqMessage.setProduceStatus(MqMessageStatus.PRODUCE_FAIL.name());
				mqMessage.setProduceFailTimes(oldMessage == null ? 1 : oldMessage.getProduceFailTimes() + 1);
			}
			break;
			
		case CONSUMER:
			if(Boolean.valueOf(mqMessage.getSuccess())){
				mqMessage.setConsumeSuccessTime(mqMessage.getOccurTime());
				mqMessage.setConsumeStatus(MqMessageStatus.CONSUMED.name());
			}else{
				mqMessage.setConsumeFailTimes(mqMessage.getConsumeFailTimes() + 1);
				mqMessage.setConsumeStatus(MqMessageStatus.CONSUME_FAIL.name());
				mqMessage.setConsumeFailTimes(oldMessage == null ? 1 : oldMessage.getConsumeFailTimes() + 1);
			}
			break;
			
		default:
			break;
		}
		
		if(oldMessage == null){
			
			insert(MqOpsConstant.INDEX, mqMessage);
//		} else if((MqMessageStatus.CONSUMED.name().equals(oldMessage.getConsumeStatus()) && MqMessageType.CONSUMER == mqMessageType)
//				||(MqMessageStatus.PRODUCED.name().equals(oldMessage.getProduceStatus()) && MqMessageType.PRODUCER == mqMessageType)){
//			logger.error("this message status is success,msgkey:{}", mqMessage.getMsgKey());
		} else {
			update(MqOpsConstant.INDEX, mqMessage, oldMessage.getId());
		}
	}
} 
