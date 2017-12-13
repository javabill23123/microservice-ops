package com.yonyou.microservice.gate.admin.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.microservice.gate.admin.biz.OfficeAccountSettingBiz;
import com.yonyou.microservice.gate.admin.entity.OfficeAccountSetting;

/**
 * ${DESCRIPTION}
 *
 * @author joy
 * @create 2017-06-21 8:15
 */
@RestController
@RequestMapping("officeAccountSetting")
public class OfficeAccountSettingController extends BaseController<OfficeAccountSettingBiz, OfficeAccountSetting> {

}
