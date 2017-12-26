package com.yonyou.microservice.gate.admin.mapper;

import com.yonyou.microservice.gate.admin.entity.ResourceAuthority;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
/**
 * @author joy
 */
public interface ResourceAuthorityMapper extends Mapper<ResourceAuthority> {
	/**
	 * 根据角色id和资源类型删除授权
	 * @param authorityId，角色id
	 * @param resourceType，资源类型
	 */
    public void deleteByAuthorityIdAndResourceType(@Param("authorityId")String authorityId,@Param("resourceType") String resourceType);
}