package com.yonyou.cloud.ops.notify.service;

import java.util.List;

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

	public void sendMessage(NotifyThirdMessage mes)  {
		//取得第三方发送的url和contentType，根据appid取得contentType，如果notifyUrl==null，则回调url来自NotifyAppUrl设置的url
		NotifyAppUrl na=notifyAppUrlService.getAppUrl(mes.getNotifyUrl(), mes.getAppId());
		if(na!=null){
			this.sendMessage(na.getUrl(), na.getContentType(), mes.getMsgKey(), mes.getBizId(), mes.getData());
		}
	}
	public void sendMessage(String url,String contentType,String msgKey,
			String bizId,String data){
		//使用restTemplate发送数据到第三方
		String repBody=restTemplateUtil.sendMessage( url, contentType, msgKey,bizId, data);
		//返回内容包含 1001002 代表执行成功，更新消息状态
		if(repBody!=null && !"".equals(repBody) && repBody.contains(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS)){
			//更新消息状态为发送成功
			this.updateNotisyMessageByMeskey(NotifyConsts.NOTIFY_STATUS_NOTICE_SUCCESS,
					msgKey);
		}
	}
	/**
	 * 重发未成功发送到第三方的消息
	 */
	public void reSendMessage(){
		//读取未成功发送的消息
		List<NotifyThirdMessage> list=notifyThirdMessageMapper.getNotifyMessageByStatus(NotifyConsts.NOTIFY_STATUS_NEW);
		for(NotifyThirdMessage m:list){
			this.sendMessage(m);
		}
	}
}
