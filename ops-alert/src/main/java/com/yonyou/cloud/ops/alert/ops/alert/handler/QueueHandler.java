package com.yonyou.cloud.ops.alert.ops.alert.handler;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 
 * @author daniell
 *
 */
@Configuration
@EnableScheduling
@Component
public class QueueHandler {

	
	@Autowired
	AlertInfoHandler alertInfoHandler;
	
	@Scheduled(cron = "${redis.task.schedule}")
	public void queueHandler() {
		try {
			alertInfoHandler.redisFile();
		} catch (IOException e) {
			System.out.println("链接异常");
			e.printStackTrace();
		}
	}

}
