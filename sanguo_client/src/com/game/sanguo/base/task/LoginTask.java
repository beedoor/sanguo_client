package com.game.sanguo.base.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.game.sanguo.base.domain.CityItem;
import com.game.sanguo.base.domain.ClientUpdateInfo;
import com.game.sanguo.base.domain.EquipmentItem;
import com.game.sanguo.base.domain.GameAreaInfo;
import com.game.sanguo.base.domain.HeroItem;
import com.game.sanguo.base.domain.LoginByEmailInfo;
import com.game.sanguo.base.domain.LoginGameInfo;
import com.game.sanguo.base.domain.PackItem;
import com.game.sanguo.base.domain.PlayerCitysInfo;
import com.game.sanguo.base.domain.PlayerHerosInfo;
import com.game.sanguo.base.domain.PlayerItemsInfo;
import com.game.sanguo.base.domain.StartChatInfo;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.util.PipleLineTask;

public class LoginTask extends GameTask {
	public LoginTask(UserBean userBean) {
		this.userBean = userBean;
	}

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(System.currentTimeMillis())));
	}

	public boolean doAction() {
		try {
			logger.info("开始登陆:"+userBean.getUserName()+"\t"+userBean.getAreaName());
			doLoginGame();
			doLoginArea();
			return true;
		} catch (Throwable e) {
			logger.error(
					this.userBean.getUserName() + ":" + userBean.getAreaName()
							+ "登录异常", e);
		}
		return false;
	}

	public void doLoginGame() throws Exception {
		this.userBean.reSetNumberIdAndBatchId();
		getBaseInfoNew();
		loginByEmail();
		sleep(5);
		getServerList();
		logger.info(this.userBean.getUserName() + ":" + userBean.getAreaName()
				+ " 登录游戏成功");
	}

	public void doLoginArea() {
		clientUpdate();
		loginGame();
		sleep(5);
		startChat();
		// msgIdServerTime();

		// msgIdGetCitiesGoldAvailable();
		logger.info(this.userBean.getUserName() + ":" + userBean.getAreaName()
				+ " 登录分区成功");
	}

	private void getBaseInfoNew() {
		PostMethod postMethod = new PostMethod(
				"http://118.26.192.76:8080/portal/dwr//call/plaincall/UserBean.getBaseInfoNew.dwr;jsessionid=");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "UserBean"));
		postMethod.addParameter(new NameValuePair("c0-methodName",
				"getBaseInfoNew"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:ANDK"));
		postMethod.addParameter(new NameValuePair("c0-param2", "string:10"));
		postMethod.addParameter(new NameValuePair("c0-param3",
				"string:com.noumena.android.olcn.of"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));

		doRequest(postMethod);
	}

	private void loginByEmail() {
		PostMethod postMethod = new PostMethod(
				"http://118.26.192.76:8080/portal/dwr//call/plaincall/UserBean.loginByEmail.dwr;jsessionid=");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "UserBean"));
		postMethod.addParameter(new NameValuePair("c0-methodName",
				"loginByEmail"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:"
				+ this.userBean.getUserName()));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:"
				+ this.userBean.getPassword()));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));

		InputStream inputStream = doRequest(postMethod);

		LoginByEmailInfo beanInfo = (LoginByEmailInfo) initBeanInfo(
				LoginByEmailInfo.class, inputStream, "dwr");
		this.userBean.setCheckId(beanInfo.getCheckId());
		// this.userBean.setAreaId(beanInfo.getServerId());
		this.userBean.setUserID(beanInfo.getUserId());

	}

	private void loginGame() {
		PostMethod postMethod = new PostMethod(String.format(
				"%s/hero/dwr/call/plaincall/DwrGame.loginGame.dwr;jsessionid=",
				new Object[] { this.userBean.getUrlPrx() }));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrGame"));
		postMethod
				.addParameter(new NameValuePair("c0-methodName", "loginGame"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:"
				+ this.userBean.getUserID()));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:ANDK"));
		postMethod.addParameter(new NameValuePair("c0-param2", "number:"
				+ this.userBean.getAreaId()));
		postMethod.addParameter(new NameValuePair("c0-param3", "string:"
				+ this.userBean.getCheckId()));
		postMethod.addParameter(new NameValuePair("c0-param4",
				"string:602102200"));
		postMethod.addParameter(new NameValuePair("c0-param5", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param6",
				"string:official"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));

		InputStream inputStream = doRequest(postMethod);

		LoginGameInfo beanInfo = decodeLoginGameInfo(inputStream);
		logger.info(userBean.getUserName() + "\t" + beanInfo);
		this.userBean.setLoginGameInfo(beanInfo);
		this.userBean.setSessionId(beanInfo.getSessionId());

	}

	private LoginGameInfo decodeLoginGameInfo(InputStream inputStream) {
		LoginGameInfo loginGameInfo = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			List<PlayerCitysInfo> playerCitysInfoList = new ArrayList();
			List<PlayerHerosInfo> playerHerosInfoList = new ArrayList();
			List<PlayerItemsInfo> playerItemsInfoList = new ArrayList();
			Pattern itemsPattern = Pattern
					.compile("(s[\\d]{1,})\\[[\\d]{1,}\\]=");
			Pattern itemsContentPattern = Pattern.compile("(s[\\d]{1,})[.]");
			Map<String, List<String>> itemChildMap = new HashMap();
			Map<String, String> itemContentMap = new HashMap();
			for (String s1 = null; (s1 = br.readLine()) != null;) {
				if (s1.startsWith("dwr")) {
					loginGameInfo = (LoginGameInfo) initBeanInfo(
							LoginGameInfo.class, s1);
				} else {
					Matcher m = itemsContentPattern.matcher(s1);
					if (m.find()) {
						String itemName = m.group(1);
						String contentStr = m.replaceAll("");
						itemContentMap.put(itemName, contentStr);
					}
					m = itemsPattern.matcher(s1);
					if (m.find()) {
						String keyName = m.group(1);
						List<String> sList = new ArrayList();
						itemChildMap.put(keyName, sList);
						String tempStr = m.replaceAll("");
						String[] strArray = tempStr.split(";");
						String[] as;
						int j = (as = strArray).length;
						for (int i = 0; i < j; i++) {
							String s = as[i];
							sList.add(s);
						}
					}
				}
			}
			if (itemChildMap.containsKey(loginGameInfo.getPlayerCitys())) {
				Iterator<String> iterator = ((List) itemChildMap
						.get(loginGameInfo.getPlayerCitys())).iterator();
				String content;
				for (; iterator.hasNext(); playerCitysInfoList
						.add((PlayerCitysInfo) initBeanInfo(
								PlayerCitysInfo.class, content, ';', '='))) {
					String itemName = (String) iterator.next();
					content = (String) itemContentMap.get(itemName);
				}
			}
			if (itemChildMap.containsKey(loginGameInfo.getPlayerHeros())) {
				Iterator<String> iterator1 = ((List) itemChildMap
						.get(loginGameInfo.getPlayerHeros())).iterator();
				String content;
				for (; iterator1.hasNext(); playerHerosInfoList
						.add((PlayerHerosInfo) initBeanInfo(
								PlayerHerosInfo.class, content, ';', '='))) {
					String itemName = (String) iterator1.next();
					content = (String) itemContentMap.get(itemName);
				}
			}
			if (itemChildMap.containsKey(loginGameInfo.getPlayerItems())) {
				Iterator<String> iterator2 = ((List) itemChildMap
						.get(loginGameInfo.getPlayerItems())).iterator();
				String content;
				for (; iterator2.hasNext(); playerItemsInfoList
						.add((PlayerItemsInfo) initBeanInfo(
								PlayerItemsInfo.class, content, ';', '='))) {
					String itemName = (String) iterator2.next();
					content = (String) itemContentMap.get(itemName);
				}
			}
			if (loginGameInfo != null) {
				loginGameInfo.setPlayerCitysInfoList(playerCitysInfoList);
				loginGameInfo.setPlayerHerosInfoList(playerHerosInfoList);
				loginGameInfo.setPlayerItemsInfoList(playerItemsInfoList);
			}
			addOtherInfo(playerCitysInfoList, playerHerosInfoList,
					playerItemsInfoList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loginGameInfo;
	}

	private void addOtherInfo(List<PlayerCitysInfo> playerCitysInfoList,
			List<PlayerHerosInfo> playerHerosInfoList,
			List<PlayerItemsInfo> playerItemsInfoList) {
		for (PlayerCitysInfo playerCitysInfo : playerCitysInfoList) {
			CityItem cityItem = this.userBean.getItemConfig().decodeCity(
					playerCitysInfo.getSourceId());
			if (cityItem != null) {
				playerCitysInfo.setCityName(cityItem.getCity_name());
			}
		}
		for (PlayerHerosInfo playerHerosInfo : playerHerosInfoList) {
			this.userBean.putHeroIdToSrcId(playerHerosInfo.getId(),
					playerHerosInfo.getSourceId());
			CityItem cityItem = this.userBean.getItemConfig().decodeCity(
					playerHerosInfo.getCityId());
			HeroItem heroItem = this.userBean.getItemConfig().decodeHero(
					playerHerosInfo.getSourceId());
			if (cityItem != null) {
				playerHerosInfo.setCityName(cityItem.getCity_name());
			}
			if (heroItem != null) {
				playerHerosInfo.setHeroName(heroItem.getName());
			}
		}
		for (PlayerItemsInfo playerItemsInfo : playerItemsInfoList) {
			this.userBean.putItemIdToSrcId(playerItemsInfo.getId(),
					playerItemsInfo.getSourceId());
			EquipmentItem equipmentItem = this.userBean.getItemConfig()
					.decodeEquipment(playerItemsInfo.getSourceId());
			HeroItem heroItem = this.userBean.getItemConfig().decodeHero(
					this.userBean.decodeHeroSrcIdByUseId(playerItemsInfo
							.getHeroUseId()));
			if (equipmentItem != null) {
				playerItemsInfo.setItemName(equipmentItem.getName());
			} else {
				PackItem packItem = this.userBean.getItemConfig().decodePack(
						playerItemsInfo.getSourceId());
				if (packItem != null) {
					playerItemsInfo.setItemName(packItem.getProps_name());
				}
			}
			if (heroItem != null) {
				playerItemsInfo.setHeroName(heroItem.getName());
			}
		}
	}

	private void getServerList()throws Exception {
		PostMethod postMethod = new PostMethod(
				"http://118.26.192.76:8080/VersionServerIOS/dwr//call/plaincall/DwrEntry.getServerList.dwr;jsessionid");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrEntry"));
		postMethod.addParameter(new NameValuePair("c0-methodName",
				"getServerList"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:8"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));
		InputStream inputStream = doRequest(postMethod);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));
		String s1 = null;
		while ((s1 = br.readLine()) != null) {
			if (s1.startsWith("s")) {
				int prex = s1.indexOf(".");
				String areaIdStr = s1.substring(0, prex);

				s1 = s1.replaceAll(areaIdStr + "[.]", "");
				s1 = s1.replaceAll("[:]", "#");
				s1 = s1.replaceAll("[=]", ":");
				s1 = s1.replaceAll("[;]", ",");
				GameAreaInfo gameArea = (GameAreaInfo) initBeanInfo(
						GameAreaInfo.class, "{" + s1 + "}");
				String url = gameArea.getUrl().replaceAll("#", ":");
				gameArea.setUrl(url);
				this.userBean.putGameAreaInfo(gameArea);
			}
		}

	}

	private void clientUpdate() {
		PostMethod postMethod = new PostMethod(
				"http://118.26.192.76:8080/VersionServer/dwr//call/plaincall/DwrEntry.clientUpdate.dwr;jsessionid");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrEntry"));
		postMethod.addParameter(new NameValuePair("c0-methodName",
				"clientUpdate"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:SC"));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:2"));
		postMethod.addParameter(new NameValuePair("c0-param2", "string:"
				+ getClientVersion()));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));
		InputStream inputStream = doRequest(postMethod);

		ClientUpdateInfo clientInfo = (ClientUpdateInfo) initBeanInfo(
				ClientUpdateInfo.class, inputStream, "dwr");
		this.userBean.setClientInfo(clientInfo);

	}

	private String getClientVersion() {
		if (this.userBean.getClientInfo() == null) {
			return "475";
		}
		return this.userBean.getClientInfo().getLatestVersion() + "";
	}

	public void startChat() {
		PostMethod postMethod = new PostMethod(
				String.format(
						"%s/ChatServer/dwr/call/plaincall/DwrChat.startChat.dwr;jsessionid",
						new Object[] { this.userBean.getUrlPrx() }));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrChat"));
		postMethod
				.addParameter(new NameValuePair("c0-methodName", "startChat"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:"
				+ this.userBean.getAreaId()));
		postMethod.addParameter(new NameValuePair("c0-param1", "number:1"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));
		InputStream inputStream = doRequest(postMethod);

		StartChatInfo beanInfo = (StartChatInfo) initBeanInfo(
				StartChatInfo.class, inputStream, "dwr");
		this.userBean.setChatSessionId(beanInfo.getSessionId());

	}

	private void msgIdServerTime() {
		PostMethod postMethod = new PostMethod(
				String.format(
						"%s/hero/dwr/call/plaincall/DwrGameWorld.getMsg.dwr;jsessionid=%s;mid=%s",
						new Object[] { this.userBean.getUrlPrx(),
								this.userBean.getSessionId(),
								this.userBean.getSessionId() }));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId",
				this.userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName",
				"DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2",
				"string:msgTypeGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdServerTime"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param0",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));
		doRequest(postMethod);
	}

	private void msgIdGetCitiesGoldAvailable() {
		PostMethod postMethod = new PostMethod(
				String.format(
						"%s/hero/dwr/call/plaincall/DwrGameWorld.getMsg.dwr;jsessionid=%s;mid=%s",
						new Object[] { this.userBean.getUrlPrx(),
								this.userBean.getSessionId(),
								this.userBean.getSessionId() }));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent",
				"Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId",
				this.userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId",
				"51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName",
				"DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod
				.addParameter(new NameValuePair("c0-e2", "string:msgTypeCity"));
		postMethod.addParameter(new NameValuePair("c0-e3",
				"string:msgIdGetCitiesGoldAvailable"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod
				.addParameter(new NameValuePair(
						"c0-param0",
						"Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", this.userBean
				.getBatchId() + ""));
		doRequest(postMethod);
	}
}
