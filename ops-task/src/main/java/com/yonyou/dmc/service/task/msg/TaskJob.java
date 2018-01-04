package com.yonyou.dmc.service.task.msg;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
/**
 * 
 * @author daniell
 *
 */
public interface TaskJob {

	String REVEIVE = "reveive_job";
	String SEND = "send_job";
    
	/**
	 * 计划任务消息发送
	 * @return
	 */
    @Output(TaskJob.SEND)
	MessageChannel sendJob();
    
    /**
     * 计划任务消息接受
     * @return
     */
    @Input(TaskJob.REVEIVE)
	SubscribableChannel receiveJob();
    
    

}