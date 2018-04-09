package com.youyou.microservice.auth.server.dynauth.handler;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yonyou.cloud.common.reflection.SpringUtil;
import com.youyou.microservice.auth.server.entity.AuthProvider;

/**
 * 策略生成工厂
 * 
 * 原来扫包的方式放弃 工厂直接取IOC中的bean
 * 
 * @author BENJAMIN
 *
 */
public class DynAuthHttpResultHanderFactory {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	public DynAuthHttpResultHandler createHandler(AuthProvider pInfo) {
		logger.info("查找对应策略===="+pInfo.getAcceptType());
		return (DynAuthHttpResultHandler)SpringUtil.getBean(pInfo.getAcceptType());
	}

    
    public static DynAuthHttpResultHanderFactory getInstance() {
        return DynAuthHttpResultHanderFactoryInstance.instance;
    }

    private static class DynAuthHttpResultHanderFactoryInstance {
        private static DynAuthHttpResultHanderFactory instance = new DynAuthHttpResultHanderFactory();
    }


}
