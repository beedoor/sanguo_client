package com.game.sanguo.base.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.game.sanguo.base.domain.CityItem;
import com.game.sanguo.base.domain.EquipmentItem;
import com.game.sanguo.base.domain.FightItem;
import com.game.sanguo.base.domain.HeroItem;
import com.game.sanguo.base.domain.PackItem;
import com.game.sanguo.base.domain.ResourceItem;
import com.game.sanguo.base.domain.SkillItem;

public class AutoFightConfig {
	protected Logger logger = LoggerFactory.getLogger(AutoFightConfig.class);
	private List<FightItem> fightUserConfig = new ArrayList<FightItem>();
	
	
	public AutoFightConfig() {
	}

	

	public List<FightItem> getFightUserConfig() {
		return fightUserConfig;
	}

	public void loadUserConfig() {
		File configFile = new File("./config/autoFight.xml");
		if (!configFile.exists()) {
			logger.error("缺乏自动保护配置文件，创建一个");
			return;
		}
		loadConfig(configFile);
	}

	
	public void add(FightItem fightItem) {
		fightUserConfig.add(fightItem);
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
	}

	private void addRule(Digester digester) {
		digester.addObjectCreate("users/user", FightItem.class);// 创建节点的实例
		digester.addBeanPropertySetter("users/user/name", "name");
		digester.addBeanPropertySetter("users/user/password", "password");
		digester.addBeanPropertySetter("users/user/area", "area");
		digester.addBeanPropertySetter("users/user/defend", "target");
		digester.addBeanPropertySetter("users/user/isOnlyLogin", "isOnlyLogin");
		digester.addBeanPropertySetter("users/user/notDonate", "notDonate");
		digester.addBeanPropertySetter("users/user/vipLv", "vipLv");
		digester.addBeanPropertySetter("users/user/isAutoLqLogin", "isAutoLqLogin");
		digester.addSetNext("users/user", "add");
	}

	public static void main(String args[]) {
		AutoFightConfig config = new AutoFightConfig();
		config.loadUserConfig();
		System.out.println(Arrays.toString(config.fightUserConfig.toArray()));
	}
}
