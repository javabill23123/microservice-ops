package com.yonyou.cloud.ops.alert.ops.alert.alarm;

import java.util.List;

import com.yonyou.cloud.ops.alert.ops.alert.alarm.mails.AlarmMessageSender;
import com.yonyou.cloud.ops.alert.ops.alert.domain.dto.mail.MessageTemplate;

//销售部门----服务端  
public class AlarmMessageContext {
	AlarmMessageSender msgSend = null;

	public AlarmMessageContext(AlarmMessageSender msgSend) {
		this.msgSend = msgSend;
	}

	public void AlarmMessage(List<MessageTemplate> msgTemp) {
		this.msgSend.sendMessage(msgTemp);
	}
}