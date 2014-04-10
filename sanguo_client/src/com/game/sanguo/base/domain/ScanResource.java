package com.game.sanguo.base.domain;

public class ScanResource {

	Long gold=0L;
	Long treasure=0L;
	Long market=0L;
	Long solider=0L;
	Long waitTime=1000L;
	String 	sortType;
	public Long getGold() {
		return gold;
	}
	public void setGold(Long gold) {
		this.gold = gold;
	}
	public Long getTreasure() {
		return treasure;
	}
	public void setTreasure(Long treasure) {
		this.treasure = treasure;
	}
	public Long getMarket() {
		return market;
	}
	public void setMarket(Long market) {
		this.market = market;
	}
	public Long getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(Long waitTime) {
		this.waitTime = waitTime;
	}
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	public Long getSolider() {
		return solider;
	}
	public void setSolider(Long solider) {
		this.solider = solider;
	}
	@Override
	public String toString() {
		return "ScanResource [gold=" + gold + ", treasure=" + treasure + ", market=" + market + ", solider=" + solider + ", waitTime=" + waitTime + ", sortType=" + sortType + "]";
	}
}
