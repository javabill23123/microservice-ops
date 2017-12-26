package com.yonyou.microservice.gate.common.msg;

import com.yonyou.cloud.common.beans.ResultBean;
import com.yonyou.microservice.gate.common.constant.RestCodeConstants;

/**
 * @author joy
 */
public class TokenForbiddenResponse  extends ResultBean {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}
