package com.game.sanguo.base.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.FightItem;
import com.game.sanguo.base.domain.PlayerHerosInfo;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.PipleLineTask;

/**
 * @author beedoor
 * 
 */
public class MsgTypeWorldPvpFightTask extends GameTask {
	FightItem	fightItem;

	public MsgTypeWorldPvpFightTask(UserBean userBean, FightItem fightItem) {
		this.fightItem = fightItem;
		this.userBean = userBean;
	}

	public boolean doAction() {
		try {
			String hero = null;
			for (PlayerHerosInfo p : userBean.getLoginGameInfo().getPlayerHerosInfoList()) {
				if (p.getCityId() < 10000) {
					hero = p.getId() + "";
				}
			}
			msgTypeWorldPveFight(hero);
			sleep(3, TimeUnit.SECONDS);
			msgIdFightSelectHero(hero);
			pipleLineTask.addFirst(new TaskUnit(new MsgTypeWorldPvpFightRetreatTask(userBean), new Date(System.currentTimeMillis() + (6 * 1000 * 60L))));
			logger.info(String.format("%s对%s本次保护成功", fightItem.getName(), fightItem.getTarget()));
			return true;
		} catch (Throwable e) {
			logger.error("MsgTypeWorldPvpFightTask", e);
		}
		return false;
	}

	private void msgTypeWorldPveFight(String hero) {
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
		postMethod.addParameter(new NameValuePair("c0-e1", "number:" + fightItem.getTarget()));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeWorldPveFight"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdFightSelectHero"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:" + hero + "," + hero));
		postMethod.addParameter(new NameValuePair("c0-param1", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);
	}

	private void msgIdFightSelectHero(String hero) {
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
		postMethod.addParameter(new NameValuePair("c0-e1", "number:" + fightItem.getTarget()));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeWorldPvpFight"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdFightSelectHero"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:1," + hero + "," + hero));
		postMethod.addParameter(new NameValuePair("c0-param1", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);
	}
}