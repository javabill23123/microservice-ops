package com.yonyou.microservice.gate.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.microservice.auth.client.jwt.UserAuthUtil;
import com.yonyou.microservice.gate.common.vo.authority.IgnoreUriInfo;
import com.yonyou.microservice.gate.common.vo.authority.PermissionInfo;
import com.yonyou.microservice.gate.common.vo.user.UserInfo;
import com.yonyou.microservice.gate.server.feign.IIgnoreUriService;
import com.yonyou.microservice.gate.server.feign.IUserService;

@Service
public class CacheService {
	private static Logger logger=Logger.getLogger(CacheService.class);
    @Autowired
    IIgnoreUriService iIgnoreUriService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserAuthUtil userAuthUtil;

    @Cacheable(value = "gate.ignoreuri",key="'gate.ignoreuri'")
    public List<IgnoreUriInfo> getIgnoreUris() {
    	logger.info("--getIgnoreUris from db");
        return this.iIgnoreUriService.getIgnoreUris();
    }
    /**
     * 获取所有授权信息
     * @return cache
     */
    @Cacheable(value = "gate",key="'gate.allpermission'")
    public List<PermissionInfo> getAllPermissionInfo(){
    	logger.info("--getAllPermissionInfo from db");
    	return userService.getAllPermissionInfo();
    }
    /**
     * 根据jwt解析用户信息
     * @param authToken
     * @return cache
     * @throws Exception
     */
    @Cacheable(value = "gate",key="'gate.jwt.'+#authToken")
    public IJwtInfo getInfoFromToken(String authToken) throws Exception{
    	logger.info("--getInfoFromToken from jwtUtil"+authToken);
    	return userAuthUtil.getInfoFromToken(authToken);
    }
    @Cacheable(value = "gate",key="'gate.permission.user.'+#username")
    public List<PermissionInfo> getPermissionByUsername(String username){
    	logger.info("--getPermissionByUsername from db");
    	return userService.getPermissionByUsername(username);
    }
    @Cacheable(value = "gate",key="'gate.userInfo.'+#username")
    public UserInfo getUserByUsername(String username){
    	logger.info("--getUserInfoByUsername from db,username="+username);
    	return userService.getUserByUsername(username);
    }
}
