package com.yonyou.microservice.gate.admin.mapper;

import com.yonyou.microservice.gate.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * @author joy
 */
public interface UserMapper extends Mapper<User> {
	/**
	 * 根据分组id查询用户列表
	 * @param groupId
	 * @return 用户列表
	 */
    public List<User> selectMemberByGroupId(@Param("groupId") int groupId);
    /**
     * 根据分组id查询分组领导
     * @param groupId，分组id
     * @return 领导列表
     */
    public List<User> selectLeaderByGroupId(@Param("groupId") int groupId);
}