package com.yonyou.cloud.ops.alert.ops.alert.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yonyou.cloud.ops.alert.ops.alert.biz.AlertInfoBiz;
import com.yonyou.cloud.ops.alert.ops.alert.biz.RuleInfoBiz;
import com.yonyou.cloud.ops.alert.ops.alert.domain.constants.AlertStatus;
import com.yonyou.cloud.ops.alert.ops.alert.domain.dto.MsgInfoVO;
import com.yonyou.cloud.ops.alert.ops.alert.entity.AlertInfo;
import com.yonyou.cloud.ops.alert.ops.alert.entity.RuleInfo;
import com.yonyou.cloud.ops.alert.ops.alert.utils.DateTimeUtils;
import com.yonyou.cloud.ops.alert.ops.alert.utils.FilterUtil;

/**
 * 
 * @author daniell
 *
 */
@Component
public class AlertInfoHandler {

private static final Logger loger = LoggerFactory.getLogger(AlertInfoHandler.class);

	@Value("${redis.listener.key}")
	private String listenerKey;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private RedisTemplate<String, Long> redisTemplateLong;

	@Autowired
	private RuleInfoBiz ruleInfoBiz;

	@Autowired
	private AlertInfoBiz alertInfoBiz;

	public void redisFile() throws IOException {

		String msgInfos = redisTemplate.opsForList().leftPop(listenerKey);
		if (StringUtils.isNotBlank(msgInfos)) {
			JSONObject jsonObject = JSONUtil.parseObj(msgInfos);
			MsgInfoVO vo = JSONUtil.toBean(jsonObject, MsgInfoVO.class);

			List<RuleInfo> vv = ruleInfoBiz.selectRuleInfoByAppOrIp(vo.getType(), vo.getHost());
			String msginfo = vo.getMsginfo();
			for (RuleInfo rule : vv) {
				System.out.println(msginfo);
				System.out.println(rule.getKeyword());
				if (FilterUtil.findTextIgnoreCase(msginfo, rule.getKeyword())) {
					System.out.println(msginfo);

					List<MsgInfoVO> list = new ArrayList<>();
					list.add(vo);

					Long keylength = redisTemplateLong.opsForList().leftPush(rule.getKeyword(),
							DateTimeUtils.parseDateTime(vo.getLogDate()));
					List<Long> keylist = redisTemplateLong.opsForList().range(rule.getKeyword(), 0, keylength);
					Long lastvalue = keylist.get(0);
					if (keylength >= rule.getCount()) {
						Long startValue = keylist.get(Integer.valueOf(rule.getCount() - 1));
						if (lastvalue - startValue > rule.getTime()) {
							redisTemplateLong.opsForList().trim(rule.getKeyword(), 0, keylength - 1);
							loger.info("触发报警规则，满足报警条件");
							AlertInfo alertInfo = new AlertInfo();
							alertInfo.setGroupId(rule.getGroupId());
							alertInfo.setAlertDetail(msginfo);
							alertInfo.setStatus(AlertStatus.Trigger.getValue());
							alertInfoBiz.insertSelective(alertInfo);
						}
					}
				}
			}
		}
	}
}
