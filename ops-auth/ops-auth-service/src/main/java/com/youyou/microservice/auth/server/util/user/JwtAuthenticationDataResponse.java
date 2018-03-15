package com.youyou.microservice.auth.server.util.user;

import java.io.Serializable;

import com.xiaoleilu.hutool.json.JSONObject;
/**
 *  @author joy
 */
public class JwtAuthenticationDataResponse  implements Serializable{

	private static final long serialVersionUID = 3499186841961348924L;
	private final String jwt;
    private final JSONObject rData;

    public JwtAuthenticationDataResponse(String jwt,JSONObject data) {
        this.jwt = jwt;
        this.rData=data;
    }

	public String getJwt() {
		return jwt;
	}

	public JSONObject getrData() {
		return rData;
	}
    
}
