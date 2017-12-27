package com.yonyou.microservice.wechat.dao;

import java.util.List;
import java.util.Map;

import com.yonyou.microservice.wechat.entity.TtWoMassSend;

import tk.mybatis.mapper.common.Mapper;
/**
 * 
 * @author Richard
 *
 */
public interface TtWoMassSendMapper extends Mapper<TtWoMassSend> {
	//void updateMediaId(String navId,String mediaId);
	
	/**
	 * 读取未发送的去重的ObjectId
	 * @return
	 */
	List<TtWoMassSend> selectUnSendWithDistinctObjectId();
	
	/**
	 * 读取未发送的消息
	 * @return
	 */
	List<TtWoMassSend> selectUnSend();
	
	/**
	 * 根据objectId更新mediaId
	 * @param map
	 */
	void updateMediaId(Map<String,String> map);
	
	/**
	 * 根据objectId更新picTextMediaId
	 * @param map
	 */
	void updatePicTextMediaId(Map<String,String> map);
}