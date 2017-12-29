package com.yonyou.dmc.service.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2Doc
public class DmcServiceTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmcServiceTaskApplication.class, args);
	}
}
