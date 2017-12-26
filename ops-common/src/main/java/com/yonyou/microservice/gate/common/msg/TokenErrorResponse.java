package com.yonyou.microservice.gate.common.msg;

import com.yonyou.cloud.common.beans.ResultBean;
import com.yonyou.microservice.gate.common.constant.RestCodeConstants;

/**
 * @author joy
 */
public class TokenErrorResponse extends ResultBean {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
