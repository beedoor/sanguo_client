package com.game.sanguo.base.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.domain.SearchResult;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;

public class GetTimeZoneTask extends GameTask {
	SearchResult searchResult = null;
	ResourceConfig resourceConfig=null;
	public GetTimeZoneTask(UserBean userBean, SearchResult searchResult,ResourceConfig resourceConfig,
			PipleLineTask pipleLineTask) {
		super(pipleLineTask);
		this.resourceConfig = resourceConfig;
		this.userBean = userBean;
		this.searchResult = searchResult;
	}

	public boolean doAction() {
		this.searchResult.clearAllResourceList();

		this.searchResult.clearGoldList();
		this.searchResult.clearMarketList();
		this.searchResult.clearSoliderList();
		this.searchResult.clearTreasureList();
		long xPosEnd = this.userBean.getConfigure().getScanResource()
				.getAllResourceConfig().getxPosEnd().longValue();
		long yPosEnd = this.userBean.getConfigure().getScanResource()
				.getAllResourceConfig().getyPosEnd().longValue();
		for (int i = 0; i < xPosEnd; i++) {
			for (int j = 0; j < yPosEnd; j++) {
				try {
					msgIdGetZoneInfo(i, j);
					sleep(this.userBean.getConfigure().getScanResource()
							.getWaitTime().longValue(), TimeUnit.MILLISECONDS);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
		doExport();
		resourceConfig.loadResourceConfig();
		return true;
	}

	protected void doExport() {
		FileWriter fos = null;
		File f = null;
		try {
			String endline = "\r\n";
			f = new File("config/resource.xmlbak");
			if (f.exists()) {
				f.delete();
			}
			fos = new FileWriter(f);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			fos.write(endline);
			fos.write("<resources>");
			writeOne(fos, this.searchResult.getTreasureList(), "treasure",
					endline);
			writeOne(fos, this.searchResult.getMarketList(), "market", endline);
			writeOne(fos, this.searchResult.getGoldList(), "gold", endline);
			writeOne(fos, this.searchResult.getSoliderList(), "solider",
					endline);
			fos.write("</resources>");
		} catch (Exception e) {
			logger.error("写入文件异常", e);
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File ftarget = new File("config/resource.xml");
		if (ftarget.exists()) {
			ftarget.delete();
		}
		f.renameTo(ftarget);
	}

	private void writeOne(FileWriter fos, List<CityInfo> targetList,
			String type, String endline) throws Exception {
		if ((targetList == null) || (fos == null) || (type == null)) {
			return;
		}
		for (Iterator<CityInfo> iterator = targetList.iterator(); iterator
				.hasNext(); fos.write(endline)) {
			CityInfo city = (CityInfo) iterator.next();
			fos.write("<resource>");
			fos.write(endline);
			fos.write(String.format("<type>%s</type>", new Object[] { type }));
			fos.write(endline);
			fos.write(String.format("<areaId>%s</areaId>",
					new Object[] { city.getId() }));
			fos.write(endline);
			fos.write("</resource>");
		}
	}

	private void msgIdGetZoneInfo(int areaX, int areaY) {
		PostMethod postMethod = new PostMethod(
				String.format(
						"%s/hero/dwr/call/plaincall/Multiple.2.dwr;jsessionid=%s;mid=%s",
						new Object[] { this.userBean.getUrlPrx(),
								this.userBean.getSessionId(),
								this.userBean.getSessionId() }));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Host", "g8.ol.ko.cn:8080");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId",
				this.userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName",
				"DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "setMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:"
				+ this.userBean.getNumberId()));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2",
				"string:msgTypeWorld"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdGetZoneInfo"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:" + areaX
				+ "," + areaY));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param1",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));
		InputStream inputStream = doRequest(postMethod);
		try {
			decoeLoginGameInfo(inputStream);
		} catch (Exception e) {
			logger.error("转换异常", e);
		}
	}

	private void decoeLoginGameInfo(InputStream inputStream) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			Pattern itemsPattern = Pattern.compile("s[\\d]{1,}\\.");
			for (String s1 = null; (s1 = br.readLine()) != null;) {
				Matcher m = itemsPattern.matcher(s1);
				if (m.find()) {
					String contentStr = m.replaceAll("");
					CityInfo cityInfo = (CityInfo) initBeanInfo(CityInfo.class,
							contentStr, ';', '=');
					if (cityInfo.getTypeAsInt().longValue() == 2L) {
						this.searchResult.addGoIdnfo(cityInfo);
					} else if (cityInfo.getTypeAsInt().longValue() == 3L) {
						this.searchResult.addTreasureInfo(cityInfo);
					} else if (cityInfo.getTypeAsInt().longValue() == 4L) {
						this.searchResult.addMarketInfo(cityInfo);
					} else if (cityInfo.getTypeAsInt().longValue() == 6L) {
						this.searchResult.addSoliderInfo(cityInfo);
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
