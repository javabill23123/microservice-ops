package com.yonyou.microservice.auth.client.exception;

/**
 * @author joy
 */
public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
