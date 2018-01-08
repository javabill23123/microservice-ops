package com.yonyou.cloud.ops.mq.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.service.BaseEsService;
import com.yonyou.cloud.ops.mq.common.MqMessageStatus;
import com.yonyou.cloud.ops.mq.common.MqMessageType;
import com.yonyou.cloud.ops.mq.common.MqOpsConstant;
import com.yonyou.cloud.ops.mq.entity.MqConsumeDetailInfo;
import com.yonyou.cloud.ops.mq.entity.MqMessage;

@Service
public class MqMessageService extends BaseEsService<MqMessage>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MqConsumeDetailInfoService mqConsumeDetailInfoService;

	public void save (MqMessage mqMessage, MqMessageType mqMessageType) throws Exception{
		
		MqMessage oldMessage = selectOne(MqOpsConstant.INDEX, "msgKey:" + "\"" +mqMessage.getMsgKey() + "\"");
		
		if(oldMessage == null){
			updateMsgStatus(mqMessage, mqMessage, mqMessageType);
			insert(MqOpsConstant.INDEX, mqMessage);
//		} else if((MqMessageStatus.CONSUMED.name().equals(oldMessage.getConsumeStatus()) && MqMessageType.CONSUMER == mqMessageType)
//				||(MqMessageStatus.PRODUCED.name().equals(oldMessage.getProduceStatus()) && MqMessageType.PRODUCER == mqMessageType)){
//			logger.error("this message status is success,msgkey:{}", mqMessage.getMsgKey());
		} else {
			updateMsgStatus(oldMessage, mqMessage, mqMessageType);
			update(MqOpsConstant.INDEX, oldMessage, oldMessage.getId());
		}
	}

	private void updateMsgStatus(MqMessage oldMessage, MqMessage newMessage, MqMessageType mqMessageType) {
		switch (mqMessageType) {
		case PRODUCER:
			if(Boolean.valueOf(newMessage.getSuccess())){
				oldMessage.setProduceSuccessTime(newMessage.getOccurTime());
				oldMessage.setProduceStatus(MqMessageStatus.PRODUCED.name());
			}else{
				oldMessage.setProduceFailTimes(oldMessage.getProduceFailTimes() + 1);
				oldMessage.setProduceStatus(MqMessageStatus.PRODUCE_FAIL.name());
			}
			break;
			
		case CONSUMER:
			
			boolean allSucces = true;
			
			try {
				List<MqConsumeDetailInfo> mqConsumeDetailInfo = mqConsumeDetailInfoService.selectList(MqOpsConstant.INDEX, "msgKey:" + "\"" +newMessage.getMsgKey() + "\"");
				//分组
				Map<String, List<MqConsumeDetailInfo>> m = mqConsumeDetailInfo.stream().filter(t -> t.getConsumerId() != null)
						.collect(Collectors.groupingBy(MqConsumeDetailInfo::getConsumerId));
				//聚合
				for(List<MqConsumeDetailInfo> l : m.values()){
					allSucces = allSucces & l.parallelStream().filter(t -> Boolean.valueOf(t.getSuccess())).count() > 0 ? true : false;
				}
			} catch (Exception e) {
				logger.error("mqConsumeDetailInfo converge fail.");
			}
			
			if(Boolean.valueOf(newMessage.getSuccess())){
				oldMessage.setConsumeSuccessTime(newMessage.getOccurTime());
			}else{
				oldMessage.setConsumeFailTimes(oldMessage.getConsumeFailTimes() + 1);
			}
			oldMessage.setConsumeStatus(allSucces?MqMessageStatus.CONSUMED.name():MqMessageStatus.CONSUME_FAIL.name());
			break;
			
		default:
			break;
		}
	}
} 
