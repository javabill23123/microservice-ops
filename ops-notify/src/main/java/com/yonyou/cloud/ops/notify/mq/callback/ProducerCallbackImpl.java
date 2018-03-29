//package com.yonyou.cloud.ops.notify.mq.callback;
//
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.yonyou.cloud.mom.core.dto.ProducerDto;
//import com.yonyou.cloud.mom.core.store.callback.ProducerStoreDBCallback;
//import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;
//
//@Component
//public class ProducerCallbackImpl implements ProducerStoreDBCallback{
//	
//
//	/**
//	 * 保存消息
//	 */
//	@Override
//	public void saveMsgData(String msgKey, String data, String exchange, String routerKey, String bizClassName)
//			throws StoreDBCallbackException {
//		
//	}
//
//	/**
//	 * 发送成功后相关处理
//	 */
//	@Override
//	public void update2success(String msgKey) throws StoreDBCallbackException {
//	}
//
//	/**
//	 * 发送成功后相关处理
//	 */
//	@Override
//	public void update2faild(String msgKey, String infoMsg, Long costTime, String exchange, String routerKey,
//			String data, String bizClassName) throws StoreDBCallbackException {
//	}
//
//	/**
//	 * 查询需要重发的消息
//	 */
//	@Override
//	public List<ProducerDto> selectResendList(Integer status) {
//		return null;
//	}
//
//	/**
//	 * 根据msgKey查询单条需要重发的消息
//	 */
//	@Override
//	public ProducerDto getResendProducerDto(String Msgkey) { 
//			return null;
//		 
//	}
//}
