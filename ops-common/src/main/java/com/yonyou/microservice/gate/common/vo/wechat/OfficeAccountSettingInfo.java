package com.yonyou.microservice.gate.common.vo.wechat;


/**
 * 服务号相关的信息
 * appid/secret/token/AESKey等
 * @author joy
 */
public class OfficeAccountSettingInfo {
	private  int id;
	
	private  String serviceNo;
	
	private  String appid;

	private  String appsecret;
	
	private  String token;
	
	private  String menuStr;
	
	private  String aesKey;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}


}
