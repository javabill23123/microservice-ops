package com.yonyou.microservice.gate.admin.rpc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.microservice.gate.admin.biz.MenuUrlBiz;
import com.yonyou.microservice.gate.admin.entity.MenuUrl;
import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;

/**
 * @author joy
 */
@Service
public class MenuUrlService {
    @Autowired
    private MenuUrlBiz menuUrlBiz;
    
    public  List<MenuUrlInfo> getMenuUrls() {
    	List<MenuUrl> list=menuUrlBiz.selectListAll();
    	List<MenuUrlInfo> r=new ArrayList();
    	for(MenuUrl p:list){
    		MenuUrlInfo i=new MenuUrlInfo();
    		i.setId(p.getId());
    		i.setCode(p.getCode());
    		i.setUrl(p.getUrl());
    		r.add(i);
    	}
        return r;
    }
}
