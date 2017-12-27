package com.yonyou.microservice.wechat.dao;

import org.apache.ibatis.annotations.Param;

import com.yonyou.microservice.wechat.entity.TmPotentialUser;

import tk.mybatis.mapper.common.Mapper;
/**
 * 
 * @author Richard
 *
 */
public interface TmPotentialUserMapper extends Mapper<TmPotentialUser> {
    /**
     * 根据用户id获取openid
     * @param userId
     * @return
     */
    TmPotentialUser queryUserOpenId(@Param("userId") String userId);
    
    
    /**
     * 根据车主id获取openid
     * @param userId
     * @return
     */
    TmPotentialUser queryUserOpenIdByOwnerId(@Param("userId") String userId);
    
}