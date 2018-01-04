package com.yonyou.microservice.gate.server.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IgnoreUriController {
	
    @RequestMapping(value = "/ignoreuris/refresh", method = RequestMethod.GET)
	@CacheEvict(value="gate.ignoreuri", allEntries=true)
    public String cleanIgnoreUriCache() {
        return "ok";
    }
}
