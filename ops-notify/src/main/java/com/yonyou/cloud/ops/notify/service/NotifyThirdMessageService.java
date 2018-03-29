package com.yonyou.cloud.ops.notify.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.ops.notify.consts.NotifyConsts;
import com.yonyou.cloud.ops.notify.entity.NotifyAppUrl;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;
import com.yonyou.cloud.ops.notify.mapper.NotifyThirdMessageMapper;
import com.yonyou.cloud.ops.notify.util.RestTemplateUtil;

import tk.mybatis.mapper.common.Mapper;

@Service
public class NotifyThirdMessageService  extends BaseService<Mapper<NotifyThirdMessage>, NotifyThirdMessage>{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RestTemplateUtil restTemplateUtil;
	@Autowired
	private NotifyThirdMessageMapper notifyThirdMessageMapper;
	@Autowired
	private NotifyAppUrlService notifyAppUrlService;
	
	public List<NotifyThirdMessage> getNotifyMessage(String appId, String bizId) {
		return notifyThirdMessageMapper.getNotifyMessage(appId, bizId);
	}
	
	@Transactional(rollbackFor= Exception.class)
    public void updateNotisyMessageByMeskey(String status,String msgKey){
		notifyThirdMessageMapper.updateNotisyMessageByMeskey(status, msgKey);
    }

	public void sendMessage(NotifyThirdMessage mes) throws Exception  {
		//取得第三方发送的url和contentType，根据appid取得contentType，如果notifyUrl==null，则回调url来自NotifyAppUrl设置的url
		NotifyAppUrl na=notifyAppUrlService.getAppUrl(mes.getNotifyUrl(), mes.getAppId());
		if(na!=null){
			this.sendMessage(na.getUrl(), na.getContentType(), mes.getMsgKey(), mes.getBizId(), mes.getData());
		}
	}
	public void sendMessage(String url,String contentType,String msgKey,
			String bizId,String data) throws Exception{
		logger.info("--发送第三方数据,url="+url+",bizId="+bizId+",data="+data);
		//使用restTemplate发送数据到第三方
		String repBody=restTemplateUtil.sendMessage( url, contentType, msgKey,bizId, data);
		//返回内容包含 1001002 代表执行成功，更新消息状态
		if(repBody!=null && !"".equals(repBody) && !repBody.contains(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS)){
			logger.error("--第三方接收失败,msgKey="+msgKey+",bizId="+bizId+",data="+data+",repBody="+repBody);
			//发送失败，抛出异常
			throw new Exception("消息发送到第三方接口失败,msgKey="+msgKey+",bizId="+bizId+",data="+data+",repBody="+repBody);
//			this.updateNotisyMessageByMeskey(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS,
//					msgKey);
		}
		logger.info("--发送第三方数据成功");
	}
	/**
	 * 重发未成功发送到第三方的消息
	 * @throws Exception 
	 */
	public void reSendMessage() throws Exception{
		//读取未成功发送的消息
		List<NotifyThirdMessage> list=notifyThirdMessageMapper.getNotifyMessageByStatus(NotifyConsts.NOTIFY_STATUS_NEW);
		for(NotifyThirdMessage mes:list){
			this.sendMessage(mes);
			//没有抛出异常代表第三方接收成功
			this.updateNotisyMessageByMeskey(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS,mes.getMsgKey());
		}
	}
}
