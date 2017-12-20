package com.youyou.microservice.auth.server.util.user;

import java.io.Serializable;

public class JwtAuthenticationDataResponse  {//implements Serializable

    private final String jwt;
    private final String rData;

    public JwtAuthenticationDataResponse(String jwt,String data) {
        this.jwt = jwt;
        this.rData=data;
    }

	public String getJwt() {
		return jwt;
	}

	public String getrData() {
		return rData;
	}
    
}
