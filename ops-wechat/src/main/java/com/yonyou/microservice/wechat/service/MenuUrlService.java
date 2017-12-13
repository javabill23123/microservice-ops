package com.yonyou.microservice.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;
import com.yonyou.microservice.wechat.util.feign.IMenuUrlService;

/**
 * ${DESCRIPTION}
 *
 * @author joy
 * @create 2017-06-08 16:23
 */
@Service
public class MenuUrlService  {
	@Autowired
	private IMenuUrlService service;

	private List<MenuUrlInfo> list=null;

    public List<MenuUrlInfo> selectListAll() {
		if(list==null){
			list=service.getMenuUrlAll().getData();
		}
        return list;
    }


	public MenuUrlInfo getMenuUrl(String code){
		List<MenuUrlInfo> tmp=this.selectListAll();
		for(MenuUrlInfo e:tmp){
			if(e.getCode().equals(code)){
				return e;
			}
		}
		return null;
	}
}
