package com.youyou.microservice.auth.server.mapper;

import com.youyou.microservice.auth.server.entity.Client;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 *  @author joy
 */
public interface ClientMapper extends Mapper<Client> {
	/**
	 * 获取允许访问的微服务名称列表
	 * @param serviceId
	 * @return
	 */
    @Select(" SELECT\n" +
            "        client.CODE\n" +
            "      FROM\n" +
            "          gate_client client\n" +
            "      INNER JOIN gate_client_service gcs ON gcs.client_id = client.id\n" +
            "    WHERE\n" +
            "        gcs.service_id = #{serviceId}")
    @ResultType(String.class)
    List<String> selectAllowedClient(String serviceId);
}