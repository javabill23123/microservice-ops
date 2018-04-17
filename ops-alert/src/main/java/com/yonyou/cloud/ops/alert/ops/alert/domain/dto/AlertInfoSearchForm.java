package com.yonyou.cloud.ops.alert.ops.alert.domain.dto;

import java.util.Date;

import com.yonyou.cloud.common.service.utils.EsPageQuery;

public class AlertInfoSearchForm extends EsPageQuery{
	
	/**
	 * alertinfoId
	 */
    private Integer id;
    /**
     * 规则组Id
     */
    private Integer groupId; 
    /**
     * 规则组状态
     */
    private String status; 
    /**
     * 规则组名称
     */
    private String groupName;
    
    private String appName;
    
    private Date startDate;
    
    private Date endDate;
    
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
}
