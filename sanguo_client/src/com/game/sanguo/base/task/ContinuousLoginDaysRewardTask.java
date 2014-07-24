package com.game.sanguo.base.task;

import java.io.InputStream;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.ContinuousLoginDaysRewardInfo;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.PipleLineTask;

public class ContinuousLoginDaysRewardTask extends GameTask {
	public ContinuousLoginDaysRewardTask(UserBean userBean) {
		this.userBean = userBean;
	}

	public boolean doAction() {
		try {
			// 计算是领取哪天的礼包
			Long loginDays = userBean.getLoginGameInfo().getContinuousLoginDays();
			
			if(loginDays == 7)
			{
				//更改为每到周五下午开始领取
				for(int rewardDay=1;rewardDay<=7;rewardDay++)
				{
					msgIdRecvContinuousLoginDaysRewardTask(rewardDay);
					logger.info(String.format("[%s]开始领取第[%s]天的奖励 ", userBean.getUserName(),rewardDay));
					userBean.getLoginGameInfo().setContinuousLoginDays(String.valueOf(loginDays + 1));
				}
			}
			return true;
		} catch (Throwable e) {
			logger.error("领取连续登录礼包异常", e);
		}
		return false;
	}

	private ContinuousLoginDaysRewardInfo msgIdRecvContinuousLoginDaysRewardTask(long rewardDay) {
		ContinuousLoginDaysRewardInfo awardInfo = null;
		PostMethod postMethod = new PostMethod(String.format("%s/hero/dwr/call/plaincall/DwrGameWorld.setMsg.dwr;jsessionid=%s;mid=%s", userBean.getUrlPrx(), userBean.getSessionId(),
				userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		// 使用系统提供的默认的恢复策略
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "setMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:" + userBean.getNumberId()));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdRecvContinuousLoginDaysReward"));
		postMethod.addParameter(new NameValuePair("c0-e4", "number:" + rewardDay));
		postMethod.addParameter(new NameValuePair("c0-param1", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		InputStream inputStream = doRequest(postMethod);

		try {
			awardInfo = initBeanInfo(ContinuousLoginDaysRewardInfo.class, inputStream, "dwr");
			if (null != awardInfo) {
				logger.info("连续登录奖励[" + rewardDay + "," + awardInfo.toString() + "]");
			}
		} catch (Throwable e) {
			logger.error("领取奖励异常", e);
		}

		return awardInfo;
	}
}
