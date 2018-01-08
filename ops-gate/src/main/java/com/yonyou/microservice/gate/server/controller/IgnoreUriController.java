package com.yonyou.microservice.gate.server.controller;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.microservice.gate.server.filter.AdminAccessFilter;

@RestController
public class IgnoreUriController {
	private static Logger logger=Logger.getLogger(IgnoreUriController.class);
	
    @RequestMapping(value = "/ignoreuris/refresh", method = RequestMethod.GET)
	@CacheEvict(value="gate.ignoreuri", allEntries=true)
    public String cleanIgnoreUriCache() {
    	logger.info("--IgnoreUriController cleanIgnoreUriCache was called");
        return "ok";
    }
}
