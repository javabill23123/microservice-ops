package com.yonyou.microservice.gate.admin.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.microservice.gate.admin.biz.GroupBiz;
import com.yonyou.microservice.gate.admin.vo.GroupUsers;

@RestController
@RequestMapping("api")
public class GroupRest {

	@Autowired
	private GroupBiz groupbiz;

	/**
	 * 根据用户组ID获取用户组信息和用户组下用户列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/group", method = RequestMethod.GET)
	@ResponseBody
	public RestResultResponse<GroupUsers> getUsers(@PathVariable int id) {
		return new RestResultResponse<GroupUsers>().success(true).data(groupbiz.getGroupInfoAndUsers(id));
	}
}
