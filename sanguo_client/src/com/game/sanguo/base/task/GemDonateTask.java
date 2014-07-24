package com.game.sanguo.base.task;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.EquipmentItem;
import com.game.sanguo.base.domain.EquipmentItemSellInfo;
import com.game.sanguo.base.domain.LoginGameInfo;
import com.game.sanguo.base.domain.PlayerItemsInfo;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;

public class GemDonateTask extends GameTask {
	ItemConfig itemConfig;
	public GemDonateTask(UserBean userBean, ItemConfig itemConfig) {
		this.userBean = userBean;
		this.itemConfig = itemConfig;
	}

	@Override
	protected boolean doAction() {
		try {
			if(userBean.getLoginGameInfo().getVipLv() >=3)
			{
				logger.info(userBean.getUserName()+"\t捐献:"+1000);
				doDonate(1000);
			}else if(userBean.getLoginGameInfo().getVipLv() >0)
			{
				logger.info(userBean.getUserName()+"\t捐献:"+100);
				doDonate(100);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private void doDonate(long donate) {
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
		postMethod.addParameter(new NameValuePair("c0-param0", "" + userBean.getNumberId()));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeLeague"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdLeagueDonate"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:2,"+donate));
		postMethod.addParameter(new NameValuePair("c0-param1", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		InputStream inputStream=doRequest(postMethod);
	}
}
