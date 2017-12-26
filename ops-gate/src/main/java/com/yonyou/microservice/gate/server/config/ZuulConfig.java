package com.yonyou.microservice.gate.server.config;

import org.springframework.context.annotation.Bean;

import com.yonyou.microservice.auth.client.interceptor.ServiceFeignInterceptor;

/**
 * @author joy
 * @Configuration
 */
public class ZuulConfig {
    @Bean
    ServiceFeignInterceptor getClientTokenInterceptor(){
        return new ServiceFeignInterceptor();
    }
}
