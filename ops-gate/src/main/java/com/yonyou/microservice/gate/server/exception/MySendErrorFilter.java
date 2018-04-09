package com.yonyou.microservice.gate.server.exception;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yonyou.microservice.gate.common.msg.ZuulExceptionResponse;

/**
 * 
 * @author joy
 * 
 *zuul错误处理类
 */
@Component
public class MySendErrorFilter extends ZuulFilter {

	    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";

	    private static final Log logger= LogFactory.getLog((Class)MySendErrorFilter.class);;

	    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";
	    private String errorPath="/zuul/error";

	    public MySendErrorFilter(){
	    	logger.info("--MySendErrorFilter()");
	    }
	    @Override
	    public String filterType() {
	        return "error";
	    }

	    @Override
	    public int filterOrder() {
	        return 0;
	    }

	    @Override
	    public boolean shouldFilter() {
	        RequestContext ctx = RequestContext.getCurrentContext();
	        return ctx.getThrowable() != null && !ctx.getBoolean("sendErrorFilter.ran", false);
//	        return ctx.getThrowable() != null ;
	    }

	    @Override
	    public Object run() {   
	    	try {
	            final RequestContext ctx = RequestContext.getCurrentContext();
	            final ZuulException exception = this.findZuulException(ctx.getThrowable());
	            final HttpServletRequest request = ctx.getRequest();
//	            request.setAttribute("javax.servlet.error.status_code", (Object)exception.nStatusCode);
	            logger.warn((Object)"Error during filtering", (Throwable)exception);
//	            request.setAttribute("javax.servlet.error.exception", (Object)exception);
//	            if (StringUtils.hasText(exception.errorCause)) {
//	                request.setAttribute("javax.servlet.error.message", "x--x");//(Object)exception.errorCause)
//	            }
//	            final RequestDispatcher dispatcher = request.getRequestDispatcher(this.errorPath);
//	            if (dispatcher != null) {
//	                ctx.set("sendErrorFilter.ran", (Object)true);
//	                if (!ctx.getResponse().isCommitted()) {
//	                    dispatcher.forward((ServletRequest)request, (ServletResponse)ctx.getResponse());
//	                }
//	            }

		        ctx.set("sendErrorFilter.ran", true);
	            ZuulExceptionResponse result=new ZuulExceptionResponse(exception.getMessage());
	            String body=JSON.toJSONString(result);
	            ctx.getResponse().getOutputStream().write(body.getBytes());
	        }
	        catch (Exception ex) {
	            ReflectionUtils.rethrowRuntimeException((Throwable)ex);
	        }
	        return null;

	    }

	    
	    private ZuulException findZuulException(final Throwable throwable) {
	        if (throwable.getCause() instanceof ZuulRuntimeException) {
	            return (ZuulException)throwable.getCause().getCause();
	        }
	        if (throwable.getCause() instanceof ZuulException) {
	            return (ZuulException)throwable.getCause();
	        }
	        if (throwable instanceof ZuulException) {
	            return (ZuulException)throwable;
	        }
	        return new ZuulException(throwable, 200, (String)null);
	    }
	    private Throwable getOriginException(Throwable e){
	        e = e.getCause();
	        while(e.getCause() != null){
	            e = e.getCause();
	        }
	        return e;
	    } 
}
