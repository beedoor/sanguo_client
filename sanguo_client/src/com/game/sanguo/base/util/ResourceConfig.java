package com.game.sanguo.base.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.game.sanguo.base.domain.ResourceItem;

public class ResourceConfig {
	protected Logger logger = LoggerFactory.getLogger(ResourceConfig.class);

	private List<String> goldAreaIdList = new ArrayList<String>();
	private List<String> barrackAreaIdList = new ArrayList<String>();
	private List<String> marketAreaIdList = new ArrayList<String>();
	private List<String> treasureAreaIdList = new ArrayList<String>();
	private List<String> soliderAreaIdList = new ArrayList<String>();

	List<ResourceItem> resourceItemList = new ArrayList<ResourceItem>();

	public ResourceConfig() {
	}

	public List<String> getGoldAreaIdList() {
		return goldAreaIdList;
	}

	public void setGoldAreaIdList(List<String> goldAreaIdList) {
		this.goldAreaIdList = goldAreaIdList;
	}

	public List<String> getBarrackAreaIdList() {
		return barrackAreaIdList;
	}

	public void setBarrackAreaIdList(List<String> barrackAreaIdList) {
		this.barrackAreaIdList = barrackAreaIdList;
	}

	public List<String> getMarketAreaIdList() {
		return marketAreaIdList;
	}

	public void setMarketAreaIdList(List<String> marketAreaIdList) {
		this.marketAreaIdList = marketAreaIdList;
	}

	public List<String> getTreasureAreaIdList() {
		return treasureAreaIdList;
	}

	public void setTreasureAreaIdList(List<String> treasureAreaIdList) {
		this.treasureAreaIdList = treasureAreaIdList;
	}

	public List<ResourceItem> getResourceItemList() {
		return resourceItemList;
	}

	public void setResourceItemList(List<ResourceItem> resourceItemList) {
		this.resourceItemList = resourceItemList;
	}

	public List<String> getSoliderAreaIdList() {
		return soliderAreaIdList;
	}

	public void setSoliderAreaIdList(List<String> soliderAreaIdList) {
		this.soliderAreaIdList = soliderAreaIdList;
	}

	public void addItem(ResourceItem ri) {
		if (ri.getType().equals("gold")) {
			goldAreaIdList.add(ri.getAreaId());
		} else if (ri.getType().equals("barrack")) {
			barrackAreaIdList.add(ri.getAreaId());
		} else if (ri.getType().equals("market")) {
			marketAreaIdList.add(ri.getAreaId());
		} else if (ri.getType().equals("treasure")) {
			treasureAreaIdList.add(ri.getAreaId());
		} else if (ri.getType().equals("solider")) {
			soliderAreaIdList.add(ri.getAreaId());
		}
		resourceItemList.add(ri);
	}

	private void resetConfig() {

		goldAreaIdList.clear();

		barrackAreaIdList.clear();

		marketAreaIdList.clear();

		treasureAreaIdList.clear();

		soliderAreaIdList.clear();

		resourceItemList.clear();
	}

	public void loadResourceConfig() {
		File configFile = new File("./config/resource.xml");
		if (!configFile.exists()) {
			logger.error("缺乏资源配置文件，创建一个");
			return;
		}
		resetConfig();
		loadConfig(configFile);
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
		digester.addObjectCreate("resources/resource", ResourceItem.class);// 创建节点的实例
		digester.addBeanPropertySetter("resources/resource/type", "type");
		digester.addBeanPropertySetter("resources/resource/areaId", "areaId");
		digester.addSetNext("resources/resource", "addItem");
	}

	public static void main(String args[]) {
		ResourceConfig config = new ResourceConfig();
		config.loadResourceConfig();
		System.out
				.println(Arrays.toString(config.treasureAreaIdList.toArray()));
	}
}
