package com.yonyou.microservice.gate.common.context;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yonyou.cloud.common.jwt.StringHelper;
import com.yonyou.microservice.gate.common.constant.CommonConstants;

/**
 * @author joy
 */
public class BaseContextHandler {
	private static final int MAP_SIZE=300;
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
    	System.out.println("--threadid:"+Thread.currentThread());
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>(MAP_SIZE);
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
    	System.out.println("--threadid:"+Thread.currentThread());
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>(MAP_SIZE);
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserID(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getUsername(){
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }


    public static String getName(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getToken(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }
    public static void setToken(String token){set(CommonConstants.CONTEXT_KEY_USER_TOKEN,token);}

    public static void setName(String name){set(CommonConstants.CONTEXT_KEY_USER_NAME,name);}

    public static void setUserID(String userID){
        set(CommonConstants.CONTEXT_KEY_USER_ID,userID);
    }

    public static void setUsername(String username){
        set(CommonConstants.CONTEXT_KEY_USERNAME,username);
    }

    private static String returnObjectValue(Object value) {
        return value==null?null:value.toString();
    }

    public static void remove(){
        threadLocal.remove();
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class UnitTest {
        private Logger logger = LoggerFactory.getLogger(UnitTest.class);

        @Test
        public void testSetContextVariable() throws InterruptedException {
            BaseContextHandler.set("test", "main");
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("baseContextHandler-pool-%d").build();
            ExecutorService singleThreadPool = new ThreadPoolExecutor(10, 10,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

            singleThreadPool.execute(()->{
                    BaseContextHandler.set("test", "moo");

                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    assertEquals(BaseContextHandler.get("test"), "moo");
                    logger.info("thread one done!");
                });
            singleThreadPool.execute(()->{
                    BaseContextHandler.set("test", "moo2");
                    assertEquals(BaseContextHandler.get("test"), "moo2");
                    logger.info("thread two done!");
                });
            singleThreadPool.shutdown();   

            Thread.currentThread().sleep(5000);
            assertEquals(BaseContextHandler.get("test"), "main");
            logger.info("main one done!");
        }

        @Test
        public void testSetUserInfo(){
            BaseContextHandler.setUserID("test");
            assertEquals(BaseContextHandler.getUserID(), "test");
            BaseContextHandler.setUsername("test2");
            assertEquals(BaseContextHandler.getUsername(), "test2");
        }
    }
}
