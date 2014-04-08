package com.game.sanguo.base.domain;

import java.util.Date;

import com.game.sanguo.base.util.GameUtil;

/**
 * s5
 * 
 * @author beedoor
 * 
 */
public class PlayerCitysInfo {
	private Date createDate = null;
	private Long fidelity = 0L;
	private Boolean hasObtainAward;
	private Long id;
	private String cityName;
	private Long kingId;
	private Date lastSearchDate;
	private Long level;
	private Long population;
	private Date resDate;
	private Long sourceId;
	private Long type;
	private Date updateDate;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = GameUtil.parseDate(createDate);
	}

	public Long getFidelity() {
		return fidelity;
	}

	public void setFidelity(String fidelity) {
		this.fidelity = Long.parseLong(fidelity);
	}

	public Boolean getHasObtainAward() {
		return hasObtainAward;
	}

	public void setHasObtainAward(String hasObtainAward) {
		this.hasObtainAward = Boolean.valueOf(hasObtainAward);
	}

	public Long getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	public Long getKingId() {
		return kingId;
	}

	public void setKingId(String kingId) {
		this.kingId = Long.parseLong(kingId);
	}

	public Date getLastSearchDate() {
		return lastSearchDate;
	}

	public void setLastSearchDate(String lastSearchDate) {
		this.lastSearchDate = GameUtil.parseDate(lastSearchDate);
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = Long.parseLong(level);
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = Long.parseLong(population);
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = GameUtil.parseDate(resDate);
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = Long.parseLong(sourceId);
	}

	public Long getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Long.parseLong(type);
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = GameUtil.parseDate(updateDate);
	}

	@Override
	public String toString() {
		return "PlayerCitysInfo [createDate=" + createDate + ", fidelity=" + fidelity + ", hasObtainAward=" + hasObtainAward + ", id=" + id + ", cityName=" + cityName + ", kingId=" + kingId
				+ ", lastSearchDate=" + lastSearchDate + ", level=" + level + ", population=" + population + ", resDate=" + resDate + ", sourceId=" + sourceId + ", type=" + type + ", updateDate="
				+ updateDate + "]";
	}
}
