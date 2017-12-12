package com.yonyou.microservice.wechat.entity;

import javax.persistence.Table;

/*
 * 服务号相关的信息
 * appid/secret/token/AESKey等
 */

@Table(name = "base_office_account")
public class OfficeAccountSetting {
	private  int id;
	
	private  String officeAccount;
	
	private  String appid;

	private  String appsecret;
	
	private  String token;
	
	private  String menuStr;
	
	private  String AESKey;

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

	public String getAESKey() {
		return AESKey;
	}

	public void setAESKey(String aESKey) {
		AESKey = aESKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfficeAccount() {
		return officeAccount;
	}

	public void setOfficeAccount(String officeAccount) {
		this.officeAccount = officeAccount;
	}
	
}
