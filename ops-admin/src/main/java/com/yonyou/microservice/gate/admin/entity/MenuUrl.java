package com.yonyou.microservice.gate.admin.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信菜单与url对应的信息
 * @author joy
 */
@Table(name = "wechat_menu_url")
public class MenuUrl {
	@Id
	private  int id;
	
	private  String code;
	
	private  String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
