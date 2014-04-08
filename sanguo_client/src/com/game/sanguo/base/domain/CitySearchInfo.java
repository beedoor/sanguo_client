package com.game.sanguo.base.domain;

import java.util.Date;

import com.game.sanguo.base.util.GameUtil;

public class CitySearchInfo {
	String cityIdList;
	Long errCode = 0L;
	String errMsg;
	String heros;
	String items;
	Long gold = new Long(0);
	Long searchGold = new Long(0);
	Date searchTime;
	String searched;

	public String getCityIdList() {
		return cityIdList;
	}

	public void setCityIdList(String cityIdList) {
		this.cityIdList = cityIdList;
	}

	public Long getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = Long.parseLong(errCode);
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getHeros() {
		return heros;
	}

	public void setHeros(String heros) {
		this.heros = heros;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public long getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = Long.parseLong(gold);
	}

	public Long getSearchGold() {
		return searchGold;
	}

	public void setSearchGold(String searchGold) {
		this.searchGold =  Long.parseLong(searchGold);
	}

	public Date getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = GameUtil.parseDate(searchTime);
	}

	public String getSearched() {
		return searched;
	}

	public void setSearched(String searched) {
		this.searched = searched;
	}

	@Override
	public String toString() {
		return "CitySearchInfo [cityIdList=" + cityIdList + ", errCode=" + errCode + ", errMsg=" + errMsg + ", heros=" + heros + ", items=" + items + ", gold=" + gold + ", searchGold=" + searchGold
				+ ", searchTime=" + searchTime + ", searched=" + searched + "]";
	}
}
