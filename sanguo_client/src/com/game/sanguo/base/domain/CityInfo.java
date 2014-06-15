package com.game.sanguo.base.domain;

import java.util.Date;

public class CityInfo {
	Long areaId = 0L;
	Long badge = 0L;
	String cityName;
	Long citySrc = 0L;
	Long color = 0L;
	Long heroCount = 0L;
	Long id = 0L;
	Long leagueId = 0L;
	Long lv = 0L;
	Long maxHeroCount = 0L;
	Long occupierHeroCount = 0L;
	Long occupierId = 0L;
	String occupierName;
	Long occupierVipLv = 0L;
	Date occupyTime;
	Long playerId = 0L;
	Long protectMsLeft = 0L;
	Long statusAsInt = 0L;
	Long typeAsInt = 0L;
	String unionName;
	Long zoneId = 0L;
	Date endTime;
	Long vipLv=0L;
	
	public Long getVipLv() {
		return vipLv;
	}

	public void setVipLv(String vipLv) {
		this.vipLv = Long.valueOf(vipLv);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = Long.parseLong(areaId);
	}

	public Long getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = Long.parseLong(badge);
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getCitySrc() {
		return citySrc;
	}

	public void setCitySrc(String citySrc) {
		this.citySrc = Long.parseLong(citySrc);
	}

	public Long getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = Long.parseLong(color);
	}

	public Long getHeroCount() {
		return heroCount;
	}

	public void setHeroCount(String heroCount) {
		this.heroCount = Long.parseLong(heroCount);
	}

	public Long getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = Long.parseLong(leagueId);
	}

	public Long getLv() {
		return lv;
	}

	public void setLv(String lv) {
		this.lv = Long.parseLong(lv);
	}

	public Long getMaxHeroCount() {
		return maxHeroCount;
	}

	public void setMaxHeroCount(String maxHeroCount) {
		this.maxHeroCount = Long.parseLong(maxHeroCount);
	}

	public Long getOccupierHeroCount() {
		return occupierHeroCount;
	}

	public void setOccupierHeroCount(String occupierHeroCount) {
		this.occupierHeroCount = Long.parseLong(occupierHeroCount);
	}

	public Long getOccupierId() {
		return occupierId;
	}

	public void setOccupierId(String occupierId) {
		this.occupierId = Long.parseLong(occupierId);
	}

	public String getOccupierName() {
		return occupierName;
	}

	public void setOccupierName(String occupierName) {
		this.occupierName = occupierName;
	}

	public Long getOccupierVipLv() {
		return occupierVipLv;
	}

	public void setOccupierVipLv(String occupierVipLv) {
		this.occupierVipLv = Long.parseLong(occupierVipLv);
	}

	public Date getOccupyTime() {
		return occupyTime;
	}

	public void setOccupyTime(String occupyTime) {
		if (occupyTime != null && !occupyTime.equals("") && !occupyTime.endsWith("null")) {
			this.occupyTime = new Date(Long.parseLong(occupyTime.substring(occupyTime.indexOf("(") + 1, occupyTime.indexOf(")"))));
		}
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = Long.parseLong(playerId);
	}

	public Long getProtectMsLeft() {
		return protectMsLeft;
	}

	public void setProtectMsLeft(String protectMsLeft) {
		this.protectMsLeft = Long.parseLong(protectMsLeft);
	}

	public Long getStatusAsInt() {
		return statusAsInt;
	}

	public void setStatusAsInt(String statusAsInt) {
		this.statusAsInt = Long.parseLong(statusAsInt);
	}

	public Long getTypeAsInt() {
		return typeAsInt;
	}

	public void setTypeAsInt(String typeAsInt) {
		this.typeAsInt = Long.parseLong(typeAsInt);
	}

	public String getUnionName() {
		return unionName;
	}

	public void setUnionName(String unionName) {
		this.unionName = unionName;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = Long.parseLong(zoneId);
	}

	@Override
	public String toString() {
		return "CityInfo [areaId=" + areaId + ", badge=" + badge + ", cityName=" + cityName + ", citySrc=" + citySrc + ", color=" + color + ", heroCount=" + heroCount + ", id=" + id + ", leagueId="
				+ leagueId + ", lv=" + lv + ", maxHeroCount=" + maxHeroCount + ", occupierHeroCount=" + occupierHeroCount + ", occupierId=" + occupierId + ", occupierName=" + occupierName
				+ ", occupierVipLv=" + occupierVipLv + ", occupyTime=" + occupyTime + ", playerId=" + playerId + ", protectMsLeft=" + protectMsLeft + ", statusAsInt=" + statusAsInt + ", typeAsInt="
				+ typeAsInt + ", unionName=" + unionName + ", zoneId=" + zoneId + "]";
	}

}
