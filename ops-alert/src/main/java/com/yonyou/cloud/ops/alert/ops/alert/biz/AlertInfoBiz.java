package com.yonyou.cloud.ops.alert.ops.alert.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yonyou.cloud.common.beans.PageResultResponse;
import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.common.service.utils.PageQuery;
import com.yonyou.cloud.ops.alert.ops.alert.alarm.AlarmMessageContext;
import com.yonyou.cloud.ops.alert.ops.alert.alarm.mails.EmailMessage;
import com.yonyou.cloud.ops.alert.ops.alert.domain.constants.AlertStatus;
import com.yonyou.cloud.ops.alert.ops.alert.domain.dto.AlertInfoBO;
import com.yonyou.cloud.ops.alert.ops.alert.domain.dto.AlertInfoSearchForm;
import com.yonyou.cloud.ops.alert.ops.alert.domain.dto.mail.MessageTemplate;
import com.yonyou.cloud.ops.alert.ops.alert.entity.AlertInfo;
import com.yonyou.cloud.ops.alert.ops.alert.entity.UserInfo;
import com.yonyou.cloud.ops.alert.ops.alert.mapper.AlertInfoMapper;

/**
 * 
 * @author daniell
 *
 */
@Service
public class AlertInfoBiz extends BaseService<AlertInfoMapper, AlertInfo> {

	@Autowired
	EmailMessage emailMessage;
	@Autowired
	UserInfoBiz userInfoBiz;
   
	public PageResultResponse<AlertInfoBO> selectByQuery(AlertInfoSearchForm searchForm){
    	Page<Object> result = PageHelper.startPage(searchForm.getPage(), searchForm.getLimit()); 
    	 List<AlertInfoBO> list= mapper.selectAlertBO(searchForm);
        return new PageResultResponse<AlertInfoBO>(result.getTotal(), list);
     }
    
	public void sendMail() {
		 
		List<AlertInfo> alertbo = mapper.selectAlertAndGroupByStatus(AlertStatus.Trigger.getValue());
		System.out.println("" + alertbo);
		List<MessageTemplate> msgTemp = new ArrayList<MessageTemplate>();
		AlarmMessageContext context = new AlarmMessageContext(emailMessage);
		for (AlertInfo alert : alertbo) {
			alert.getAlertDetail();
			alert.getGroupName();
			MessageTemplate msg = new MessageTemplate();
			msg.setSubject("业务报警邮件");
			msg.setContent(alert.getAlertDetail());
			List<UserInfo> userlist = userInfoBiz.selectUserByRuleGroup(alert.getGroupId());

			String[] toAddress = new String[userlist.size()];
			int i = 0;
			for (UserInfo user : userlist) {
				toAddress[i] = user.getEmail();
				i++;
			}
			msg.setToAddress(toAddress);
			msgTemp.add(msg);
		}

		context.AlarmMessage(msgTemp);
		
		for (AlertInfo alert : alertbo) {
			alert.setStatus(AlertStatus.Notice.getValue());
			alert.setGroupName(null);
			mapper.updateByPrimaryKey(alert);
		}
		 
	}
}
