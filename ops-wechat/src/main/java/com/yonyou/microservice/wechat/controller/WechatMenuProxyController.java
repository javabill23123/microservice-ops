package com.yonyou.microservice.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;
import com.yonyou.microservice.wechat.common.ApiVersionConsts;
import com.yonyou.microservice.wechat.service.MenuUrlService;
import com.yonyou.microservice.wechat.service.TokenService;

/*
 * 统一微信菜单格式，代理转发到配置的页面
 */
@Controller
@RequestMapping(value ="/wechat" )
public class WechatMenuProxyController {

	private static Logger logger=Logger.getLogger(WechatMenuProxyController.class);

	@Autowired
	private TokenService tokenService;
    @Autowired
    private MenuUrlService menuUrlService;
//
//	@RequestMapping("/portalredirect/{id}")
//	public String portalredirect(@PathVariable("id") String id) {
//		logger.info("----------------portalredirect");
//		String dm = env.getProperty("wechat.domain");
//		String appid = env.getProperty("wechat.appid");
//		String u = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http://" + dm
//				+ "/wx/portal/" + id + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
//		return "redirect:" + u;
//	}

	@RequestMapping("/proxy/{serviceNo}/{id}")
    public RestResultResponse<String> proxy(@PathVariable("serviceNo") String serviceNo,@PathVariable("id") String id,
    		HttpServletRequest request) throws Exception{
        logger.info("菜单Code为>>>>>>>"+id);
//        String value = CookieUtil.getOpenid(request);
//        logger.info("获取到的OPENID为>>>>>>>"+value);
        MenuUrlInfo m=menuUrlService.getMenuUrl(id);
        String url="redirect:"+m.getUrl();
        logger.info("菜单url为>>>>>>>"+url);
        return new RestResultResponse<String>().data(url);
    }

}
