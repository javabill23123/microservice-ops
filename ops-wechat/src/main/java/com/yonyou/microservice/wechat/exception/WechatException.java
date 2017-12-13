package com.yonyou.microservice.wechat.exception;

import com.yonyou.cloud.common.exception.BizException;

public class WechatException extends BizException{

	/**
	 * token错误
	 */
	public static final BizException WECHAT_USER_URL_NOT_FIND = new BizException(90030001, "openid转成用户的url未配置，导致无法通过openid获取用户信息");

}
