package com.yonyou.microservice.gate.common.msg;

import com.yonyou.cloud.common.beans.ResultBean;
import com.yonyou.microservice.gate.common.constant.RestCodeConstants;
/**
 * zuul异常统一返回数据的格式
 * @author joy
 *
 */
public class ZuulExceptionResponse  extends ResultBean {
    public ZuulExceptionResponse(String message) {
        super(RestCodeConstants.ZUUL_EXCEPTION_CODE, message);
    }

}
