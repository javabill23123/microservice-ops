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
 * @author BENJAMIN
 *
 */
public class DynAuthHttpResultHanderFactory {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

//	private static final String HTTP_HANDLER_PACKAGE = "com.youyou.microservice.auth.server.dynauth.handler.impl";// 扫描策略的包
//
//	private ClassLoader classLoader = getClass().getClassLoader();
//
//	private List<Class<? extends DynAuthHttpResultHandler>> httpResluthandlerList;// 策略列表
	
    /**
     * 单例
     */
//    private DynAuthHttpResultHanderFactory() {
//        init();
//    }

	public DynAuthHttpResultHandler createHandler(AuthProvider pInfo) {
		// 在策略列表查找策略
//		for (Class<? extends DynAuthHttpResultHandler> clazz : httpResluthandlerList) {
//			DynHttpAuthHandler  httpAuthHandler= handleAnnotation(clazz);// 获取该策略的注解
//			// 判断策略是否一致
//			if(pInfo.getAcceptType().equals(httpAuthHandler.type())) {
//				try {
//					// 是的话我们返回一个当前策略的实例
//					return clazz.newInstance();
//				} catch (Exception e) {
//					throw new RuntimeException("策略获得失败");
//				}
//			}
//		}
		logger.info("查找对应策略===="+pInfo.getAcceptType());
		return (DynAuthHttpResultHandler)SpringUtil.getBean(pInfo.getAcceptType());
//		throw new RuntimeException("策略获得失败");
	}

//	/**
//	 * 返回一个策略的注解
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	private DynHttpAuthHandler handleAnnotation(Class<? extends DynAuthHttpResultHandler> clazz) {
//		Annotation[] annotations = clazz.getDeclaredAnnotations();
//		if (annotations == null || annotations.length == 0) {
//			return null;
//		}
//		for (int i = 0; i < annotations.length; i++) {
//			if (annotations[i] instanceof DynHttpAuthHandler) {
//				return (DynHttpAuthHandler) annotations[i];
//			}
//		}
//		return null;
//	}
//	
//	//在工厂初始化时要初始化策略列表
//    private void init() {
//    		httpResluthandlerList = new ArrayList<Class<? extends DynAuthHttpResultHandler>>();
//        File[] resources = getResources();//获取到包下所有的class文件
//        Class<DynAuthHttpResultHandler> handerClass = null;
//        try {
//        		handerClass = (Class<DynAuthHttpResultHandler>) classLoader.loadClass(DynAuthHttpResultHandler.class.getName());//使用相同的加载器加载策略接口
//        } catch (ClassNotFoundException e1) {
//            throw new RuntimeException("未找到策略接口");
//        }
//        for (int i = 0; i < resources.length; i++) {
//            try {
//                //载入包下的类
//                Class<?> clazz = classLoader.loadClass(HTTP_HANDLER_PACKAGE + "." + resources[i].getName().replace(".class", ""));
//                //判断是否是实现类，不是自身
//                if (DynAuthHttpResultHandler.class.isAssignableFrom(clazz) && clazz != handerClass) {
//                		httpResluthandlerList.add((Class<? extends DynAuthHttpResultHandler>) clazz);
//                }
//            } catch (ClassNotFoundException e) {
//            		logger.error("没有加载到策略",e);
//            }
//        }
//    }
//    
//  //获取扫描的包下面所有的class文件
//    private File[] getResources() {
//        try {
//            File file = new File(classLoader.getResource(HTTP_HANDLER_PACKAGE.replace(".", "/")).toURI());
//            return file.listFiles(new FileFilter() {
//                public boolean accept(File pathname) {
//                    if (pathname.getName().endsWith(".class")) {
//                        return true;
//                    }
//                    return false;
//                }
//            });
//        } catch (URISyntaxException e) {
//            throw new RuntimeException("未找到策略资源");
//        }
//    }
    
    public static DynAuthHttpResultHanderFactory getInstance() {
        return DynAuthHttpResultHanderFactoryInstance.instance;
    }

    private static class DynAuthHttpResultHanderFactoryInstance {
        private static DynAuthHttpResultHanderFactory instance = new DynAuthHttpResultHanderFactory();
    }


}
