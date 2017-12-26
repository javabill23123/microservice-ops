package com.youyou.microservice.auth.server.bean;


import com.yonyou.cloud.common.jwt.IJWTInfo;

/**
 *  @author joy
 */
public class ClientInfo implements IJWTInfo {
    String clientId;
    String name;
    String remark;

    public ClientInfo(String clientId, String name, String id) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;
    }

    public ClientInfo(String clientId, String name, String id,String remark) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;
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
}
