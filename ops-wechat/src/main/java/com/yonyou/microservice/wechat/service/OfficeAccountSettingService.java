package com.yonyou.microservice.wechat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.microservice.wechat.dao.OfficeAccountSettingMapper;
import com.yonyou.microservice.wechat.entity.OfficeAccountSetting;

/**
 * ${DESCRIPTION}
 *
 * @author joy
 * @create 2017-06-08 16:23
 */
@Service
public class OfficeAccountSettingService extends BaseService<OfficeAccountSettingMapper,OfficeAccountSetting> {

	private List<OfficeAccountSetting> list=null;

	@Override
    public List<OfficeAccountSetting> selectListAll() {
		if(list==null){
			list=mapper.selectAll();
		}
        return list;
    }

	public String getOfficeAccountToken(String code){
		List<OfficeAccountSetting> tmp=this.selectListAll();
		for(OfficeAccountSetting e:tmp){
			if(e.getOfficeAccount().equals(code)){
				return e.getToken();
			}
		}
		return "";
	}

	public OfficeAccountSetting getOfficeAccount(String code){
		List<OfficeAccountSetting> tmp=this.selectListAll();
		for(OfficeAccountSetting e:tmp){
			if(e.getOfficeAccount().equals(code)){
				return e;
			}
		}
		return null;
	}
}
