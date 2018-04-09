package com.yonyou.microservice.gate.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author joy
 *
 *权限忽略定时更新接口
 */
@RestController
public class IgnoreUriController {
	private static Logger logger=Logger.getLogger(IgnoreUriController.class);
	
	@ResponseBody
    @RequestMapping(value = "/ignoreuris/refresh", method = RequestMethod.GET)
	@CacheEvict(value="gate", allEntries=true)
    public Map cleanIgnoreUriCache() {
    	logger.info("--IgnoreUriController cleanIgnoreUriCache was called");
    	Map<String,String> map=new HashMap(16);
    	map.put("message", "ok");
        return map;
    }
}
