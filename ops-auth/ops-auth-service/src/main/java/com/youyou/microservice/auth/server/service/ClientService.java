package com.youyou.microservice.auth.server.service;


import java.util.List;

/**
 *  @author joy
 */
public interface ClientService {
	/**
	 * 生成token
	 * @param clientId
	 * @param secret
	 * @return
	 * @throws Exception
	 */
    public String apply(String clientId, String secret) throws Exception;

    /**
     * 获取授权的客户端列表
     * @param serviceId
     * @param secret
     * @return
     */
    public List<String> getAllowedClient(String serviceId, String secret);
    /**
     * 注册微服务到db，用于微服务之间访问授权
     */
    public void registryClient();
}
