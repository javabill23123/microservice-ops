package com.yonyou.microservice.gate.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.yonyou.cloud.common.jwt.IJwtInfo;
import com.yonyou.cloud.common.jwt.JwtInfo;
import com.yonyou.cloud.common.jwt.StringHelper;
import com.yonyou.microservice.auth.client.jwt.UserAuthUtil;
import com.yonyou.microservice.gate.common.vo.authority.IgnoreUriInfo;
import com.yonyou.microservice.gate.common.vo.authority.PermissionInfo;
import com.yonyou.microservice.gate.common.vo.user.UserInfo;
import com.yonyou.microservice.gate.server.feign.IIgnoreUriService;
import com.yonyou.microservice.gate.server.feign.IUserService;
/**
 * 
 * @author joy
 *@create 2018-01-9
 * 缓存服务类，对特定数据做缓存
 */
@Service
public class CacheService {
	private static Logger logger=Logger.getLogger(CacheService.class);
    @Autowired
    IIgnoreUriService iIgnoreUriService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Autowired
    private RedisTemplate redisTemplate;
	@Value("${redis.expire}")
    private int expire;

	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
	    RedisSerializer stringSerializer = new StringRedisSerializer();
	    redisTemplate.setKeySerializer(stringSerializer);
	    redisTemplate.setValueSerializer(stringSerializer);
	    redisTemplate.setHashKeySerializer(stringSerializer);
	    redisTemplate.setHashValueSerializer(stringSerializer);
	    this.redisTemplate = redisTemplate;
	}
	
    @Cacheable(value = "gate",key="'gate.ignoreuri'")
    public List<IgnoreUriInfo> getIgnoreUris() {
    	logger.info("--getIgnoreUris from db");
        return this.iIgnoreUriService.getIgnoreUris();
    }
    /**
     * 获取所有授权信息
     * @return cache
     */
    @Cacheable(value = "gate",key="'gate.allpermission'")
    public List<PermissionInfo> getAllPermissionInfo(){
    	logger.info("--getAllPermissionInfo from db");
    	return userService.getAllPermissionInfo();
    }
    /**
     * 根据jwt解析用户信息
     * @param authToken
     * @return cache
     * @throws Exception
     */
    public IJwtInfo getInfoFromToken(String authToken) throws Exception{
    	logger.info("--getInfoFromToken from jwtUtil,"+authToken);
    	IJwtInfo result=null;
    	try{
        	//根据jwt取jwtInfo
        	Object jwtInfo=redisTemplate.opsForValue().get(authToken);
        	if(jwtInfo!=null){
        		//取得登录账号
        		String loginName=((IJwtInfo)jwtInfo).getUniqueName();
        		logger.info("--jwt,loginName="+loginName);
        		//根据账号取得有效的jwt
            	Object jwt=redisTemplate.opsForValue().get(loginName);
            	if(jwt!=null){
            		//当前jwt与有效jwt是否匹配
            		boolean r=authToken.equals(jwt);
            		boolean kickOut=((IJwtInfo)jwtInfo).getKickout();
            		//当前jwt与有效jwt匹配，或者不互踢
            		if(r||!kickOut){
            			//设置jwt有效期
            			redisTemplate.expire(authToken, expire, TimeUnit.SECONDS); 
            			//设置loginName有效期
            			redisTemplate.expire(loginName, expire, TimeUnit.SECONDS); 
            			result=(IJwtInfo)jwtInfo;
                		logger.info("--延长jwt和登录账号有效期"+loginName);
            		}else{
                		logger.info("--jwt已经被踢掉,loginName="+loginName+",jwt="+authToken+",currentJwt="+jwt);
            		}
            	}else{//根据账号没有找到有效的jwt，则认为当前这个jwt即为有效的jwt
            		logger.info("--根据登录账号没有找到有效的jwt");
            		//缓存登录账号有效的jwt
            		redisTemplate.opsForValue().set(loginName, authToken, expire, TimeUnit.SECONDS); 
        			//设置jwt有效期
        			redisTemplate.expire(authToken, expire, TimeUnit.SECONDS); 
        			result=(IJwtInfo)jwtInfo;
            		logger.info("--设置账号有效的jwt,loginName="+loginName+",jwt="+authToken);
            	}
        	}else{//如果jwt在缓存找不到，则认为这个jwt是新的有效jwt
        		logger.info("--没有jwt缓存信息");
        		IJwtInfo info=userAuthUtil.getInfoFromToken(authToken);
        		//根据jwt缓存用户信息
        		redisTemplate.opsForValue().set(authToken, info, expire, TimeUnit.SECONDS); 
        		logger.info("--缓存信息jwt,"+authToken);
        		String loginName=info.getUniqueName();
        		//根据用户账号缓存有效的jwt，踢掉其它的jwt
        		redisTemplate.opsForValue().set(loginName, authToken, expire, TimeUnit.SECONDS); 
        		logger.info("--用登录账号缓存有效的jwt,loginName="+loginName);
        		result= info;
        	}
    	}catch(Exception e){
    		logger.info("--redis发生异常，从jwt取信息");
    		logger.error(e);
    		IJwtInfo info=userAuthUtil.getInfoFromToken(authToken);
    		try{
        		//根据jwt缓存用户信息
        		redisTemplate.opsForValue().set(authToken, info, expire, TimeUnit.SECONDS); 
    		}catch(Exception f){
    			logger.error(f);
    		}
    		result= info;
    	}
    	return result;
//    	return null;
    }
    
    /**
     * DMSredis解析方式
     * @param authToken
     * @return
     * @throws Exception
     */
    public IJwtInfo getInfoFromTokenDms(String authToken) throws Exception{
    	logger.info("--getInfoFromTokenDms from jwtUtil,"+authToken);
    	IJwtInfo result=null;
    	try{
    		JWT jwt = JWT.decode(authToken);
            String uid = jwt.getClaim("uid").asString();
            logger.info("--getInfoFromTokenDms uid="+uid);
            setRedisTemplate(redisTemplate);

//        	Object jwtInfo=redisTemplate.opsForValue().get("USR_CTX:"+uid);
//        	 logger.info("--getInfoFromTokenDms jwtInfo="+jwtInfo);
            Object loginInfoObj = redisTemplate.opsForHash().get("USR_CTX:"+uid, "\"loginInfo\"");
            logger.info("--getInfoFromTokenDms loginInfoObj="+"USR_CTX:"+uid+"=\\\"loginInfo\\\"");
            logger.info("--getInfoFromTokenDms loginInfoObj="+loginInfoObj);
            Object loginInfoObj3 = redisTemplate.opsForValue().get("test");
            logger.info("--getInfoFromTokenDms loginInfoObj3="+loginInfoObj3);
            redisTemplate.opsForValue().set("mygate", "mygate");
            Object loginInfoObj11 = redisTemplate.opsForHash().get("USR_CTX:"+uid, "loginInfo");
            logger.info("--getInfoFromTokenDms loginInfoObj11="+loginInfoObj11);
            Object loginInfoObj12 = redisTemplate.opsForHash().get("USR_CTX:"+uid, "\"loginInfo\"");
            logger.info("--getInfoFromTokenDms loginInfoObj12="+loginInfoObj12);
            Object userAccessInfoObj = redisTemplate.opsForHash().get("USR_CTX:"+uid, "\"userAccessInfo\"");
            if(loginInfoObj!=null){
//        		Map<String,Object> jwtMap = (Map<String,Object>)jwtInfo;
        				
        				//jwtMap.get("loginInfo");
//        		 logger.info("--getInfoFromTokenDms loginInfoObj="+loginInfoObj);
//        		if(null != loginInfoObj){
//        			logger.info("--getInfoFromTokenDms loginInfo="+loginInfo);
        			
        			JSONObject json = JSONObject.parseObject(loginInfoObj.toString());  
        			String dealerCode= StringHelper.getObjectValue(json.getString("dealerCode")).trim();
        			String dealerName= StringHelper.getObjectValue(json.getString("dealerName")).trim();
//        			String orgName= StringHelper.getObjectValue(json.getString("orgName")).trim();
        			String userAccount= StringHelper.getObjectValue(json.getString("userAccount")).trim();
        			String userId=  StringHelper.getObjectValue(json.getString("userId")).trim();
        			String userIdT = StringHelper.getObjectValue(userId.substring(userId.indexOf(",")+1, userId.indexOf("]"))).trim();
        			String userName= StringHelper.getObjectValue(json.getString("userName"));
//    				logger.info("--getInfoFromTokenDms loginInfo.getUserName="+loginInfo.getUserName());
        			
        			 logger.info("--getInfoFromTokenDms dealerCode="+dealerCode);
        			 logger.info("--getInfoFromTokenDms dealerName="+dealerName);
        			 logger.info("--getInfoFromTokenDms userAccount="+userAccount);
        			 logger.info("--getInfoFromTokenDms userId="+userId);
        			 logger.info("--getInfoFromTokenDms userIdT="+userIdT);
        			 logger.info("--getInfoFromTokenDms userName="+userName);
        			 
        			
					/**
					 * map存放的数据
					 */
        			String groupCode= StringHelper.getObjectValue(json.getString("groupCode")).trim();
        			String purchaseDepot= StringHelper.getObjectValue(json.getString("purchaseDepot")).trim();
        			String carLoadDepot= StringHelper.getObjectValue(json.getString("carLoadDepot")).trim();
        			String suppliesDepot= StringHelper.getObjectValue(json.getString("suppliesDepot")).trim();
        			String repair= StringHelper.getObjectValue(json.getString("repair")).trim();
        			String purchase= StringHelper.getObjectValue(json.getString("purchase")).trim();
        			String isPartCustomer= StringHelper.getObjectValue(json.getString("isPartCustomer")).trim();
        			String orgType= StringHelper.getObjectValue(json.getString("orgType")).trim();
        			
	       			 logger.info("--getInfoFromTokenDms repair befer="+repair);
	       			 logger.info("--getInfoFromTokenDms purchase befer="+purchase);
	       			 repair = repair.replaceAll("\"@class\":\"java.util.HashMap\",", "");
	       			 purchase = purchase.replaceAll("\"@class\":\"java.util.HashMap\",", "");
        			 logger.info("--getInfoFromTokenDms purchaseDepot="+purchaseDepot);
        			 logger.info("--getInfoFromTokenDms carLoadDepot="+carLoadDepot);
        			 logger.info("--getInfoFromTokenDms suppliesDepot="+suppliesDepot);
        			 logger.info("--getInfoFromTokenDms repair after="+repair);
        			 logger.info("--getInfoFromTokenDms purchase after="+purchase);
    				Map<String,String> map=new HashMap<String,String>();
    				map.put("GROUP_CODE", groupCode);
    				map.put("PURCHASE_DEPOT", purchaseDepot);
    				map.put("CAR_LOAD_DEPOT", carLoadDepot);
    				map.put("SUPPLIES_DEPOT", suppliesDepot);
    				map.put("REPAIR_PARAMETER", repair);
    				map.put("PART_PARAMETER", purchase);
    				map.put("IS_PART_CUSTOMER", isPartCustomer);
    				map.put("ORG_TYPE", orgType);
    				map.put("AUTH_TOKEN", authToken);//token
    				map.put("USER_ACCESS_INFO", userAccessInfoObj==null?"":userAccessInfoObj.toString());
    				
    				result = new JwtInfo(
    						userAccount,
    						userIdT,
    						userName,
    						dealerCode,
    						dealerName,
    						"",
    						true,map,"根据token，生成用户信息"
    				);
//        			}
        	}
    	}catch(Exception e){
    		logger.info("--redis发生异常，从jwt取信息");
    		logger.error(e);
    	}
    	return result;
    }
    
    @Cacheable(value = "gate",key="'gate.permission.user.'+#username")
    public List<PermissionInfo> getPermissionByUsername(String username){
    	logger.info("--getPermissionByUsername from db");
    	return userService.getPermissionByUsername(username);
    }
    @Cacheable(value = "gate",key="'gate.userInfo.'+#username")
    public UserInfo getUserByUsername(String username){
    	logger.info("--getUserInfoByUsername from db,username="+username);
    	return userService.getUserByUsername(username);
    }
}
