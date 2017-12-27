/*
* Copyright 2016 Yonyou Auto Information Technology（Shanghai） Co., Ltd. All Rights Reserved.
*
* This software is published under the terms of the YONYOU Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : marketing-service-wechat
*
* @File name : DealerWechatMessageService.java
*
* @Author : LiuJun
*
* @Date : 2016年12月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年12月6日    LiuJun    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.yonyou.microservice.wechat.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.http.HttpUtil;
import com.yonyou.cloud.mom.client.MqSender;
import com.yonyou.microservice.gate.common.vo.wechat.OfficeAccountSettingInfo;
import com.yonyou.microservice.wechat.common.BusinessConstant;
import com.yonyou.microservice.wechat.common.WechatDict;
import com.yonyou.microservice.wechat.dto.BizEvent;
import com.yonyou.microservice.wechat.dto.EventWechatAttentionDTO;
import com.yonyou.microservice.wechat.dto.EventWechatUnAttentionDTO;
import com.yonyou.microservice.wechat.encryption.WxBizMsgCrypt;
import com.yonyou.microservice.wechat.entity.Check;
import com.yonyou.microservice.wechat.eventenum.EventConstant.EventBizStep;
import com.yonyou.microservice.wechat.eventenum.EventConstant.EventBizType;
import com.yonyou.microservice.wechat.exception.WechatException;
import com.yonyou.microservice.wechat.util.CookieSecurityUtil;
import com.yonyou.microservice.wechat.util.EncoderHandler;
import com.yonyou.microservice.wechat.util.MapConverUtil;
import com.yonyou.microservice.wechat.util.WechatMessageHandleUtils;

import net.sf.json.JSONObject;

/**
*
* @author LiuJun
* 接收经销商服务号消息service
* @date 2016年12月6日
*/
@Service
public class WechatMessageService {
	private static final String CONST_QUERY_AUTH_CODE="QUERY_AUTH_CODE";
	@Autowired
	private OfficeAccountSettingService settingService;
    
    private Logger logger=Logger.getLogger(WechatMessageService.class);

	@Autowired
	private MqSender mqSender;
    @Autowired
    private TokenService tokenService;
    
	public String validate(Check tokenModel,String serviceNo) {
		String wxToken=settingService.getOfficeAccountToken(serviceNo);
		String signature = tokenModel.getSignature();
		Long timestamp = tokenModel.getTimestamp();
		Long nonce = tokenModel.getNonce();
		String echostr = tokenModel.getEchostr();
		if (signature != null && timestamp != null && nonce != null) {
			String[] str = { wxToken, timestamp + "", nonce + "" };
			// 字典序排序
			Arrays.sort(str); 
			String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			String digest = EncoderHandler.encode("SHA1", bigStr).toLowerCase();
			// 确认请求来至微信
			if (digest.equals(signature)) {
				return echostr;
			}
		}
		return "error";
	}

    /**
    *
    * @author LiuJun
    * 接收并回复微信第三方平台消息
    * @date 2016年12月6日
    * @param dealerAppid
    * @param nonce
    * @param timestamp
    * @param signature
    * @param xml
    * @return
    * @throws WechatException
    * @throws ServiceAppException
    */
    public String receiveAndReplyDealerWeChatMsg(String serviceNo,
                                                 String xml,HttpServletResponse response) throws WechatException{
        try {
            Map<String,String> xmlMap = WechatMessageHandleUtils.xmlToMap(xml);
            
            String msgType = xmlMap.get("MsgType");
            
            if (WechatDict.MESSAGE_TYPE_TEXT.equals(msgType)) {
                logger.info("receiveAndReplyDealerWeChatMsg-text");
                // 文本类型消息处理
                return handleTextMsg(serviceNo,xmlMap);
                
            } else if(WechatDict.MESSAGE_TYPE_EVENT.equals(msgType)) {
                logger.info("receiveAndReplyDealerWeChatMsg-event");
                // 事件类型消息处理
                return handleEventMsg(serviceNo,xmlMap,response);
                
            }else if(WechatDict.MESSAGE_TYPE_IMAGE.equals(msgType)) {
                
            }else if(WechatDict.MESSAGE_TYPE_VOICE.equals(msgType)) {
                
            }


            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new WechatException();
        }
    }
    
    
    /**
    *
    * @author LiuJun
    * 文本类型消息处理
    * @date 2016年12月6日
    * @param msgType
    * @param map
    * @return
    */ 
    private String handleTextMsg(String serviceNo,Map<String, String> msgMap) throws WechatException{
        try {
            
            //模拟粉丝发送文本消息给专用测试公众号
            String content = msgMap.get("Content");
            logger.info("handleTextMsg-content="+content);
            
            String fromUserName = msgMap.get("FromUserName");
            String toUserName = msgMap.get("ToUserName");
            
            logger.info("handleTextMsg-fromUserName="+fromUserName);
            logger.info("handleTextMsg-toUserName="+toUserName);
            
            Map<String, Object> map = new HashMap<String, Object>(20);
            map.put("ToUserName",fromUserName);
            map.put("FromUserName",toUserName);
            map.put("CreateTime", System.currentTimeMillis() + "");
            map.put("MsgType", WechatDict.MESSAGE_TYPE_TEXT);
            //模拟粉丝发送文本消息给专用测试公众号，第三方平台方需在5秒内返回空串表明暂时不回复，然后再立即使用客服消息接口发送消息回复粉丝
            if(content.contains(CONST_QUERY_AUTH_CODE)){
                map.put("Content", "");
            }else{//模拟粉丝发送文本消息给专用测试公众号，第三方平台方需根据文本消息的内容进行相应的响应：
                map.put("Content", "您的问题，我们将抓紧答复！");
            }

            OfficeAccountSettingInfo oa=settingService.getOfficeAccount(serviceNo);
            WxBizMsgCrypt crypt = new WxBizMsgCrypt(oa.getToken(), oa.getAesKey(), oa.getAppid());
            String replyMsg = WechatMessageHandleUtils.mapToXml(map);
            logger.info("handleTextMsg-replyMsg="+replyMsg);
            
            return replyMsg;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new WechatException();
        }

    }
    
    
    /**
    *
    * @author LiuJun
    * 事件类型消息处理
    * @date 2016年12月8日
    * @param msgMap
    * @param timestamp
    * @param nonce
    * @return
    */
    private String handleEventMsg(String serviceNo,Map<String, String> msgMap,HttpServletResponse response) {

        String event = msgMap.get("Event");
        logger.info("handleEventMsg-event="+event);
        switch (event) {
        case WechatDict.EVENT_SUBSCRIBE:
            // 用户未关注时，进行关注后的事件推送
            return handleSubscribeEvent(serviceNo,event,msgMap,response);
        case WechatDict.EVENT_SCAN:
            // 用户已关注时的事件推送
            return null;
            
        case WechatDict.EVENT_UNSUBSCRIBE:
            // 用户取消关注时的事件推送
            return handleCancelSubscribeEvent(event,msgMap);
            
        case WechatDict.EVENT_CLICK:
            // 自定义菜单点击事件
//            return handleClickEvent(msgMap);
            return null;
        default:
            return null;
        }
    }
    public String getUser(String serviceNo,String openid)  throws WechatException{
    	this.logger.info("getUser,openid="+openid);
		String url = WechatDict.GET_OPEN_INFO_URL;
		url = url.replace("ACCESS_TOKEN", tokenService.getAccessToken(serviceNo));
		url = url.replace("OPENID", openid);
        String body=HttpUtil.get(url);
		return body;
    }
    
    private String handleSubscribeEvent(String serviceNo,String event,Map<String, String> msgMap,HttpServletResponse response)  throws WechatException{
        try{
            

            String toUserName = msgMap.get("ToUserName");
            String fromUserName = msgMap.get("FromUserName");
            String eventKey=msgMap.get("EventKey");
            if(eventKey!=null && !"".equals(eventKey)){
            	int i=eventKey.indexOf("qrscene_");
            	if(i==0){
            		i=8;
            	}
            	eventKey=eventKey.substring(i, eventKey.length());
            }
    		String url = WechatDict.GET_OPEN_INFO_URL;
    		url = url.replace("ACCESS_TOKEN", tokenService.getAccessToken(serviceNo));
    		url = url.replace("OPENID", fromUserName);
            String body=HttpUtil.get(url);
    		JSONObject fromObject = JSONObject.fromObject(body);
            try{
                //关注后保存用户基本信息
            	EventWechatAttentionDTO attentionDto = new EventWechatAttentionDTO();
            	attentionDto.setStatus(BusinessConstant.POTENTIAL_USER_STATUS_SUBSCRIBE);
            	attentionDto.setSourceType(BusinessConstant.SOURCE_TYPE_WECHAT);
            	attentionDto.setNickName(fromObject.get("nickname").toString());
            	attentionDto.setHeadImgurl(fromObject.get("headimgurl").toString());
            	attentionDto.setCity(fromObject.get("city").toString());
            	attentionDto.setCountry(fromObject.get("country").toString());
            	attentionDto.setProvince(fromObject.get("province").toString());
            	attentionDto.setSex(fromObject.get("sex").toString());
            	attentionDto.setSubscribeDate(new Date()); 
//            	attentionDto.setAuthorizerAppid(dealerAppid);
            	attentionDto.setOperateType(BusinessConstant.POTENTIALUSER_OPERATETYPE_SUBSCRIBE);
            	attentionDto.setDeviceOpenId(fromUserName);
            	attentionDto.setCreateDate(new Date());
            	attentionDto.setUpdateDate(new Date());
            	attentionDto.setDealerCode(eventKey);
            	BizEvent et=new BizEvent();
            	et.setEventBizType(EventBizType.WECHAT_RELATION);
            	et.setEventBizStep(EventBizStep.WECHAT_USER_ATTENTION);
            	et.setEventKey("");
            	et.setEventData(MapConverUtil.Po2Map(attentionDto));
            	mqSender.send("event-wechat", "attention", et);
            	logger.info("-----user info="+body);
                logger.info("handleSubscribeEvent-saveOrUpdatePotentialUser is success");
                
            }catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            
            //关注时回复图文消息dealerAppid,,timestamp,nonce
            String replyMsg = replyImageTextMessage(fromUserName,toUserName,event);
            
            if(!(null == replyMsg || "".equals(replyMsg))){
                return replyMsg;
            }else{
                 //如果没有图文消息回复
                // 第三方公众号测试回复,timestamp,nonce
                return testReplyTextMessage(serviceNo,fromUserName,toUserName,event,response);
            }

        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new WechatException();
        }

    }
    
    /**
    *
    * @author LiuJun
    * 第三方公众号测试回复
    * @date 2016年12月26日
    * @param fromUserName
    * @param toUserName
    * @param event
    * @param timestamp
    * @param nonce
    * @return
    * @throws WechatException
    * @throws ServiceAppException
    */
    @SuppressWarnings("unused")
    private String testReplyTextMessage(String code, String fromUserName,String toUserName,String event,HttpServletResponse response) throws WechatException {
        try {
            String value = fromUserName;
            String oxid=value+",,"+new Date().toString();
            String openId = CookieSecurityUtil.encrypt(oxid);
            Map<String, Object> map = new HashMap<String, Object>(20);
            map.put("ToUserName", fromUserName);
            map.put("FromUserName", toUserName);
            map.put("CreateTime", System.currentTimeMillis() + "");
            map.put("MsgType", WechatDict.MESSAGE_TYPE_TEXT);
            String content = "壮志菱云，共悦人生！亲爱的菱粉，欢迎来到广汽三菱官方微信服务平台，这里是广汽三菱唯一官方车友俱乐部——菱悦会，更是以“热爱SUV，创造愉悦生活”为纽带，促进SUV文化交流，实现权益尊享的车主专属互动平台，还有丰富的活动和豪礼相送。现诚邀您<a href=\"http://i-club.gmmc.com.cn/wx/views/homepage/signUp.html?openId="+openId+"\">【点击加入会员】</a>与广汽三菱一起纵情天地！";
            logger.info("发送的消息体为>>>>>>"+content);
            map.put("Content", content);

//            OfficeAccountSetting oa=settingService.getOfficeAccount(code);
//            WXBizMsgCrypt crypt = new WXBizMsgCrypt(oa.getToken(), oa.getAESKey(), oa.getAppid());
            String replyMsg = WechatMessageHandleUtils.mapToXml(map);
            logger.info("handleSubscribeEvent-replyMsg=" + replyMsg);
            return replyMsg;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new WechatException();
        }

    }
    
    /**
    *
    * @author LiuJun
    * 回复图文消息
    * @date 2016年12月26日
    * @param authorizerAppid
    * @param fromUserName
    * @param toUserName
    * @param event
    * @param timestamp
    * @param nonce
    * @return
    * @throws WechatException
    * @throws ServiceAppException
    */
    private String replyImageTextMessage(String fromUserName,String toUserName,String event) throws WechatException {
    	return "";

    }

    /**
     * 用户取消关注时的事件推送
     * @param msgType
     * @param map
     * @return
     * @throws Exception 
     */
    private String handleCancelSubscribeEvent(String event,Map<String, String> msgMap) throws WechatException{
        try {
            
            String toUserName = msgMap.get("ToUserName");
            String fromUserName = msgMap.get("FromUserName");
            
            try{
                
                String openId = msgMap.get("FromUserName");
                EventWechatUnAttentionDTO attentionDto = new EventWechatUnAttentionDTO();
            	attentionDto.setStatus(BusinessConstant.POTENTIAL_USER_STATUS_CANCEL);
            	attentionDto.setSourceType(BusinessConstant.SOURCE_TYPE_WECHAT);
//            	attentionDto.setAuthorizerAppid(dealerAppid);
            	attentionDto.setDeviceOpenId(fromUserName);
            	attentionDto.setUpdateDate(new Date());
            	BizEvent et=new BizEvent();
            	et.setEventBizType(EventBizType.WECHAT_RELATION);
            	et.setEventBizStep(EventBizStep.WECHAT_USER_UNATTENTION);
            	et.setEventData(MapConverUtil.Po2Map(attentionDto));
            	mqSender.send("event-wechat", "unAttention", et);
            }catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            
            
            Map<String, Object> map = new HashMap<String, Object>(20);
            map.put("ToUserName", fromUserName);
            map.put("FromUserName", toUserName);
            map.put("CreateTime", System.currentTimeMillis() + "");
            map.put("MsgType", WechatDict.MESSAGE_TYPE_TEXT);
            map.put("Content", "感谢您的关注！");

            WxBizMsgCrypt crypt = new WxBizMsgCrypt(WechatDict.token, WechatDict.AESKey, WechatDict.appid);
            String replyMsg = WechatMessageHandleUtils.mapToXml(map);
            logger.info("handleCancelSubscribeEvent-replyMsg="+replyMsg);
            return replyMsg;
//            return crypt.encryptMsg(replyMsg, timestamp, nonce);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new WechatException();
        }

    }
    
    
    /**
     * 点击事件
     * @param msgType
     * @param map
     * @return
     * @throws Exception 
     */
    private String handleClickEvent(Map<String, String> map) {
        String eventKey = map.get("EventKey");
        String messageXml = "";
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        return messageXml;
    }

    
    
    
}
