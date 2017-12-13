package com.yonyou.microservice.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.microservice.gate.common.vo.wechat.OfficeAccountSettingInfo;
import com.yonyou.microservice.wechat.util.feign.IOfficeAccountSettingService;

/**
 * ${DESCRIPTION}
 *
 * @author joy
 * @create 2017-06-08 16:23
 */
@Service
public class OfficeAccountSettingService  {
	@Autowired
	private IOfficeAccountSettingService service;

	private List<OfficeAccountSettingInfo> list=null;

    public List<OfficeAccountSettingInfo> selectListAll() {
		if(list==null){
			list=service.getOfficeAccountSettingAll().getData();
		}
        return list;
    }

	public String getOfficeAccountToken(String code){
		List<OfficeAccountSettingInfo> tmp=this.selectListAll();
		for(OfficeAccountSettingInfo e:tmp){
			if(e.getServiceNo().equals(code)){
				return e.getToken();
			}
		}
		return "";
	}

	public OfficeAccountSettingInfo getOfficeAccount(String code){
		List<OfficeAccountSettingInfo> tmp=this.selectListAll();
		for(OfficeAccountSettingInfo e:tmp){
			if(e.getServiceNo().equals(code)){
				return e;
			}
		}
		return null;
	}
}
