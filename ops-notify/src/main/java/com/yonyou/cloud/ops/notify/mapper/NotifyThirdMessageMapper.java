package com.yonyou.cloud.ops.notify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;

import tk.mybatis.mapper.common.Mapper;
/**
 * @author joy
 */
public interface NotifyThirdMessageMapper extends Mapper<NotifyThirdMessage> {

    public List<NotifyThirdMessage> getNotifyMessage(@Param("appId")String appId,@Param("bizId") String bizId);
    
    public List<NotifyThirdMessage> getNotifyMessageByKey(@Param("msgKey")String msgKey);
    
    public List<NotifyThirdMessage> getNotifyMessageByStatus(@Param("status")String status);
    
    public void updateNotisyMessageByMeskey(@Param("status")String status,@Param("msgKey") String msgKey);
}