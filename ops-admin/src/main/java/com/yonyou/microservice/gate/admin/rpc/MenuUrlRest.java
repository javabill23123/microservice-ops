package com.yonyou.microservice.gate.admin.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.gate.admin.rpc.service.MenuUrlService;
import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;

/**
 * ${DESCRIPTION}
 *
 * @author joy
 * @create 2017-06-21 8:15
 */
@RestController
@RequestMapping("api")
public class MenuUrlRest {
    @Autowired
    private MenuUrlService service;

    @RequestMapping(value = "/menuUrl/all",method = RequestMethod.GET, produces="application/json")
    public  @ResponseBody RestResultResponse getMenuUrls() {
    	List<MenuUrlInfo> r=service.getMenuUrls();
        return new RestResultResponse<List<MenuUrlInfo>>().data(r);
    }

}
