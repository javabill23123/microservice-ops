package com.yonyou.microservice.gate.server.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.cloud.common.service.utils.ClientUtil;
import com.yonyou.microservice.auth.client.config.ServiceAuthConfig;
import com.yonyou.microservice.auth.client.config.UserAuthConfig;
import com.yonyou.microservice.auth.client.jwt.ServiceAuthUtil;
import com.yonyou.microservice.auth.client.jwt.UserAuthUtil;
import com.yonyou.microservice.gate.common.context.BaseContextHandler;
import com.yonyou.microservice.gate.common.msg.TokenErrorResponse;
import com.yonyou.microservice.gate.common.msg.TokenForbiddenResponse;
import com.yonyou.microservice.gate.common.vo.authority.IgnoreUriInfo;
import com.yonyou.microservice.gate.common.vo.authority.PermissionInfo;
import com.yonyou.microservice.gate.common.vo.log.LogInfo;
import com.yonyou.microservice.gate.common.vo.user.UserInfo;
import com.yonyou.microservice.gate.server.feign.IIgnoreUriService;
import com.yonyou.microservice.gate.server.feign.ILogService;
import com.yonyou.microservice.gate.server.feign.IUserService;
import com.yonyou.microservice.gate.server.utils.DbLog;

import lombok.extern.slf4j.Slf4j;

/**
 * gateway filter
 * filter type pre
 * 可以通过配置 gate.ignore.startWith 来忽略经过过滤器的请求
 *
 * @author joy
 * @create 2017-06-23 8:25
 */
@Component
@Slf4j
public class AdminAccessFilter extends ZuulFilter {
	private static final String USER_HEAD_ID="userId";
	private static final String USER_HEAD_NAME="userName";
	private static final String USER_HEAD_REMARK="remark";
	private static final String PERMISSION="permission";
	private static final String HTTP_GET="GET";
	private static Logger logger=Logger.getLogger(AdminAccessFilter.class);

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ILogService logService;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Value("${zuul.prefix}")
    private String zuulPrefix;
    
    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private ServiceAuthUtil serviceAuthUtil;
    
    @Autowired
    RequestMappingHandlerMapping mapping1;
    
    @Autowired
    IIgnoreUriService iIgnoreUriService;
    
    private List<IgnoreUriInfo> startWithList;
    
    
    public AdminAccessFilter(){
    	logger.info("--AdminAccessFilter对象创建");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
    	logger.info("--AdminAccessFilter.run(),进入网关");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        BaseContextHandler.setToken(null);
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            return null;
        }
        IJwtInfo user = null;
        try {
        	//从JWT中解析出用户信息
            user = getJWTUser(request,ctx);
        } catch (Exception e) {
        	//如果jwt中的用户信息获取失败，这块返回信息可能要改成统一的response格式
            setFailedRequest(JSON.toJSONString(new TokenErrorResponse(e.getMessage())),200);
            return null;
        }
        List<PermissionInfo> permissionInfos = this.getAllPermissionInfo();
        // 判断资源是否启用权限约束
        Collection<PermissionInfo> result = getPermissionInfos(requestUri, method, permissionInfos);
        if(result.size()>0){
			checkAllow(requestUri, method, ctx, user.getUniqueName());
        }
        // 申请客户端密钥头
        ctx.addZuulRequestHeader(serviceAuthConfig.getTokenHeader(),serviceAuthUtil.getClientToken());
        ctx.addZuulRequestHeader(USER_HEAD_ID,user.getId());
        ctx.addZuulRequestHeader(USER_HEAD_NAME,user.getUniqueName());
        ctx.addZuulRequestHeader(USER_HEAD_REMARK,user.getRemark());
        BaseContextHandler.remove();
        return null;
    }

    /**
     * 获取目标权限资源
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return cache
     */
    @Cacheable(value = "gate",key="'gate.permission.'+#requestUri+#method")
    private Collection<PermissionInfo> getPermissionInfos(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
        return Collections2.filter(serviceInfo, new Predicate<PermissionInfo>() {
                @Override
                public boolean apply(PermissionInfo permissionInfo) {
                    String url = permissionInfo.getUri();
                    String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                    String regEx = "^" + uri + "$";
                    return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                            && method.equals(permissionInfo.getMethod());
                }
            });
    }

    private void setCurrentUserInfoAndLog(RequestContext ctx, String username, PermissionInfo pm) {
        UserInfo info = userService.getUserByUsername(username);
        String host =  ClientUtil.getClientIp(ctx.getRequest());
        ctx.addZuulRequestHeader("userId", info.getId());
        try {
        	String name=URLEncoder.encode(info.getName(),"utf-8");
			ctx.addZuulRequestHeader("userName", name);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
        ctx.addZuulRequestHeader("userHost", ClientUtil.getClientIp(ctx.getRequest()));
        LogInfo logInfo = new LogInfo(pm.getMenu(),pm.getName(),pm.getUri(),new Date(),info.getId(),info.getName(),host);
        DbLog.getInstance().setLogService(logService).offerQueue(logInfo);
    }

    /**
     * 从header中取到JWT
     * 优先从header中取
     * 没有就从参数中取token参数
     * 
     * @param request
     * @param ctx
     * @return
     */
    private IJwtInfo getJWTUser(HttpServletRequest request,RequestContext ctx) throws Exception {
        String authToken = request.getHeader(userAuthConfig.getTokenHeader());
        if(StringUtils.isBlank(authToken)){
            authToken = request.getParameter("Authorization");
        }
        ctx.addZuulRequestHeader(userAuthConfig.getTokenHeader(),authToken);
        //将token放到threadlocal中
        BaseContextHandler.setToken(authToken);
        return this.getInfoFromToken(authToken);
    }

    /**
     * 读取权限
     * @param request
     * @param username
     * @return
     */
    private List<PermissionInfo> getPermissionInfos(HttpServletRequest request, String username) {
        List<PermissionInfo> permissionInfos;
        if (request.getSession().getAttribute(PERMISSION) == null) {
            permissionInfos = this.getPermissionByUsername(username);
            request.getSession().setAttribute("permission", permissionInfos);
        } else {
            permissionInfos = (List<PermissionInfo>) request.getSession().getAttribute("permission");
        }
        return permissionInfos;
    }

    /**
     * 权限校验
     * @param requestUri
     * @param method
     * @throws UnsupportedEncodingException
     */
    private boolean checkAllow(final String requestUri, final String method ,RequestContext ctx,String username) {
        log.debug("uri：" + requestUri + "----method：" + method);
        List<PermissionInfo> permissionInfos = getPermissionInfos(ctx.getRequest(), username) ;
        Collection<PermissionInfo> result = getPermissionInfos(requestUri, method, permissionInfos);
        if (result.size() <= 0) {
            setFailedRequest(JSON.toJSONString(new TokenForbiddenResponse("Token Forbidden!")), 200);
        } else{
            PermissionInfo[] pms =  result.toArray(new PermissionInfo[]{});
            PermissionInfo pm = pms[0];
            if(!HTTP_GET.equals(method)){
                setCurrentUserInfoAndLog(ctx, username, pm);
            }
        }
        return true;
    }


    /**
     * URI是否以什么打头
     * @param requestUri
     * @return cache
     */
    @Cacheable(value = "gate",key="'gate.ignoreurl.'+#requestUri")
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        if(startWithList==null){
        	startWithList=this.iIgnoreUriService.getIgnoreUris();
        }
        for (IgnoreUriInfo s : startWithList) {
            if (requestUri.startsWith(s.getUri())) {
                return true;
            }
        }
        return flag;
    }
    /**
     * 获取所有授权信息
     * @return cache
     */
    @Cacheable(value = "gate",key="gate.allpermission")
    private List<PermissionInfo> getAllPermissionInfo(){
    	return userService.getAllPermissionInfo();
    }
    /**
     * 根据jwt解析用户信息
     * @param authToken
     * @return cache
     * @throws Exception
     */
    @Cacheable(value = "gate",key="'gate.jwt.'+#authToken")
    private IJwtInfo getInfoFromToken(String authToken) throws Exception{
    	return userAuthUtil.getInfoFromToken(authToken);
    }
    @Cacheable(value = "gate",key="'gate.permission.user.'+#username")
    private List<PermissionInfo> getPermissionByUsername(String username){
    	return userService.getPermissionByUsername(username);
    }
    /**
     * Reports an error message given a response body and code.
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
//            throw new RuntimeException("Code: " + code + ", " + body); //optional
        }
    }
}
