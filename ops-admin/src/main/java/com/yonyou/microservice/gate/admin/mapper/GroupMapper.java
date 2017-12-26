package com.yonyou.microservice.gate.admin.mapper;

import com.yonyou.microservice.gate.admin.entity.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
/**
 * @author joy
 */
public interface GroupMapper extends Mapper<Group> {
	/**
	 * 根据分组id删除分组人员
	 * @param groupId,分组id
	 */
    public void deleteGroupMembersById (@Param("groupId") int groupId);
    /**
     * 根据分组id删除分组领导
     * @param groupId
     */
    public void deleteGroupLeadersById (@Param("groupId") int groupId);
    /**
     * 新增分组人员
     * @param groupId，分组id
     * @param userId，人员id
     */
    public void insertGroupMembersById (@Param("groupId") int groupId,@Param("userId") int userId);
    /**
     * 新增分组领导
     * @param groupId，分组id
     * @param userId，领导id
     */
    public void insertGroupLeadersById (@Param("groupId") int groupId,@Param("userId") int userId);
}