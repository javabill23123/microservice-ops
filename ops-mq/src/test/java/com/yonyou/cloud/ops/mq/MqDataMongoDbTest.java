package com.yonyou.cloud.ops.mq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yonyou.cloud.ops.mq.common.MqMessageStatus;
import com.yonyou.cloud.ops.mq.entity.MqMessage;
import com.yonyou.cloud.ops.mq.repository.MqMessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqDataMongoDbTest {
	
	@Autowired
	private MqMessageRepository mqMessageRepository;
	
//	@Before
//	public void setUp() {
//		mqMessageRepository.deleteAll();
//		MqMessage mqMessage = new MqMessage();
//		mqMessage.setMsgKey("msgKey");
//		mqMessageRepository.save(mqMessage);
//	}
	
	@Test
	public void insert(){
		MqMessage mqMessage = new MqMessage();
		mqMessage.setMsgKey("msgKey");
		mqMessage.setOccurTime(System.currentTimeMillis());
		mqMessage.setProduceSuccessTime(System.currentTimeMillis());
		mqMessage.setConsumeSuccessTime(System.currentTimeMillis());
		mqMessage.setData("{\"alco\": \"cn.sh.ly\"}");
		mqMessage.setHost("10.32.38.3");
		mqMessage.setExchangeName("exchange_dol_l");
		mqMessage.setMsg("ok");
		mqMessage.setRoutingKey("keyyyyyyy");
		mqMessage.setSender("发送方clod");
		mqMessage.setSuccess("true");
		mqMessage.setStatus(MqMessageStatus.PRODUCED.toString());
		mqMessageRepository.save(mqMessage);
		Assert.assertTrue(mqMessageRepository.findAll().size()>0);

	}
	
	@Test
	public void search(){
		MqMessage message = mqMessageRepository.findByMsgKey("msgKey");
		Assert.assertNotNull(message);
	}
	
	@Test
	@Ignore
	public void del(){
		mqMessageRepository.deleteAll();
		Assert.assertEquals(0, mqMessageRepository.findAll().size());
	}
	

}
