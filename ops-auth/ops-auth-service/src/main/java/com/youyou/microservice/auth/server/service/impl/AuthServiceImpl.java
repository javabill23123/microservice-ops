package com.youyou.microservice.auth.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.jwt.JwtInfo;
import com.yonyou.microservice.gate.common.constant.CommonConstants;
import com.yonyou.microservice.gate.common.vo.authority.PermissionInfo;
import com.yonyou.microservice.gate.common.vo.user.UserInfo;
import com.youyou.microservice.auth.server.feign.IUserService;
import com.youyou.microservice.auth.server.service.AuthService;
import com.youyou.microservice.auth.server.util.user.JwtTokenUtil;
import com.youyou.microservice.auth.server.vo.FrontUser;
/**
 *  @author joy
 */
@Service
public class AuthServiceImpl implements AuthService {
	private static final String TEST_CAPTCHA="8888";
	private static Logger logger=Logger.getLogger(AuthServiceImpl.class);

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
//    @Autowired
//	 MqSender mqSender;
    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(String username, String password) throws Exception {
//    	Map<String,String> msg = new HashMap();
//		msg.put("username", username);
//		msg.put("loginTime",new Date().toString());
//		JSONObject json=JSONObject.fromObject(msg);
//		mqSender.justSend("auth-user", "login", json.toString());
        UserInfo info = userService.getUserByUsername(username);
        String token = "";
        if (encoder.matches(password, info.getPassword())) {
            token = jwtTokenUtil.generateToken(new JwtInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        return token;
    }
    @Override
    public String loginPhone(String phone, String captcha) throws Exception{
    	logger.info("--AuthServiceImpl,phone="+phone+",captcha="+captcha);
        UserInfo info = userService.getUserByPhone(phone);
        String token = "";
        if (info!=null&& TEST_CAPTCHA.equals(captcha)) {
            token = jwtTokenUtil.generateToken(new JwtInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        return token;
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public FrontUser getUserInfo(String token) throws Exception {
        String username = jwtTokenUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        UserInfo user = userService.getUserByUsername(username);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        List<PermissionInfo> permissionInfos = userService.getPermissionByUsername(username);
        Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setMenus(menus.collect(Collectors.toList()));
        Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
            return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setElements(elements.collect(Collectors.toList()));
        return frontUser;
    }

    @Override
    public Boolean invalid(String token) {
        // TODO: 2017/9/11 注销token
        return null;
    }

    @Override
    public String refresh(String oldToken) {
        // TODO: 2017/9/11 刷新token
        return null;
    }
}
