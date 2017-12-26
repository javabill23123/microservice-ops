package com.yonyou.microservice.gate.admin.mapper;

import com.yonyou.microservice.gate.admin.entity.Element;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * @author joy
 */
public interface ElementMapper extends Mapper<Element> {
	/**
	 * 根据userId返回资源列表
	 * @param userId用户id
	 * @return 资源列表
	 */
    public List<Element> selectAuthorityElementByUserId(@Param("userId")String userId);
    /**
     * 根据用户id和菜单id查询授权的资源
     * @param userId，用户id
     * @param menuId，菜单id
     * @return 资源列表
     */
    public List<Element> selectAuthorityMenuElementByUserId(@Param("userId")String userId,@Param("menuId")String menuId);
    /**
     * 根据应用id查询资源列表
     * @param clientId，微服务应用id
     * @return 资源列表
     */
    public List<Element> selectAuthorityElementByClientId(@Param("clientId")String clientId);

}