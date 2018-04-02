package com.youyou.microservice.auth.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.jwt.JwtInfo;
import com.youyou.microservice.auth.server.service.AuthService;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationRequest;
import com.youyou.microservice.auth.server.util.user.JwtAuthenticationResponse;
import com.youyou.microservice.auth.server.vo.FrontUser;
/**
 *  @author joy
 */
@RestController
@RequestMapping("jwt")
public class AuthController {
    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
    	if("".equals(authenticationRequest.getPassword())){
    		return ResponseEntity.ok("password can't be null");
    	}
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(String token) throws Exception {
        authService.validate(token);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "invalid", method = RequestMethod.POST)
    public ResponseEntity<?> invalid(@RequestHeader("access-token") String token){
        authService.invalid(token);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = authService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "userInfo", method = RequestMethod.POST)
    public ResponseEntity<?> getUserInfoByJwt(
            @RequestBody String jwt) throws Exception {
    	if(jwt==null || "".equals(jwt)){
    		return ResponseEntity.ok("jwt can't be null");
    	}
    	JwtInfo userInfo=(JwtInfo)authService.getUserInfoByJwt(jwt);
        return ResponseEntity.ok(userInfo);
    }
}
