package com.yonyou.cloud.ops.alert.ops.alert.biz;

import java.util.List;

import org.springframework.stereotype.Service;
import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.ops.alert.ops.alert.entity.RuleInfo;
import com.yonyou.cloud.ops.alert.ops.alert.mapper.RuleInfoMapper;

/**
 * 
 * @author daniell
 *
 */
@Service
public class RuleInfoBiz extends BaseService<RuleInfoMapper, RuleInfo> {
	public List<RuleInfo> selectRuleInfoByAppOrIp(String appName, String hostIp) {
		return mapper.selectRuleInfoByAppOrIp(appName, hostIp);
	}
}
