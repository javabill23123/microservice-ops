package com.yonyou.microservice.auth.client.exception;

/**
 * @author joy
 */
public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
