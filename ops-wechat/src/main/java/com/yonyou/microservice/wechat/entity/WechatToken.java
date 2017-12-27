package com.yonyou.microservice.wechat.entity;
/**
 * 
 * @author Richard
 *
 */
public class WechatToken {
	/**
	 * // 获取到的凭证
	 */
	private String accessToken; 
	/**
	 * // 凭证有效时间，单位：秒
	 */
	private String expiresIn; 
	/**
	 * // jsapi_ticket
	 */
	private String ticket; 
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
