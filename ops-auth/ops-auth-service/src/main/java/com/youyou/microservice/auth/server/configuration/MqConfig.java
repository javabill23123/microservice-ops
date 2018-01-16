//package com.youyou.microservice.auth.server.configuration;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitOperations;
//import org.springframework.amqp.support.converter.JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.yonyou.cloud.mom.client.impl.MqSenderSimpleImpl;
//import com.yonyou.cloud.track.Track;
//
//@Configuration
//public class MqConfig {
//
//	@Bean
//	public MqSenderSimpleImpl mqSenderDefaultImpl(RabbitOperations rabbitOperations) {
//		MqSenderSimpleImpl mqSenderDefaultImpl = new MqSenderSimpleImpl();
////		mqSenderDefaultImpl.setTack(tack);
//		mqSenderDefaultImpl.setRabbitOperations(rabbitOperations);
//		return mqSenderDefaultImpl;
//	}
//	@Bean
//	public Queue loginQueue() {
//		return new Queue("user-login", true); // 队列持久
//	}
//
//	@Bean
//	public DirectExchange eventExchange() {
//		return new DirectExchange("auth-user");
//	}
//
//	@Bean
//	public Binding PointsBindingLogin() {
//		return BindingBuilder.bind(loginQueue()).to(eventExchange()).with("login");
//	}
//
////	@Bean
////	public SimpleMessageListenerContainer messageContainer1(ConnectionFactory connectionFactory,
////			PointsListenLogin pointsListenLogin) {
////		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
////		container.setQueues(pointsListenLoginQueue());
////		container.setExposeListenerChannel(true);
////		container.setMaxConcurrentConsumers(1);
////		container.setConcurrentConsumers(1);
////		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
////		container.setMessageListener(pointsListenLogin);
////		container.setMaxConcurrentConsumers(10);//设置最大消费者数量 防止大批量涌入
////		return container;
////	}
//
//	@Bean
//	public MessageConverter messageConverter() {
//		JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
//		return jsonMessageConverter;
//	}
//}
