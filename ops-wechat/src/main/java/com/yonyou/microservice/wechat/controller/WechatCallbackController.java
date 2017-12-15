package com.yonyou.microservice.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.wechat.entity.Check;
import com.yonyou.microservice.wechat.service.WechatMessageService;

@Controller
public class WechatCallbackController {

    private static Logger logger = Logger.getLogger(WechatCallbackController.class);
    @Autowired
    private WechatMessageService wechatMessageService;

    //微信验证服务器回调接口  
	@RequestMapping(value = "/wechat/callback/{serviceNo}", method = RequestMethod.GET)//, produces="text/html;charset=UTF-8"
	@ResponseBody 
	public RestResultResponse<String> validateGet(Check tokenModel, HttpServletRequest req,
			@PathVariable("serviceNo") String serviceNo,
			HttpServletResponse res) throws ParseException, IOException {
		logger.info("----WeChatMessageController,get,check");
		String validate = wechatMessageService.validate(tokenModel,serviceNo);
		logger.info("---"+validate);
		return new RestResultResponse<String>().data(validate);
	}
    
    /**
    *
    * @author LiuJun
    * 接收经销商公众号消息入口
    * @date 2016年12月6日
    * @param openId
    * @param nonce
    * @param timestamp
    * @param signature
    * @param xml
    * @return
    *///@RequestBody Map<String, String> map,
    @RequestMapping(value = "/wechat/callback/{serviceNo}", method = RequestMethod.POST)//,  produces = "text/plain;charset=UTF-8"
    @ResponseBody
    public String receiveDealerWeChatMsg(@PathVariable("serviceNo") String serviceNo,
    		HttpServletRequest req,HttpServletResponse rps,@RequestBody String xml) throws Exception{
		logger.info("----WeChatMessageController,post,"+xml);
//        String returnMsg = wechatMessageService.receiveAndReplyDealerWeChatMsg(dealerAppid,nonce,timestamp,signature,xml);
		String returnMsg = wechatMessageService.receiveAndReplyDealerWeChatMsg(serviceNo,xml,rps);
		String openid=req.getParameter("openid");
		logger.info("----WeChatMessageController,openid,"+openid);
//		writeWechatCookiesInfo(openid,rps);
        return returnMsg;
    }
}
