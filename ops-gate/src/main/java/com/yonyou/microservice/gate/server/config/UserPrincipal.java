package com.yonyou.microservice.gate.server.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.microservice.auth.client.config.UserAuthConfig;
import com.yonyou.microservice.auth.client.jwt.UserAuthUtil;
import com.yonyou.microservice.gate.ratelimit.config.IUserPrincipal;

/**
 * @author joy
 */

public class UserPrincipal implements IUserPrincipal {

    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private UserAuthUtil userAuthUtil;

    @Override
    public String getName(HttpServletRequest request) {
        IJwtInfo infoFromToken = getJwtInfo(request);
        return infoFromToken == null ? null : infoFromToken.getUniqueName();
    }

    private IJwtInfo getJwtInfo(HttpServletRequest request) {
    	IJwtInfo infoFromToken = null;
        try {
            String authToken = request.getHeader(userAuthConfig.getTokenHeader());
            if(StringUtils.isEmpty(authToken)) {
                infoFromToken = null;
            } else {
                infoFromToken = userAuthUtil.getInfoFromToken(authToken);
            }
        } catch (Exception e) {
            infoFromToken = null;
        }
        return infoFromToken;
    }

}
