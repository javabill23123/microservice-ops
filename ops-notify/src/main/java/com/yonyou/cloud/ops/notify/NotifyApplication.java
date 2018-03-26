package com.yonyou.cloud.ops.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.spring4all.swagger.EnableSwagger2Doc;

@SpringBootApplication
@EnableSwagger2Doc
@EnableDiscoveryClient
public class NotifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyApplication.class, args);
	}
}
