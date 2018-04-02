package com.youyou.microservice.auth.server.service;


import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.youyou.microservice.auth.server.vo.FrontUser;
/**
 *  @author joy
 */
public interface AuthService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
    String login(String username, String password) throws Exception;
    /**
     * 用户手机登录
     * @param phone
     * @param captch
     * @return
     * @throws Exception
     */
    String loginPhone(String phone, String captch) throws Exception;
    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);
    /**
     * 判断token是否有效
     * @param token
     * @throws Exception
     */
    void validate(String token) throws Exception;
    /**
     * 读取用户信息
     * @param token
     * @return
     * @throws Exception
     */
    FrontUser getUserInfo(String token) throws Exception;
    /**
     * 作废token
     * @param token
     * @return
     */
    Boolean invalid(String token);

    /**
     * 根据jwt取得用户信息
     * @param token
     * @return
     */
    IJwtInfo getUserInfoByJwt(String jwt) throws Exception;
}
