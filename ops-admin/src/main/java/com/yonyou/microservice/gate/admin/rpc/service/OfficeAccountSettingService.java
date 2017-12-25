package com.yonyou.microservice.gate.admin.rpc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.microservice.gate.admin.biz.OfficeAccountSettingBiz;
import com.yonyou.microservice.gate.admin.entity.OfficeAccountSetting;
import com.yonyou.microservice.gate.common.vo.wechat.MenuUrlInfo;
import com.yonyou.microservice.gate.common.vo.wechat.OfficeAccountSettingInfo;

/**
 * @author joy
 */
@Service
public class OfficeAccountSettingService {
    @Autowired
    private OfficeAccountSettingBiz biz;
    
    public  List<OfficeAccountSettingInfo> getOfficeAccountSettings() {
    	List<OfficeAccountSetting> list=biz.selectListAll();
    	List<OfficeAccountSettingInfo> r=new ArrayList();
    	for(OfficeAccountSetting p:list){
    		OfficeAccountSettingInfo i=new OfficeAccountSettingInfo();
    		i.setId(p.getId());
    		i.setAESKey(p.getAeskey());
    		i.setAppid(p.getAppid());
    		i.setAppsecret(p.getAppsecret());
    		i.setMenuStr(p.getMenuStr());
    		i.setServiceNo(p.getServiceNo());
    		i.setToken(p.getToken());
    		r.add(i);
    	}
        return r;
    }
}
