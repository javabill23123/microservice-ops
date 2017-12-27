/*
* Copyright 2016 YONYOU Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the YONYOU Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project         marketing

* @Package         com.yonyou.marketing.message.push.entrance

* @Author          LuZhen

* @FileName        PushEntranceConstant

* @Date            16/11/3

----------------------------------------------------------------------------------
*     Date          Who             Version         Comments
* 1. 16/11/3        LuZhen          1.0             Create Source
*
----------------------------------------------------------------------------------
*/
package com.yonyou.microservice.wechat.common;
/**
 * 
 * @author Richard
 *
 */
public class MessagePushConstant {

    //public static final String FACTORY="MESSAGE.FACTORY";

    public static final String QUEUE="MESSAGE.PUSH";

    public static final String QUEUE_TEST="MESSAGE.PUSH.TEST";

    public static final String WECHAT_QUEUE="WECHAT.PUSH";

    public static final String WECHAT_QUEUE_TEST="WECHAT.PUSH.TEST";

    public static final String PROVIDER_DEMO="0";

    public static final String PROVIDER_APP="1";

    public static final String PROVIDER_WEBCHAT="2";

    public static final String PROVIDER_SMS="3";

    public static final String PROVIDER_POOL="providerPool";

    public static final String PROVIDER_THREAD="PUSH-THREAD";

    public static final String PROVIDER_THREAD_CALLBACK="PUSH-THREAD-CALLBACK";

    public static final String IOS="IOS";

    public static final String CUSTOMER="CUSTOMER";

    public static final String PUBLIC="PUBLIC";

    public static final String ANDROID="ANDROID";

    public static final String ALL ="ALL";

    public static final String DEMO ="TEST";
    /**
     * 缩略图 视频、音乐
     */
    public static final String WX_MSG_PROP_THUMB="MEDIA_THUMB_ID";
    /**
     * 描述信息 视频、音乐、
     */
    public static final String WX_MSG_PROP_DESC="DESCRIPTION";
    /**
     * 公众号模板url
     */
    public static final String WX_MSG_PROP_URL="URL";
    /**
     * 公众号模板颜色
     */
    public static final String WX_MSG_PROP_COLOR="COLOR";

    public static final String IOS_MESSAGE_ENCODE = "utf-8";

    public static final Integer IOS_MESSAGE_LENGTH_MAX = 1800;

    public static final Integer MESSAGE_MAX_RETRY_TIMES =3;


    //public static final String PROVIDER_POOL_PATH="classpath:pool.properties";


    /**
     * 消息类型 涵盖了app与webchat的各种类型
     * @author joy
     *
     */
    public enum MESSAGE_CONTENT_TYPE {
    	/**
    	 * 文本类型
    	 */
        TEXT(0),
        /**
         * 图片类型
         */
        IMAGE(1),
        /**
         * 声音类型
         */
        VOICE(2),
        /**
         * 视频类型
         */
        VIDEO(3),
        /**
         * 新闻
         */
        NEWS(4);

        private final Integer type;

        MESSAGE_CONTENT_TYPE(Integer type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type.toString();
        }
    }


    /**
     * 推送渠道
     * @author joy
     *
     */
    public enum PUSH_CHANNEL {
        /**
         * app推送
         */
        APP(PROVIDER_APP),
        /**
         * 微信推送
         */
        WEBCHAT(PROVIDER_WEBCHAT),
        /**
         * 短信推送
         */
        SMS(PROVIDER_SMS), 
        /**
         * 测试
         */
        TEST(PROVIDER_DEMO);

        private final String type;

        PUSH_CHANNEL(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    /**
     * APP平台
     * @author joy
     *
     */
    public enum PUSH_PLATFORM {
    	/**
    	 * ios app
    	 */
        APP_IOS(IOS),
        /**
         * android app
         */
        APP_ANDROID(ANDROID),
        /**
         * all
         */
        APP_ALL(ALL),
        /**
         * 自定义
         */
        WC_CUSTOMER(CUSTOMER),
        /**
         * public
         */
        WC_PUBLIC(PUBLIC),
        /**
         * test
         */
        TEST(DEMO);

        private final String type;

        PUSH_PLATFORM(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public enum RECEIVER_TYPE {
    	/**
    	 * detail
    	 */
        DETAIL(0),
        /**
         * group
         */
        GROUP(1),
        /**
         * all
         */
        ALL(2);

        private final Integer type;

        RECEIVER_TYPE(Integer type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type.toString();
        }
    }

}
