package com.game.sanguo.base.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class LoginTask extends GameTask {

	public LoginTask(UserBean userBean) {
		super();
		this.userBean = userBean;
	}

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}

	public void doAction() {
		try {
			userBean.reSetNumberIdAndBatchId();

			getBaseInfoNew();
			loginByEmail();
			sleep(5);// 游戏加载
			getServerList();
			clientUpdate();
			loginGame();
			sleep(5);// 游戏加载
			startChat();
			msgIdServerTime();
			// 获取资源信息
			msgIdGetCitiesGoldAvailable();
		} catch (Throwable e) {
			logger.error("登录异常", e);
		}
	}

	private void getBaseInfoNew() {
		PostMethod postMethod = new PostMethod("http://118.26.192.76:8080/portal/dwr//call/plaincall/UserBean.getBaseInfoNew.dwr;jsessionid=");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "UserBean"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getBaseInfoNew"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:ANDK"));
		postMethod.addParameter(new NameValuePair("c0-param2", "string:10"));
		postMethod.addParameter(new NameValuePair("c0-param3", "string:com.noumena.android.olcn.of"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));

		doRequest(postMethod);
	}

	private void loginByEmail() {
		PostMethod postMethod = new PostMethod("http://118.26.192.76:8080/portal/dwr//call/plaincall/UserBean.loginByEmail.dwr;jsessionid=");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "UserBean"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "loginByEmail"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:" + userBean.getUserName()));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:" + userBean.getPassword()));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));

		doRequest(postMethod);

		try {
			LoginByEmailInfo beanInfo = initBeanInfo(LoginByEmailInfo.class, postMethod.getResponseBodyAsStream(), "dwr");
			userBean.setCheckId(beanInfo.getCheckId());
			userBean.setAreaId(beanInfo.getServerId());
			userBean.setUserID(beanInfo.getUserId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loginGame() {
		PostMethod postMethod = new PostMethod(String.format("%s/hero/dwr/call/plaincall/DwrGame.loginGame.dwr;jsessionid=", userBean.getUrlPrx()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");

		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrGame"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "loginGame"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:" + userBean.getUserID()));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:ANDK"));
		postMethod.addParameter(new NameValuePair("c0-param2", "number:" + userBean.getAreaId()));
		postMethod.addParameter(new NameValuePair("c0-param3", "string:" + userBean.getCheckId()));
		postMethod.addParameter(new NameValuePair("c0-param4", "string:602102200"));
		postMethod.addParameter(new NameValuePair("c0-param5", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param6", "string:official"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));

		doRequest(postMethod);

		try {
			// LoginGameInfo beanInfo = initBeanInfo(LoginGameInfo.class,
			// postMethod.getResponseBodyAsStream(), "dwr");
			LoginGameInfo beanInfo = decoeLoginGameInfo(postMethod.getResponseBodyAsStream());
			userBean.setLoginGameInfo(beanInfo);
			userBean.setSessionId(beanInfo.getSessionId());
		} catch (Throwable e) {
			logger.error("登录服务器异常", e);
		}
	}

	/**
	 * 解析登录游戏内容数据
	 * 
	 * @param inputStream
	 */
	private LoginGameInfo decoeLoginGameInfo(InputStream inputStream) {
		LoginGameInfo loginGameInfo = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String s1 = null;
			List<String> s5ArrayList = new ArrayList<String>(), s6ArrayList = new ArrayList<String>(), s7ArrayList = new ArrayList<String>();
			List<PlayerCitysInfo> playerCitysInfoList = new ArrayList<PlayerCitysInfo>();
			List<PlayerHerosInfo> playerHerosInfoList = new ArrayList<PlayerHerosInfo>();
			List<PlayerItemsInfo> playerItemsInfoList = new ArrayList<PlayerItemsInfo>();
			while ((s1 = br.readLine()) != null) {
				if (s1.startsWith("dwr")) {
					loginGameInfo = initBeanInfo(LoginGameInfo.class, s1);
					break;
				}
				if (s1.startsWith("s5[0]")) {
					decodeArrayPrex(s5ArrayList, s1);
				} else if (s1.startsWith("s6[0]")) {
					decodeArrayPrex(s6ArrayList, s1);
				} else if (s1.startsWith("s7[0]")) {
					decodeArrayPrex(s7ArrayList, s1);
				} else {
					String[] sContentPrex = s1.split("[.]");
					if (sContentPrex.length <= 1) {
						continue;
					}
					if (s5ArrayList.contains(sContentPrex[0])) {
						s1 = s1.replaceAll(String.format("%s[.]", sContentPrex[0]), "");
						playerCitysInfoList.add(initBeanInfo(PlayerCitysInfo.class, s1, ';', '='));
					}
					if (s6ArrayList.contains(sContentPrex[0])) {
						s1 = s1.replaceAll(String.format("%s[.]", sContentPrex[0]), "");
						playerHerosInfoList.add(initBeanInfo(PlayerHerosInfo.class, s1, ';', '='));
					}
					if (s7ArrayList.contains(sContentPrex[0])) {
						s1 = s1.replaceAll(String.format("%s[.]", sContentPrex[0]), "");
						playerItemsInfoList.add(initBeanInfo(PlayerItemsInfo.class, s1, ';', '='));
					}
				}
			}
			if (loginGameInfo != null) {
				loginGameInfo.setPlayerCitysInfoList(playerCitysInfoList);
				loginGameInfo.setPlayerHerosInfoList(playerHerosInfoList);
				loginGameInfo.setPlayerItemsInfoList(playerItemsInfoList);
			}
			addOtherInfo(playerCitysInfoList, playerHerosInfoList, playerItemsInfoList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loginGameInfo;
	}

	private void addOtherInfo(List<PlayerCitysInfo> playerCitysInfoList, List<PlayerHerosInfo> playerHerosInfoList, List<PlayerItemsInfo> playerItemsInfoList) {
		for (PlayerCitysInfo playerCitysInfo : playerCitysInfoList) {
			CityItem cityItem = userBean.getItemConfig().decodeCity(playerCitysInfo.getSourceId());
			if (null != cityItem) {
				playerCitysInfo.setCityName(cityItem.getCity_name());
			}
		}
		for (PlayerHerosInfo playerHerosInfo : playerHerosInfoList) {
			userBean.putHeroIdToSrcId(playerHerosInfo.getId(), playerHerosInfo.getSourceId());
			CityItem cityItem = userBean.getItemConfig().decodeCity(playerHerosInfo.getCityId());
			HeroItem heroItem = userBean.getItemConfig().decodeHero(playerHerosInfo.getSourceId());
			if (cityItem != null) {
				playerHerosInfo.setCityName(cityItem.getCity_name());
			}
			if (heroItem != null) {
				playerHerosInfo.setHeroName(heroItem.getName());
			}
		}
		for (PlayerItemsInfo playerItemsInfo : playerItemsInfoList) {
			userBean.putItemIdToSrcId(playerItemsInfo.getId(), playerItemsInfo.getSourceId());
			EquipmentItem equipmentItem = userBean.getItemConfig().decodeEquipment(playerItemsInfo.getSourceId());
			HeroItem heroItem = userBean.getItemConfig().decodeHero(userBean.decodeHeroSrcIdByUseId(playerItemsInfo.getHeroUseId()));
			if (equipmentItem != null) {
				playerItemsInfo.setItemName(equipmentItem.getName());
			}else
			{
				PackItem packItem = userBean.getItemConfig().decodePack(playerItemsInfo.getSourceId());
				playerItemsInfo.setItemName(packItem.getProps_name());
			}
			if (heroItem != null) {
				playerItemsInfo.setHeroName(heroItem.getName());
			}
		}
	}

	private void decodeArrayPrex(List<String> sArrayList, String s) {
		String[] sArray = s.split(";");
		for (String sPrex : sArray) {
			String[] sPrexArray = sPrex.split("=");
			if (sPrexArray.length != 2) {
				continue;
			}
			sArrayList.add(sPrexArray[1]);
		}
	}

	public void getServerList() {
		// 创建GET方法的实例
		PostMethod postMethod = new PostMethod("http://118.26.192.76:8080/VersionServerIOS/dwr//call/plaincall/DwrEntry.getServerList.dwr;jsessionid");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrEntry"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getServerList"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:8"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
			String s1 = null;
			while ((s1 = br.readLine()) != null) {
				if (s1.startsWith("s")) {
					// 游戏服务器了
					int prex = s1.indexOf(".");
					String areaIdStr = s1.substring(0, prex);
					// 进行替换咯
					s1 = s1.replaceAll(areaIdStr + "[.]", "");
					s1 = s1.replaceAll("[:]", "#");
					s1 = s1.replaceAll("[=]", ":");
					s1 = s1.replaceAll("[;]", ",");
					GameAreaInfo gameArea = initBeanInfo(GameAreaInfo.class, "{" + s1 + "}");
					String url = gameArea.getUrl().replaceAll("#", ":");
					gameArea.setUrl(url);
					logger.info(gameArea.toString());
					userBean.putGameAreaInfo(gameArea);
				}
			}
		} catch (Throwable e) {
			logger.error("获取服务列表异常", e);
		}
	}

	public void clientUpdate() {
		// 创建GET方法的实例
		PostMethod postMethod = new PostMethod("http://118.26.192.76:8080/VersionServer/dwr//call/plaincall/DwrEntry.clientUpdate.dwr;jsessionid");
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrEntry"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "clientUpdate"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "string:SC"));
		postMethod.addParameter(new NameValuePair("c0-param1", "string:2"));
		postMethod.addParameter(new NameValuePair("c0-param2", "string:" + getClientVersion()));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);

		try {
			ClientUpdateInfo clientInfo = initBeanInfo(ClientUpdateInfo.class, postMethod.getResponseBodyAsStream(), "dwr");
			logger.info("客户端版本信息:" + clientInfo.toString());
			userBean.setClientInfo(clientInfo);
		} catch (Throwable e) {
			logger.error("客户端更新异常", e);
		}
	}

	private String getClientVersion() {
		if (userBean.getClientInfo() == null) {
			return "475";
		}
		return userBean.getClientInfo().getLatestVersion() + "";
	}

	public void startChat() {
		PostMethod postMethod = new PostMethod(String.format("%s/ChatServer/dwr/call/plaincall/DwrChat.startChat.dwr;jsessionid", userBean.getUrlPrx()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", ""));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrChat"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "startChat"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-param0", "number:" + userBean.getAreaId()));
		postMethod.addParameter(new NameValuePair("c0-param1", "number:1"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);

		try {
			StartChatInfo beanInfo = initBeanInfo(StartChatInfo.class, postMethod.getResponseBodyAsStream(), "dwr");
			logger.info(beanInfo.toString());
			userBean.setChatSessionId(beanInfo.getSessionId());
		} catch (Throwable e) {
			logger.error("获取聊天会话ID异常", e);
		}
	}

	private void msgIdServerTime() {
		PostMethod postMethod = new PostMethod(String.format("%s/hero/dwr/call/plaincall/DwrGameWorld.getMsg.dwr;jsessionid=%s;mid=%s", userBean.getUrlPrx(), userBean.getSessionId(),
				userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdServerTime"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param0", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);
	}

	private void msgIdGetCitiesGoldAvailable() {
		PostMethod postMethod = new PostMethod(String.format("%s/hero/dwr/call/plaincall/DwrGameWorld.getMsg.dwr;jsessionid=%s;mid=%s", userBean.getUrlPrx(), userBean.getSessionId(),
				userBean.getSessionId()));
		postMethod.addRequestHeader("Content-type", "application/octet-stream");
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.addRequestHeader("Pragma", "no-cache");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.addRequestHeader("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GRJ22)");
		postMethod.addRequestHeader("Connection", "Keep-Alive");
		postMethod.addParameter(new NameValuePair("callCount", "1"));
		postMethod.addParameter(new NameValuePair("page", ""));
		postMethod.addParameter(new NameValuePair("httpSessionId", userBean.getSessionId()));
		postMethod.addParameter(new NameValuePair("scriptSessionId", "51A0434AF2250025CA28BCB7B4E55E900"));
		postMethod.addParameter(new NameValuePair("c0-scriptName", "DwrGameWorld"));
		postMethod.addParameter(new NameValuePair("c0-methodName", "getMsg"));
		postMethod.addParameter(new NameValuePair("c0-id", "0"));
		postMethod.addParameter(new NameValuePair("c0-e1", "number:0"));
		postMethod.addParameter(new NameValuePair("c0-e2", "string:msgTypeCity"));
		postMethod.addParameter(new NameValuePair("c0-e3", "string:msgIdGetCitiesGoldAvailable"));
		postMethod.addParameter(new NameValuePair("c0-e4", "string:"));
		postMethod.addParameter(new NameValuePair("c0-param0", "Object_Object:{instanceId:reference:c0-e1, messageType:reference:c0-e2, messageId:reference:c0-e3, message:reference:c0-e4}"));
		postMethod.addParameter(new NameValuePair("batchId", "" + userBean.getBatchId()));
		doRequest(postMethod);
	}
}
