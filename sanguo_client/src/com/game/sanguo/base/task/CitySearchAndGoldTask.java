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
public class CitySearchAndGoldTask extends GameTask {
	ItemConfig itemConfig;

	public CitySearchAndGoldTask(UserBean userBean, ItemConfig itemConfig,PipleLineTask pipleLineTask) {
		super(pipleLineTask);
		this.userBean = userBean;
		this.itemConfig = itemConfig;
	}

	public boolean doAction() {
		try {
			logger.info(userBean.getUserName()+" 收菜开始");
//			if (userBean.getConfigure().getSearchResource() == 1) {
				msgIdGetGold();
				msgIdCitySearch();
//			} else {
//				logger.info("禁止自动收资源");
//			}
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
				.addParameter(new NameValuePair("c0-e2", "string:msgTypeCity"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdGetGold"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param1",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", ""
				+ userBean.getBatchId()));
		InputStream inputStream = doRequest(postMethod);

		try {
			GoldSearchInfo goldInfo = initBeanInfo(GoldSearchInfo.class,
					inputStream, "dwr");
			logger.info("当前金币[{}],获得金币[{}]", new Object[] { goldInfo.getGold(),
					goldInfo.getAddGold() });
		} catch (Throwable e) {
			logger.error("转换异常", e);
		}
	}

	private void msgIdCitySearch() {
		PostMethod postMethod;
		BufferedReader br;
		postMethod = new PostMethod(
				String.format(
						"%s/hero/dwr/call/plaincall/DwrGameWorld.setMsg.dwr;jsessionid=%s;mid=%s",
						new Object[] { userBean.getUrlPrx(),
								userBean.getSessionId(),
								userBean.getSessionId() }));
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
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean
				.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName",
				"DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "setMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0",
				(new StringBuilder("number:")).append(userBean.getNumberId())
						.toString()));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod
				.addParameter(new NameValuePair("c0-e2", "string:msgTypeCity"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdCitySearch"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param1",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod
				.addParameter(new NameValuePair("batchId",
						(new StringBuilder()).append(userBean.getBatchId())
								.toString()));
		InputStream inputStream = doRequest(postMethod);
		br = null;
		try {
			Pattern itemsPattern = Pattern
					.compile("(s[\\d]{1,})\\[[\\d]{1,}\\]=");
			Pattern itemsContentPattern = Pattern.compile("(s[\\d]{1,})[.]");
			Map<String, List<String>> itemStrListMap = new HashMap<String, List<String>>();
			Map<String, String> itemMap = new HashMap<String, String>();
			br = new BufferedReader(new InputStreamReader(inputStream));
			String s1 = null;
			String content = null;
			while ((s1 = br.readLine()) != null) {
				if (s1.startsWith("dwr"))
					content = s1;
				Matcher m = itemsContentPattern.matcher(s1);
				if (m.find()) {
					String iteName = m.group(1);
					String tempStr = m.replaceAll("");
					itemMap.put(iteName, tempStr);
				}
				m = itemsPattern.matcher(s1);
				if (m.find()) {
					String keyName = m.group(1);
					List<String> sList = new ArrayList<String>();
					itemStrListMap.put(keyName, sList);
					String tempStr = m.replaceAll("");
					String strArray[] = tempStr.split(";");
					String as[];
					int j = (as = strArray).length;
					for (int i = 0; i < j; i++) {
						String s = as[i];
						sList.add(s);
					}

				}
			}
			CitySearchInfo cityInfo = (CitySearchInfo) initBeanInfo(
					CitySearchInfo.class, content);
			List<SearchItem> searchItem = decode(itemMap,
					(List<String>) itemStrListMap.get(cityInfo.getItems()),
					SearchItem.class);
			logger.info(
					"[{}]当前金币[{}],获得金币[{}],物品[{}]",
					new Object[] {userBean.getUserName(), cityInfo.getGold(),
							cityInfo.getSearchGold(),
							decodeItem(searchItem, itemConfig) });
		} catch (Throwable e) {
			logger.error("转换异常", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static <T> List<T> decode(Map<String, String> itemMap,
			List<String> itemStrList, Class<T> classtype) {
		List<T> list = new ArrayList<T>();
		if (itemStrList != null) {
			for (Iterator<String> iterator = itemStrList.iterator(); iterator
					.hasNext();) {
				String s1 = (String) iterator.next();
				String item = (String) itemMap.get(s1);
				T t = initBeanInfo(classtype, item, ';', '=');
				list.add(t);
			}
		}
		return list;
	}

	private static String decodeItem(List<SearchItem> searchItemList,
			ItemConfig itemConfig) {
		StringBuffer strb = new StringBuffer();
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		for (Iterator<SearchItem> iterator = searchItemList.iterator(); iterator
				.hasNext();) {
			SearchItem item = (SearchItem) iterator.next();
			if (item.getType().longValue() == 1L) {
				EquipmentItem eq = itemConfig.decodeEquipment(item
						.getSourceId());
				if (eq != null)
					if (!aMap.containsKey(eq.getName()))
						aMap.put(eq.getName(), Integer.valueOf(1));
					else
						aMap.put(eq.getName(), Integer.valueOf(((Integer) aMap
								.get(eq.getName())).intValue() + 1));
			} else {
				PackItem eq = itemConfig.decodePack(item.getSourceId());
				if (eq != null)
					if (!aMap.containsKey(eq.getProps_name()))
						aMap.put(eq.getProps_name(), Integer.valueOf(1));
					else
						aMap.put(eq.getProps_name(),
								Integer.valueOf(((Integer) aMap.get(eq
										.getProps_name())).intValue() + 1));
			}
		}

		for (Iterator<String> ite = aMap.keySet().iterator(); ite.hasNext();) {
			String s1 = (String) ite.next();
			int v = ((Integer) aMap.get(s1)).intValue();
			strb.append(s1).append("*").append(v).append(";");
		}

		return strb.toString();
	}

	public static void main(String args[]) {
		// try {
		// ItemConfig itemConfig = new ItemConfig();
		// itemConfig.loadUserConfig();
		// Pattern itemsPattern = Pattern
		// .compile("(s[\\d]{1,})\\[[\\d]{1,}\\]=");
		// Pattern itemsContentPattern = Pattern.compile("(s[\\d]{1,})[.]");
		// Map itemStrListMap = new HashMap();
		// Map itemMap = new HashMap();
		// BufferedReader br = new BufferedReader(new InputStreamReader(
		// new FileInputStream(new File("aaaa"))));
		// String s1 = null;
		// String content = null;
		// String hero = null;
		// String item = null;
		// while ((s1 = br.readLine()) != null) {
		// if (s1.startsWith("dwr"))
		// content = s1;
		// Matcher m = itemsContentPattern.matcher(s1);
		// if (m.find()) {
		// String iteName = m.group(1);
		// String tempStr = m.replaceAll("");
		// itemMap.put(iteName, tempStr);
		// }
		// m = itemsPattern.matcher(s1);
		// if (m.find()) {
		// String keyName = m.group(1);
		// List sList = new ArrayList();
		// itemStrListMap.put(keyName, sList);
		// String tempStr = m.replaceAll("");
		// String strArray[] = tempStr.split(";");
		// String args1[];
		// int j = (args1 = strArray).length;
		// for (int i = 0; i < j; i++) {
		// String s = args1[i];
		// sList.add(s);
		// }
		//
		// }
		// }
		// CitySearchInfo cityInfo = (CitySearchInfo) initBeanInfo(com / game
		// / sanguo / domain / CitySearchInfo, content);
		// System.out.println(itemStrListMap);
		// List searchItem = decode(itemMap,
		// (List) itemStrListMap.get(cityInfo.getItems()), com / game
		// / sanguo / domain / SearchItem);
		// logger.info(
		// "\u5F53\u524D\u91D1\u5E01[{}],\u83B7\u5F97\u91D1\u5E01[{}],\u7269\u54C1[{}]",
		// new Object[] { cityInfo.getGold(),
		// cityInfo.getSearchGold(),
		// decodeItem(searchItem, itemConfig) });
		// } catch (Throwable e) {
		// logger.error("\u8F6C\u6362\u5F02\u5E38", e);
		// }
	}
}
