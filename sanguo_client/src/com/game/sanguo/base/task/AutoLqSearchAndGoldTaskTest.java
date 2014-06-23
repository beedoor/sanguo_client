package com.game.sanguo.base.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.CitySearchInfo;
import com.game.sanguo.base.domain.EquipmentItem;
import com.game.sanguo.base.domain.GoldSearchInfo;
import com.game.sanguo.base.domain.PackItem;
import com.game.sanguo.base.domain.SearchItem;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;

/**
 * 元宝定时任务
 * 
 * @author beedoor
 * 
 */
public class AutoLqSearchAndGoldTaskTest extends GameTask {
	ItemConfig itemConfig;

	public AutoLqSearchAndGoldTaskTest(UserBean userBean, ItemConfig itemConfig,PipleLineTask pipleLineTask) {
		super(pipleLineTask);
		this.userBean = userBean;
		this.itemConfig = itemConfig;
	}

	public boolean doAction() {
		try {
			
				msgIdGetGold();
				msgIdGetGold();
				msgIdGetGold();
			return true;
		} catch (Throwable e) {
			logger.error("定时搜索资源任务异常", e);
		}
		return false;
	}

	private void msgIdGetGold() {
		PostMethod postMethod = new PostMethod(
				String.format(
						"%s/hero/dwr/call/plaincall/DwrGameWorld.setMsg.dwr;jsessionid=%s;mid=%s",
						userBean.getUrlPrx(), userBean.getSessionId(),
						userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		// 使用系统提供的默认的恢复策略
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean
				.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName",
				"DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "setMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:"
				+ userBean.getNumberId()));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod
				.addParameter(new NameValuePair("c0-e2", "string:msgTypeTower"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdTowerFloorReward"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:1,100"));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param1",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", ""
				+ userBean.getBatchId()));
		InputStream inputStream = doRequest(postMethod);

		try {
			System.out.println(postMethod.getResponseBodyAsString());
		} catch (Throwable e) {
			logger.error("转换异常", e);
		}
	}

	
}