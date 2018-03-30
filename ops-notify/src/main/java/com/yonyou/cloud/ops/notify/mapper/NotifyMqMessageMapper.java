package com.yonyou.cloud.ops.notify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yonyou.cloud.ops.notify.entity.NotifyMqMessage;
import com.yonyou.cloud.ops.notify.entity.NotifyThirdMessage;

import tk.mybatis.mapper.common.Mapper;
/**
 * @author joy
 */
public interface NotifyMqMessageMapper extends Mapper<NotifyMqMessage> {
    
    public List<NotifyMqMessage> getMqMessageByMsgkey(@Param("msgKey")String msgKey);
    
    public void updateMqMessageByMsgkey(@Param("status")String status,@Param("msgKey") String msgKey);
}