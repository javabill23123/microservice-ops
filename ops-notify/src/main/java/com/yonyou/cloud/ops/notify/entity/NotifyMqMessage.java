package com.yonyou.cloud.ops.notify.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * @author joy
 */
@Table(name = "notify_mq_message")
public class NotifyMqMessage {
    @Id
    @Column(name = "msgKey")
    private String msgKey;

    private String data;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "routerKey")
    private String routerKey;

    @Column(name = "consumerClassName")
    private String consumerClassName;

    @Column(name = "bizClassName")
    private String bizClassName;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "crt_time")
    private Date crt_time;
    
    @Column(name = "upd_time")
    private Date uptTime;
    

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRouterKey() {
		return routerKey;
	}

	public void setRouterKey(String routerKey) {
		this.routerKey = routerKey;
	}

	public String getConsumerClassName() {
		return consumerClassName;
	}

	public void setConsumerClassName(String consumerClassName) {
		this.consumerClassName = consumerClassName;
	}

	public String getBizClassName() {
		return bizClassName;
	}

	public void setBizClassName(String bizClassName) {
		this.bizClassName = bizClassName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCrt_time() {
		return crt_time;
	}

	public void setCrt_time(Date crt_time) {
		this.crt_time = crt_time;
	}

	public Date getUptTime() {
		return uptTime;
	}

	public void setUptTime(Date uptTime) {
		this.uptTime = uptTime;
	}

}