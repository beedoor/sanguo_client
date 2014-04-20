package com.game.sanguo.base.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.domain.SearchResult;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.PipleLineTask;

public class GetTimeZoneTask extends GameTask {
	SearchResult searchResult = null;
	public GetTimeZoneTask(UserBean userBean,SearchResult searchResult,PipleLineTask pipleLineTask) {
		super(pipleLineTask);
		this.userBean = userBean;
		this.searchResult=searchResult;
	}

	

	public boolean doAction() {
		searchResult.clearAllResourceList();
		searchResult.clearGoldList();
		searchResult.clearMarketList();
		searchResult.clearSoliderList();
		searchResult.clearTreasureList();
		long xPosEnd = userBean.getConfigure().getScanResource()
				.getAllResourceConfig().getxPosEnd();
		long yPosEnd = userBean.getConfigure().getScanResource()
				.getAllResourceConfig().getyPosEnd();

		for (int i = 0; i < xPosEnd; i++) {
			for (int j = 0; j < yPosEnd; j++) {
				try {
					msgIdGetZoneInfo(i, j);
					sleep(userBean.getConfigure().getScanResource()
							.getWaitTime(), TimeUnit.MILLISECONDS);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	private void msgIdGetZoneInfo(int areaX, int areaY) {
		PostMethod postMethod = new PostMethod(
				String.format(
						"%s/hero/dwr/call/plaincall/Multiple.2.dwr;jsessionid=%s;mid=%s",
						userBean.getUrlPrx(), userBean.getSessionId(),
						userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Host", "g8.ol.ko.cn:8080");
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
		postMethod.addParameter(new NameValuePair("c0-e2",
				"string:msgTypeWorld"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdGetZoneInfo"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"
				+ (areaX + "," + areaY)));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param1",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", ""
				+ userBean.getBatchId()));
		InputStream inputStream =doRequest(postMethod);
		try {
			decoeLoginGameInfo(inputStream);
		} catch (Exception e) {
			logger.error("转换异常", e);
		}
	}

	/**
	 * 解析登录游戏内容数据
	 * 
	 * @param inputStream
	 */
	private void decoeLoginGameInfo(InputStream inputStream) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			Pattern itemsPattern = Pattern.compile("s[\\d]{1,}\\.");
			for (String s1 = null; (s1 = br.readLine()) != null;) {
				Matcher m = itemsPattern.matcher(s1);
				if (m.find()) {
					String contentStr = m.replaceAll("");
					CityInfo cityInfo = (CityInfo) initBeanInfo(CityInfo.class, contentStr, ';', '=');
					if(cityInfo.getTypeAsInt() == 2)
					{
						searchResult.addGoIdnfo(cityInfo);
					}else if(cityInfo.getTypeAsInt() == 3)
					{
						searchResult.addTreasureInfo(cityInfo);
					}else if(cityInfo.getTypeAsInt() == 4)
					{
						searchResult.addMarketInfo(cityInfo);
					}else if(cityInfo.getTypeAsInt() == 6)
					{
						searchResult.addSoliderInfo(cityInfo);
					} 
					if(cityInfo.getTypeAsInt() == 2 || cityInfo.getTypeAsInt() == 3 || cityInfo.getTypeAsInt() == 4 || cityInfo.getTypeAsInt() == 6)
					{
						searchResult.addAllResourceList(cityInfo);
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
