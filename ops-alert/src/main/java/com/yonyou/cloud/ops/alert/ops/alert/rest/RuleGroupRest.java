package com.yonyou.cloud.ops.alert.ops.alert.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.cloud.ops.alert.ops.alert.biz.RuleGroupBiz;
import com.yonyou.cloud.ops.alert.ops.alert.entity.RuleGroup;

@RestController
@RequestMapping("/ruleGroup")
public class RuleGroupRest extends BaseController<RuleGroupBiz, RuleGroup>{

	
}
