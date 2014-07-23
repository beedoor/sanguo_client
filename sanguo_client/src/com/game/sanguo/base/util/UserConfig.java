package com.game.sanguo.base.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.game.sanguo.base.domain.Configure;
import com.game.sanguo.base.domain.ScanResource;
import com.game.sanguo.base.domain.SellConfig;
import com.game.sanguo.base.domain.UserBean;

public class UserConfig {
	protected Logger logger = LoggerFactory.getLogger(UserConfig.class);
	private Map<String, UserBean> userBeanMap = new HashMap<String, UserBean>();

	public UserConfig() {
	}

	public UserBean getUserConfig(String username) {
		if (username == null) {
			if (!userBeanMap.isEmpty()) {
				Iterator<UserBean> ite = userBeanMap.values().iterator();
				return ite.next();
			}
		}
		return userBeanMap.get(username);
	}

	public void loadUserConfig() {
		File configFile = new File("./config/userconfig.xml");
		logger.info(configFile.getAbsolutePath());
		if (!configFile.exists()) {
			logger.error("缺乏用户配置文件，创建一个");
			return;
		}
		loadConfig(configFile);
	}

	public void addItem(UserBean userBean) {
		userBeanMap.put(userBean.getUserName(), userBean);
	}

	private void loadConfig(File configFile) {
		try {
			Digester dig = new Digester();
			addRule(dig);
			dig.push(this);
			dig.setValidating(false);
			dig.parse(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("加载完毕，共加载"+userBeanMap.size() +"个用户配置文件");
	}

	private void addRule(Digester digester) {
		digester.addObjectCreate("configuration/users/user", UserBean.class);// 创建节点的实例
		digester.addObjectCreate("configuration/users/user/configure",Configure.class);
		digester.addObjectCreate("configuration/users/user/configure/scanResource",ScanResource.class);
		digester.addObjectCreate("configuration/users/user/configure/autoSell",SellConfig.class);
		digester.addCallMethod("configuration/users/user/userName", "setUserName",0);
		digester.addCallMethod("configuration/users/user/password", "setPassword",0);
		digester.addCallMethod("configuration/users/user/reLoginTime", "setReLoginTime",0,new Class[]{Long.class});
		digester.addCallMethod("configuration/users/user/areaName", "setAreaName",0,new Class[]{String.class});
		//设置扫描间隔
//		digester.addSetProperties("configuration/users/user/configure/scanResource","waitTime","waitTime");
//		digester.addCallMethod("configuration/users/user/configure/scanResource/gold","setGold",0,new Class[]{Long.class});
//		digester.addCallMethod("configuration/users/user/configure/scanResource/treasure","setTreasure",0,new Class[]{Long.class});
//		digester.addCallMethod("configuration/users/user/configure/scanResource/market","setMarket",0,new Class[]{Long.class});
//		digester.addCallMethod("configuration/users/user/configure/scanResource/solider","setSolider",0,new Class[]{Long.class});
		
		digester.addCallMethod("configuration/users/user/configure/searchResource","setSearchResource",0,new Class[]{Long.class});
		digester.addCallMethod("configuration/users/user/configure/autoSell/sell","setSell",0,new Class[]{Long.class});
		digester.addCallMethod("configuration/users/user/configure/autoSell/level","setLevel",0,new Class[]{Long.class});
		
		digester.addSetNext("configuration/users/user/configure","setConfigure");
		digester.addSetNext("configuration/users/user/configure/scanResource","setScanResource");
		digester.addSetNext("configuration/users/user/configure/autoSell","setSellConfig");
		
		digester.addSetNext("configuration/users/user", "addItem");
	}
//	private void addRule(Digester digester) {
//		digester.addObjectCreate("configuration/users/user", UserBean.class);// 创建节点的实例
//		digester.addBeanPropertySetter("configuration/users/user/userName", "userName");
//		digester.addBeanPropertySetter("configuration/users/user/password", "password");
//		digester.addBeanPropertySetter("configuration/users/user/areaId", "areaId");
//		digester.addObjectCreate("configuration/users/user/configure",Configure.class);
//		digester.addObjectCreate("configuration/users/user/configure/scanResource",ScanResource.class);
//		//设置扫描间隔
//		digester.addSetProperties("configuration/users/user/configure/scanResource","waitTime","waitTime");
//		
//		digester.addBeanPropertySetter("configuration/users/user/configure/scanResource/gold","gold");
//		digester.addBeanPropertySetter("configuration/users/user/configure/scanResource/treasure","treasure");
//		digester.addBeanPropertySetter("configuration/users/user/configure/scanResource/market","market");
//		
//		digester.addBeanPropertySetter("configuration/users/user/configure/searchResource","searchResource");
//		
//		digester.addSetNext("configuration/users/user/configure","setConfigure", "com.game.sanguo.base.domain.Configure");
//		digester.addSetNext("configuration/users/user/configure/scanResource","setScanResource", "com.game.sanguo.base.domain.ScanResource");
//		
//		digester.addSetNext("configuration/users/user", "addItem");
//	}

	public static void main(String args[]) {
		UserConfig config = new UserConfig();
		config.loadUserConfig();
		UserBean userBean = config.userBeanMap.get("xinyuanalex@sohu.com");
		System.out.println(userBean.getConfigure());
	}
}
