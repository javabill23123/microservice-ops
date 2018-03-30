package com.yonyou.cloud.ops.notify.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * @author joy
 */
@Table(name = "notify_third_message")
public class NotifyThirdMessage {
    @Id
    private Integer id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "biz_id")
    private String bizId;

    private String data;

    @Column(name = "notify_url")
    private String notifyUrl;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;
    
    @Column(name = "upd_time")
    private Date uptTime;
    
    @Column(name = "status")
    private String status;
    

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getCrtName() {
		return crtName;
	}

	public void setCrtName(String crtName) {
		this.crtName = crtName;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getUpdName() {
		return updName;
	}

	public void setUpdName(String updName) {
		this.updName = updName;
	}

	public Date getUptTime() {
		return uptTime;
	}

	public void setUptTime(Date uptTime) {
		this.uptTime = uptTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}