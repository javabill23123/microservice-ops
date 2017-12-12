package com.yonyou.microservice.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yonyou.microservice.wechat.entity.Check;
import com.yonyou.microservice.wechat.service.TokenService;
import com.yonyou.microservice.wechat.util.CookieUtil;

import net.sf.json.JSONObject;

@Controller
public class WechatMessageDoingController {

	private static Logger logger=Logger.getLogger(WechatMessageDoingController.class);

	@Autowired
	private TokenService tokenService;
    @Autowired
    private Environment env;

	@RequestMapping("/portalredirect/{id}")
	public String portalredirect(@PathVariable("id") String id) {
		logger.info("----------------portalredirect");
		String dm = env.getProperty("wechat.domain");
		String appid = env.getProperty("wechat.appid");
		String u = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http://" + dm
				+ "/wx/portal/" + id + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		return "redirect:" + u;
	}

	@RequestMapping("/proxy/{serviceNo}/{id}")
    public String proxy(@PathVariable("serviceNo") String serviceNo,@PathVariable("id") String id,
    		HttpServletRequest request) throws Exception{
        String code  = request.getParameter("code");
        logger.info("获取到的Code为>>>>>>>"+code);
        String value = CookieUtil.getOpenid(request);
        logger.info("获取到的OPENID为>>>>>>>"+value);
		String dm=env.getProperty("wechat.domain");
		long time = (new Date()).getTime();

	    if(id.equals("person")){
           return "redirect:http://"+dm+"/wx/views/hpp.html?t="+time;
	    }else{
	       return "redirect:http://"+dm+"/wx/views/"+id+".html?t="+time;
	    }
    }

	@RequestMapping(value = "/sharepage/getToken/{serviceNo}", method = RequestMethod.GET) // ,
																				// produces="text/html;charset=UTF-8"
	@ResponseBody
	public String getToken(@PathVariable("serviceNo") String serviceNo) {
		String token = tokenService.getAccessToken(serviceNo);
		return token;
	}

	@RequestMapping(value = "/sharepage/getTicket/{serviceNo}", method = RequestMethod.GET) // ,
																				// produces="text/html;charset=UTF-8"
	@ResponseBody
	public String getTicket(@PathVariable("serviceNo") String serviceNo) {
		String token = getToken(serviceNo);
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
		try {
			String str = getReturnData(url);
			logger.info(str);
			JSONObject jsonObject = JSONObject.fromObject(str);
			String ticket = jsonObject.getString("ticket");
			logger.info("ticket为：" + ticket);
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getReturnData(String urlString) throws UnsupportedEncodingException {
		String res = "";
		try {
			URL url = new URL(urlString);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			java.io.BufferedReader in = new java.io.BufferedReader(
					new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line;
			}
			in.close();
		} catch (Exception e) {
			logger.error("error in wapaction,and e is " + e.getMessage());
		}
		return res;
	}


	// 微信回调接口 /{wxToken}
	// @RequestMapping(value = "/wechatcall/request", method =
	// RequestMethod.POST)//, produces="text/html;charset=UTF-8"
	// @ResponseBody //@PathVariable("wxToken") String wxToken, Check
	// tokenModel,
	public String validatePost(HttpServletRequest req, HttpServletResponse res) {

		logger.info("----TokenController,post,check");
		Enumeration<String> s = req.getAttributeNames();
		while (s.hasMoreElements()) {
			logger.info(s.nextElement());
		}
		return "";
	}
//
//	public String validate(String wxToken, Check tokenModel) {
//		String signature = tokenModel.getSignature();
//		Long timestamp = tokenModel.getTimestamp();
//		Long nonce = tokenModel.getNonce();
//		String echostr = tokenModel.getEchostr();
//		if (signature != null && timestamp != null && nonce != null) {
//			String[] str = { wxToken, timestamp + "", nonce + "" };
//			Arrays.sort(str); // 字典序排序
//			String bigStr = str[0] + str[1] + str[2];
//			// SHA1加密
//			String digest = Md5Utils.encode("SHA1", bigStr).toLowerCase();
//			// 确认请求来至微信
//			if (digest.equals(signature)) {
//				return echostr;
//			}
//		}
//		return "error";
//	}


	@RequestMapping("/available")
	public @ResponseBody String available() {
		return "Hello World available";
	}

}
