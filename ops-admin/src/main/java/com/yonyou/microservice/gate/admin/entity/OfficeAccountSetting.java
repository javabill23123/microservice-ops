package com.yonyou.microservice.gate.admin.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 服务号相关的信息
 * appid/secret/token/AESKey等
 */

@Table(name = "wechat_office_account")
public class OfficeAccountSetting {
	@Id
	private  int id;
	
	private  String serviceNo;
	
	private  String appid;

	private  String appsecret;
	
	private  String token;
	
	private  String menuStr;
	
	private  String aeskey;

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


	public String getAeskey() {
		return aeskey;
	}

	public void setAeskey(String aeskey) {
		this.aeskey = aeskey;
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


	
}
