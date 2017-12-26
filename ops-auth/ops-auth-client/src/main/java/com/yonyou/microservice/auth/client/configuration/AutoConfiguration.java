package com.yonyou.microservice.auth.client.configuration;

import com.yonyou.microservice.auth.client.config.ServiceAuthConfig;
import com.yonyou.microservice.auth.client.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author joy
 */
@Configuration
@ComponentScan("com.yonyou.microservice.auth.client")
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }
}
