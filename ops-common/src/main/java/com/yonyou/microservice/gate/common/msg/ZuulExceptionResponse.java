package com.yonyou.microservice.gate.common.msg;

import com.yonyou.cloud.common.beans.ResultBean;
import com.yonyou.microservice.gate.common.constant.RestCodeConstants;

public class ZuulExceptionResponse  extends ResultBean {
    public ZuulExceptionResponse(String message) {
        super(RestCodeConstants.ZUUL_EXCEPTION_CODE, message);
    }

}
