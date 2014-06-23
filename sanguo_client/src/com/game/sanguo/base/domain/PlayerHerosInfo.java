package com.game.sanguo.base.domain;

import java.util.Date;

import com.game.sanguo.base.util.GameUtil;

/**
 * s6
 * 
 * @author beedoor
 * 
 */
public class PlayerHerosInfo {
	private Long belongState=0L;
	private Long blood=0L;
	private Long bloodAdd=0L;
	private Long bloodBuffAdd=0L;
	private String bookSkills;
	private Long bufferBlood=0L;
	private Long cityId=0L;
	private Date createDate;
	private Long currentLineup=0L;
	private Long experience=0L;
	private Long fidelity=0L;
	private Boolean fighting=false;
	private Long forceAdd=0L;
	private Long forceBuffAdd=0L;
	private Long id=0L;
	private Long intelligenceAdd=0L;
	private Long intelligenceBuffAdd=0L;
	private Long killHeroNum=0L;
	private Long kingId=0L;
	private Long level=0L;
	private Long locationAsInt=0L;
	private Long magic=0L;
	private Long magicAdd=0L;
	private Long magicBuffAdd=0L;
	private Long masterLineup=0L;
	private Long masterWarriors=0L;
	private Boolean playerHero=false;
	private Long plusExpCount=0L;
	private Long sendItemFlag=0L;
	private Long sendItemNum=0L;
	private Long soldierNum=0L;
	private Long soldierNumBufferAdd=0L;
	private Long soldierType=0L;
	private Long sourceId=0L;
	private Long surrenderGrem=0L;
	private Long surrenderNum=0L;
	private Long type=0L;
	private Date updateDate;
	private String cityName;
	private String heroName;
	private Long playerId=0L;
	private Long maxMP;
	private Long maxHP;
	private Long intelligence;
	private Long force;
	
	public Long getMaxMP() {
		return maxMP;
	}

	public void setMaxMP(String maxMP) {
		this.maxMP = Long.parseLong(maxMP);
	}

	public Long getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(String maxHP) {
		this.maxHP = Long.parseLong(maxHP);
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = Long.parseLong(playerId);
	}
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public Long getBelongState() {
		return belongState;
	}

	public void setBelongState(String belongState) {
		this.belongState = Long.parseLong(belongState);
	}

	public Long getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = Long.parseLong(blood);
	}

	public Long getBloodAdd() {
		return bloodAdd;
	}

	public void setBloodAdd(String bloodAdd) {
		this.bloodAdd = Long.parseLong(bloodAdd);
	}

	public Long getBloodBuffAdd() {
		return bloodBuffAdd;
	}

	public void setBloodBuffAdd(String bloodBuffAdd) {
		this.bloodBuffAdd = Long.parseLong(bloodBuffAdd);
	}

	public String getBookSkills() {
		return bookSkills;
	}

	public void setBookSkills(String bookSkills) {
		this.bookSkills = bookSkills;
	}

	public Long getBufferBlood() {
		return bufferBlood;
	}

	public void setBufferBlood(String bufferBlood) {
		this.bufferBlood = Long.parseLong(bufferBlood);
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = Long.parseLong(cityId);
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = GameUtil.parseDate(createDate);
	}

	public Long getCurrentLineup() {
		return currentLineup;
	}

	public void setCurrentLineup(String currentLineup) {
		this.currentLineup = Long.parseLong(currentLineup);
	}

	public Long getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = Long.parseLong(experience);
	}

	public Long getFidelity() {
		return fidelity;
	}

	public void setFidelity(String fidelity) {
		this.fidelity = Long.parseLong(fidelity);
	}

	public Boolean getFighting() {
		return fighting;
	}

	public void setFighting(String fighting) {
		this.fighting = Boolean.valueOf(fighting);
	}

	public Long getForceAdd() {
		return forceAdd;
	}

	public void setForceAdd(String forceAdd) {
		this.forceAdd = Long.parseLong(forceAdd);
	}

	public Long getForceBuffAdd() {
		return forceBuffAdd;
	}

	public void setForceBuffAdd(String forceBuffAdd) {
		this.forceBuffAdd = Long.parseLong(forceBuffAdd);
	}

	public Long getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	public Long getIntelligenceAdd() {
		return intelligenceAdd;
	}

	public void setIntelligenceAdd(String intelligenceAdd) {
		this.intelligenceAdd = Long.parseLong(intelligenceAdd);
	}

	public Long getIntelligenceBuffAdd() {
		return intelligenceBuffAdd;
	}

	public void setIntelligenceBuffAdd(String intelligenceBuffAdd) {
		this.intelligenceBuffAdd = Long.parseLong(intelligenceBuffAdd);
	}

	public Long getKillHeroNum() {
		return killHeroNum;
	}

	public void setKillHeroNum(String killHeroNum) {
		this.killHeroNum = Long.parseLong(killHeroNum);
	}

	public Long getKingId() {
		return kingId;
	}

	public void setKingId(String kingId) {
		this.kingId = Long.parseLong(kingId);
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = Long.parseLong(level);
	}

	public Long getLocationAsInt() {
		return locationAsInt;
	}

	public void setLocationAsInt(String locationAsInt) {
		this.locationAsInt = Long.parseLong(locationAsInt);
	}

	public Long getMagic() {
		return magic;
	}

	public void setMagic(String magic) {
		this.magic = Long.parseLong(magic);
	}

	public Long getMagicAdd() {
		return magicAdd;
	}

	public void setMagicAdd(String magicAdd) {
		this.magicAdd = Long.parseLong(magicAdd);
	}

	public Long getMagicBuffAdd() {
		return magicBuffAdd;
	}

	public void setMagicBuffAdd(String magicBuffAdd) {
		this.magicBuffAdd = Long.parseLong(magicBuffAdd);
	}

	public Long getMasterLineup() {
		return masterLineup;
	}

	public void setMasterLineup(String masterLineup) {
		this.masterLineup = Long.parseLong(masterLineup);
	}

	public Long getMasterWarriors() {
		return masterWarriors;
	}

	public void setMasterWarriors(String masterWarriors) {
		this.masterWarriors = Long.parseLong(masterWarriors);
	}

	public Boolean getPlayerHero() {
		return playerHero;
	}

	public void setPlayerHero(String playerHero) {
		this.playerHero = Boolean.valueOf(playerHero);
	}

	public Long getPlusExpCount() {
		return plusExpCount;
	}

	public void setPlusExpCount(String plusExpCount) {
		this.plusExpCount = Long.parseLong(plusExpCount);
	}

	public Long getSendItemFlag() {
		return sendItemFlag;
	}

	public void setSendItemFlag(String sendItemFlag) {
		this.sendItemFlag = Long.parseLong(sendItemFlag);
	}

	public Long getSendItemNum() {
		return sendItemNum;
	}

	public void setSendItemNum(String sendItemNum) {
		this.sendItemNum = Long.parseLong(sendItemNum);
	}

	public Long getSoldierNum() {
		return soldierNum;
	}

	public void setSoldierNum(String soldierNum) {
		this.soldierNum = Long.parseLong(soldierNum);
	}

	public Long getSoldierNumBufferAdd() {
		return soldierNumBufferAdd;
	}

	public void setSoldierNumBufferAdd(String soldierNumBufferAdd) {
		this.soldierNumBufferAdd = Long.parseLong(soldierNumBufferAdd);
	}

	public Long getSoldierType() {
		return soldierType;
	}

	public void setSoldierType(String soldierType) {
		this.soldierType = Long.parseLong(soldierType);
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = Long.parseLong(sourceId);
	}

	public Long getSurrenderGrem() {
		return surrenderGrem;
	}

	public void setSurrenderGrem(String surrenderGrem) {
		this.surrenderGrem = Long.parseLong(surrenderGrem);
	}

	public Long getSurrenderNum() {
		return surrenderNum;
	}

	public void setSurrenderNum(String surrenderNum) {
		this.surrenderNum = Long.parseLong(surrenderNum);
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
	public Long getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(String intelligence) {
		this.intelligence = Long.parseLong(intelligence);
	}

	public Long getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = Long.parseLong(force);
	}

	@Override
	public String toString() {
		return "PlayerHerosInfo [belongState=" + belongState + ", blood=" + blood + ", bloodAdd=" + bloodAdd + ", bloodBuffAdd=" + bloodBuffAdd + ", bookSkills=" + bookSkills + ", bufferBlood="
				+ bufferBlood + ", cityId=" + cityId + ", createDate=" + createDate + ", currentLineup=" + currentLineup + ", experience=" + experience + ", fidelity=" + fidelity + ", fighting="
				+ fighting + ", forceAdd=" + forceAdd + ", forceBuffAdd=" + forceBuffAdd + ", id=" + id + ", intelligenceAdd=" + intelligenceAdd + ", intelligenceBuffAdd=" + intelligenceBuffAdd
				+ ", killHeroNum=" + killHeroNum + ", kingId=" + kingId + ", level=" + level + ", locationAsInt=" + locationAsInt + ", magic=" + magic + ", magicAdd=" + magicAdd + ", magicBuffAdd="
				+ magicBuffAdd + ", masterLineup=" + masterLineup + ", masterWarriors=" + masterWarriors + ", playerHero=" + playerHero + ", plusExpCount=" + plusExpCount + ", sendItemFlag="
				+ sendItemFlag + ", sendItemNum=" + sendItemNum + ", soldierNum=" + soldierNum + ", soldierNumBufferAdd=" + soldierNumBufferAdd + ", soldierType=" + soldierType + ", sourceId="
				+ sourceId + ", surrenderGrem=" + surrenderGrem + ", surrenderNum=" + surrenderNum + ", type=" + type + ", updateDate=" + updateDate + ", cityName=" + cityName + ", heroName="
				+ heroName + "]";
	}


}
