package com.yonyou.microservice.gate.admin.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.microservice.gate.admin.biz.MenuBiz;
import com.yonyou.microservice.gate.admin.entity.Menu;

/**
 * ${DESCRIPTION}
 *
 * @author joy
 * @create 2017-06-12 8:49
 */
@Controller
@RequestMapping("menuUrl")
public class MenuUrlController extends BaseController<MenuBiz, Menu> {
   

}
