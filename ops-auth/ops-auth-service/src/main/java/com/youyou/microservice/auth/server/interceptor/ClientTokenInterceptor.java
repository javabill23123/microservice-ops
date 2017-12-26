package com.youyou.microservice.auth.server.interceptor;

import com.youyou.microservice.auth.server.configuration.ClientConfig;
import com.youyou.microservice.auth.server.service.AuthService;
import com.youyou.microservice.auth.server.service.ClientService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *  @author joy
 */
public class ClientTokenInterceptor implements RequestInterceptor {
	private static final String URL_AUTH_PROVIDE="/api/authProvider";
    private Logger logger = LoggerFactory.getLogger(ClientTokenInterceptor.class);
    @Autowired
    private ClientConfig clientConfig;
    @Autowired
    private ClientService clientService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
        	if(requestTemplate.toString().contains(URL_AUTH_PROVIDE)){
        		return;
        	}
            requestTemplate.header(clientConfig.getClientTokenHeader(), clientService.apply(clientConfig.getClientId(), clientConfig.getClientSecret()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
