package com.game.sanguo.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.domain.SearchResult;

public class ClientSearchResult extends SearchResult {
	private List<CityInfo> marketList = new ArrayList();
	private List<CityInfo> goldList = new ArrayList();
	private List<CityInfo> soliderList = new ArrayList();
	private List<CityInfo> treasureList = new ArrayList();
	
	
	public ClientSearchResult(String sortType) {
		super(sortType);
	}

	public void setMarketList(List<CityInfo> marketList) {
		this.marketList = marketList;
	}

	public void setGoldList(List<CityInfo> goldList) {
		this.goldList = goldList;
	}

	public void setSoliderList(List<CityInfo> soliderList) {
		this.soliderList = soliderList;
	}

	public void setTreasureList(List<CityInfo> treasureList) {
		this.treasureList = treasureList;
	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName,
				listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}

	@Override
	public void addGoIdnfo(CityInfo cityInfo) {
		List<CityInfo> cityInfoList = getGoldList();
		super.addGoIdnfo(cityInfo);
		firePropertyChange("goldList", cityInfoList, getGoldList());
	}

	@Override
	public void addMarketInfo(CityInfo cityInfo) {
		List<CityInfo> cityInfoList = getMarketList();
		super.addMarketInfo(cityInfo);
		firePropertyChange("marketList", cityInfoList, getMarketList());
	}

	@Override
	public void addSoliderInfo(CityInfo cityInfo) {
		List<CityInfo> cityInfoList = getSoliderList();
		super.addSoliderInfo(cityInfo);
		firePropertyChange("soliderList", cityInfoList, getSoliderList());
	}

	@Override
	public void addTreasureInfo(CityInfo cityInfo) {
		List<CityInfo> cityInfoList = getTreasureList();
		super.addTreasureInfo(cityInfo);
		firePropertyChange("treasureList",cityInfoList, getTreasureList());
	}

	@Override
	public void clearGoldList() {
		super.clearGoldList();
		firePropertyChange("goldList", null, getGoldList());
	}

	@Override
	public void clearMarketList() {
		super.clearMarketList();
		firePropertyChange("marketList", null, getMarketList());
	}

	@Override
	public void clearSoliderList() {
		super.clearSoliderList();
		firePropertyChange("soliderList", null, getSoliderList());
	}

	@Override
	public void clearTreasureList() {
		super.clearTreasureList();
		firePropertyChange("treasureList", null, getTreasureList());
	}

	@Override
	public List<CityInfo> getGoldList() {
		// TODO Auto-generated method stub
		return super.getGoldList();
	}

	@Override
	public List<CityInfo> getMarketList() {
		// TODO Auto-generated method stub
		return super.getMarketList();
	}

	@Override
	public List<CityInfo> getSoliderList() {
		// TODO Auto-generated method stub
		return super.getSoliderList();
	}

	@Override
	public List<CityInfo> getTreasureList() {
		// TODO Auto-generated method stub
		return super.getTreasureList();
	}
}
