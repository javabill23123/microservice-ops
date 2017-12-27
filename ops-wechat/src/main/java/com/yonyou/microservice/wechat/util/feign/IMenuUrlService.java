package com.yonyou.microservice.wechat.util.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;
import com.yonyou.microservice.gate.common.vo.wechat.OfficeAccountSettingInfo;


/**
 * ${DESCRIPTION}
 * @author Richard
 *
 */
@FeignClient(value = "ops-admin")
public interface IMenuUrlService {
	/**
	 * 获取所有的菜单url
	 * @return
	 */
  @RequestMapping(value = "/api/menuUrl/all", method = RequestMethod.GET)
  public RestResultResponse<List<MenuUrlInfo>> getMenuUrlAll();
}
