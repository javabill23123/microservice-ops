package com.youyou.microservice.auth.server.bean;


import java.util.HashMap;
import java.util.Map;

import com.yonyou.cloud.common.jwt.IJwtInfo;

/**
 *  @author joy
 */
public class ClientInfo implements IJwtInfo {
	private String clientId;
	private String name;
	private String remark;
	private String dealerName;
	private String dealerCode;
    private String telPhone;
    private boolean kickOut;
    private Map<String,String> params;

    public ClientInfo(String clientId, String name, String id) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;
    }

    public ClientInfo(String clientId, String name, String id,String dealerCode,
    		String dealerName,String telPhone,boolean kickOut,Map<String,String> params,String remark) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;
        this.remark=remark;
        this.dealerCode=dealerCode;
        this.dealerName=dealerName;
        this.telPhone=telPhone;
        this.kickOut=kickOut;
        this.params=params;
        this.remark=remark;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getUniqueName() {
        return clientId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
	public String getRemark() {
		return this.remark;
	}

    @Override
	public String getDealerName() {
		return dealerName;
	}

    @Override
	public String getDealerCode() {
		return dealerCode;
	}

    @Override
	public String getTelPhone() {
		return telPhone;
	}

	@Override
	public boolean getKickout() {
		return kickOut;
	}

	@Override
	public Map<String, String> getParam() {
		if(params==null){
			params=new HashMap(16);
		}
		return params;
	}

}
