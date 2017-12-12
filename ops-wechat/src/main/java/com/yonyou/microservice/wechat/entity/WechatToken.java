package com.yonyou.microservice.wechat.entity;

public class WechatToken {

	private String accessToken; // 获取到的凭证
	private String expiresIn; // 凭证有效时间，单位：秒
	private String ticket; // jsapi_ticket
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
