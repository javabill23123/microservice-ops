package com.yonyou.microservice.wechat;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * 启动类
 */
@SpringBootApplication
//@EnableTransactionManagement
@EnableFeignClients
//@EnableConfigurationProperties
@EnableDiscoveryClient
public class WeChatPushApplication {

    /**
     * 启动main函数
     * @param args  启动桉树
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {

        SpringApplication.run(WeChatPushApplication.class, args);
    }

}
