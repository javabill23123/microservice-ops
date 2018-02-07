package com.yonyou.cloud.ops.alert.ops.alert.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yonyou.cloud.ops.alert.ops.alert.biz.AlertInfoBiz;
 
@RestController
@RequestMapping("/test")
public class testRest {
	@Autowired
	AlertInfoBiz alertInfoBiz;

	@RequestMapping("/go")
	public void goRedis() { 
		alertInfoBiz.sendMail();
	}
}
