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
import com.game.sanguo.base.domain.HeroItem;
import com.game.sanguo.base.domain.PackItem;
import com.game.sanguo.base.domain.SkillItem;

public class ItemConfig {
	protected Logger logger = LoggerFactory.getLogger(ItemConfig.class);
	private List<HeroItem> heroItemList = new ArrayList<HeroItem>();
	private List<PackItem> packItemList = new ArrayList<PackItem>();
	private List<EquipmentItem> equipmentItemList = new ArrayList<EquipmentItem>();
	private List<SkillItem> skillItemList = new ArrayList<SkillItem>();
	private List<CityItem> cityItemList = new ArrayList<CityItem>();

	private Map<Long, HeroItem> heroItemMap = new HashMap<Long, HeroItem>();
	private Map<Long, PackItem> packItemMap = new HashMap<Long, PackItem>();
	private Map<Long, EquipmentItem> equipmentItemMap = new HashMap<Long, EquipmentItem>();
	private Map<Long, SkillItem> skillItemMap = new HashMap<Long, SkillItem>();
	private Map<Long, CityItem> cityItemMap = new HashMap<Long, CityItem>();

	public HeroItem decodeHero(Long heroId) {
		if (heroItemMap.containsKey(heroId)) {
			return heroItemMap.get(heroId);
		}
		return null;
	}

	public PackItem decodePack(Long packId) {
		if (packItemMap.containsKey(packId)) {
			return packItemMap.get(packId);
		}
		return null;
	}

	public EquipmentItem decodeEquipment(Long equipmentId) {
		if (equipmentItemMap.containsKey(equipmentId)) {
			return equipmentItemMap.get(equipmentId);
		}
		return null;
	}

	public SkillItem decodeSkill(Long skillId) {
		if (skillItemMap.containsKey(skillId)) {
			return skillItemMap.get(skillId);
		}
		return null;
	}

	public CityItem decodeCity(Long cityId) {
		if (cityItemMap.containsKey(cityId)) {
			return cityItemMap.get(cityId);
		}
		return null;
	}

	public ItemConfig() {
	}

	public void loadUserConfig() {
		File configFile = new File("./config/item.xml");
		if (!configFile.exists()) {
			logger.error("缺乏物品配置文件，创建一个");
			return;
		}
		loadConfig(configFile);
	}

	public void addPack(PackItem packItem) {
		packItemList.add(packItem);
		packItemMap.put(packItem.getId(), packItem);
	}

	public void addHero(HeroItem heroItem) {
		heroItemList.add(heroItem);
		heroItemMap.put(heroItem.getId(), heroItem);
	}

	public void addEquipment(EquipmentItem equipmentItem) {
		equipmentItemList.add(equipmentItem);
		equipmentItemMap.put(equipmentItem.getId(), equipmentItem);
	}

	public void addSkill(SkillItem skillItem) {
		skillItemList.add(skillItem);
		skillItemMap.put(skillItem.getId(), skillItem);
	}

	public void addCity(CityItem cityItem) {
		cityItemList.add(cityItem);
		cityItemMap.put(cityItem.getId(), cityItem);
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
		digester.addObjectCreate("configuration/packs/pack", PackItem.class);// 创建节点的实例
		digester.addObjectCreate("configuration/heros/hero", HeroItem.class);// 创建节点的实例
		digester.addObjectCreate("configuration/equipments/equipment", EquipmentItem.class);// 创建节点的实例
		digester.addObjectCreate("configuration/skills/skill", SkillItem.class);// 创建节点的实例
		digester.addObjectCreate("configuration/citys/city", CityItem.class);// 创建节点的实例
		digester.addSetProperties("configuration/packs/pack");
		digester.addSetProperties("configuration/heros/hero");
		digester.addSetProperties("configuration/equipments/equipment");
		digester.addSetProperties("configuration/skills/skill");
		digester.addSetProperties("configuration/citys/city");

		digester.addSetNext("configuration/packs/pack", "addPack");
		digester.addSetNext("configuration/heros/hero", "addHero");
		digester.addSetNext("configuration/equipments/equipment", "addEquipment");
		digester.addSetNext("configuration/skills/skill", "addSkill");
		digester.addSetNext("configuration/citys/city", "addCity");
	}

	public static void main(String args[]) {
		ItemConfig config = new ItemConfig();
		config.loadUserConfig();
		System.out.println(Arrays.toString(config.packItemList.toArray()));
		System.out.println(Arrays.toString(config.heroItemList.toArray()));
		System.out.println(Arrays.toString(config.equipmentItemList.toArray()));
		System.out.println(Arrays.toString(config.skillItemList.toArray()));
//		System.out.println(Arrays.toString(config.cityItemList.toArray()));
	}
}
