package com.game.sanguo.base.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LoginGameInfo {
	private Boolean autoFlag;
	private String captureHeros;
	private Long challengeTimes = 0L;
	private Long changeNameTimes = 0L;
	private String computerCitys;
	private String computerHeros;
	private Long continuousLoginDays = 0L;
	private Long continuousLoginRewardRecv = 0L;
	private Long errCode = 0L;
	private String errMsg;
	private Long gem = 0L;
	private Long gold = 0L;
	private Long goldLimitId = 0L;
	private Long head = 0L;
	private String heroBoardData;
	private Long id = 0L;
	private String lastChapterIsReward;
	private Long lastForceId = 0L;
	private Long leagueId = 0L;
	private Long maxMedicalCampStorage = 0L;
	private Long medicalCampStorage = 0L;
	private Long monthCardRewardYB = 0L;
	private String name="";
	private Long occupyPvpMainCityId = 0L;
	private String option;
	private Date permitLoginTime;
	private String playerCitys;
	private String playerHeros;
	private String playerItems;
	private String preChapterIsReward;
	private Long prepareSoldierNum = 0L;
	private Long pvpMainCityOccupyHead = 0L;
	private String pvpMainCityOccupyName;
	private Long pvpScore = 0L;
	private Long resultCode = 0L;
	private Long selectedHeroPresent = 0L;
	private Long sendGold = 0L;
	private String sessionId;
	private Long sex = 0L;
	private Long skipFightTimes = 0L;
	private String taskTriggers;
	private String tasks;
	private Long totalRecharge = 0L;
	private Long unreadLetterCount = 0L;
	private String vipItem;
	private Long vipLv = 0L;
	private String worldInfo;

	private String guideProgress;
	private String piece;
	private Long hun;

	List<PlayerCitysInfo> playerCitysInfoList = new ArrayList<PlayerCitysInfo>();
	List<PlayerHerosInfo> playerHerosInfoList = new ArrayList<PlayerHerosInfo>();
	List<PlayerItemsInfo> playerItemsInfoList = new ArrayList<PlayerItemsInfo>();

	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName,
				listener);
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
	
	
	public boolean isAutoFlag() {
		return autoFlag;
	}

	public void setAutoFlag(String autoFlag) {
		this.autoFlag = Boolean.valueOf(autoFlag);
	}

	public String getCaptureHeros() {
		return captureHeros;
	}

	public void setCaptureHeros(String captureHeros) {
		this.captureHeros = captureHeros;
	}

	public Long getChallengeTimes() {
		return challengeTimes;
	}

	public void setChallengeTimes(String challengeTimes) {
		this.challengeTimes = Long.valueOf(challengeTimes);
	}

	public Long getHun() {
		return hun;
	}


	public void setHun(String hun) {
		this.hun = Long.parseLong(hun);
	}


	public String getPiece() {
		return piece;
	}


	public void setPiece(String piece) {
		this.piece = piece;
	}


	public Long getChangeNameTimes() {
		return changeNameTimes;
	}

	public void setChangeNameTimes(String changeNameTimes) {
		this.changeNameTimes = Long.valueOf(changeNameTimes);
	}

	public String getComputerCitys() {
		return computerCitys;
	}

	public void setComputerCitys(String computerCitys) {
		this.computerCitys = computerCitys;
	}

	public String getComputerHeros() {
		return computerHeros;
	}

	public void setComputerHeros(String computerHeros) {
		this.computerHeros = computerHeros;
	}

	public Long getContinuousLoginDays() {
		return continuousLoginDays;
	}

	public void setContinuousLoginDays(String continuousLoginDays) {
		this.continuousLoginDays = Long.valueOf(continuousLoginDays);
	}

	public Long getContinuousLoginRewardRecv() {
		return continuousLoginRewardRecv;
	}

	public void setContinuousLoginRewardRecv(String continuousLoginRewardRecv) {
		this.continuousLoginRewardRecv = Long.valueOf(continuousLoginRewardRecv);
	}

	public Long getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = Long.valueOf(errCode);
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Long getGem() {
		return gem;
	}

	public void setGem(String gem) {
		this.gem = Long.valueOf(gem);
	}

	public Long getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = Long.valueOf(gold);
	}

	public Long getGoldLimitId() {
		return goldLimitId;
	}

	public void setGoldLimitId(String goldLimitId) {
		this.goldLimitId = Long.valueOf(goldLimitId);
	}

	public Long getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = Long.valueOf(head);
	}

	public String getHeroBoardData() {
		return heroBoardData;
	}

	public void setHeroBoardData(String heroBoardData) {
		this.heroBoardData = heroBoardData;
	}

	public Long getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Long.valueOf(id);
	}

	public String getLastChapterIsReward() {
		return lastChapterIsReward;
	}

	public void setLastChapterIsReward(String lastChapterIsReward) {
		this.lastChapterIsReward = lastChapterIsReward;
	}

	public Long getLastForceId() {
		return lastForceId;
	}

	public void setLastForceId(String lastForceId) {
		this.lastForceId = Long.valueOf(lastForceId);
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = Long.valueOf(leagueId);
	}

	public Long getMaxMedicalCampStorage() {
		return maxMedicalCampStorage;
	}

	public void setMaxMedicalCampStorage(String maxMedicalCampStorage) {
		this.maxMedicalCampStorage = Long.valueOf(maxMedicalCampStorage);
	}

	public Long getMedicalCampStorage() {
		return medicalCampStorage;
	}

	public void setMedicalCampStorage(String medicalCampStorage) {
		this.medicalCampStorage = Long.valueOf(medicalCampStorage);
	}

	public Long getMonthCardRewardYB() {
		return monthCardRewardYB;
	}

	public void setMonthCardRewardYB(String monthCardRewardYB) {
		this.monthCardRewardYB = Long.valueOf(monthCardRewardYB);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOccupyPvpMainCityId() {
		return occupyPvpMainCityId;
	}

	public void setOccupyPvpMainCityId(String occupyPvpMainCityId) {
		this.occupyPvpMainCityId = Long.valueOf(occupyPvpMainCityId);
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Date getPermitLoginTime() {
		return permitLoginTime;
	}

	public void setPermitLoginTime(String permitLoginTime) {
		// this.permitLoginTime = Date.permitLoginTime;
	}

	public String getPlayerCitys() {
		return playerCitys;
	}

	public void setPlayerCitys(String playerCitys) {
		this.playerCitys = playerCitys;
	}

	public String getPlayerHeros() {
		return playerHeros;
	}

	public void setPlayerHeros(String playerHeros) {
		this.playerHeros = playerHeros;
	}

	public String getPlayerItems() {
		return playerItems;
	}

	public void setPlayerItems(String playerItems) {
		this.playerItems = playerItems;
	}

	public String getPreChapterIsReward() {
		return preChapterIsReward;
	}

	public void setPreChapterIsReward(String preChapterIsReward) {
		this.preChapterIsReward = preChapterIsReward;
	}

	public Long getPrepareSoldierNum() {
		return prepareSoldierNum;
	}

	public void setPrepareSoldierNum(String prepareSoldierNum) {
		this.prepareSoldierNum = Long.valueOf(prepareSoldierNum);
	}

	public Long getPvpMainCityOccupyHead() {
		return pvpMainCityOccupyHead;
	}

	public void setPvpMainCityOccupyHead(String pvpMainCityOccupyHead) {
		this.pvpMainCityOccupyHead = Long.valueOf(pvpMainCityOccupyHead);
	}

	public String getPvpMainCityOccupyName() {
		return pvpMainCityOccupyName;
	}

	public void setPvpMainCityOccupyName(String pvpMainCityOccupyName) {
		this.pvpMainCityOccupyName = pvpMainCityOccupyName;
	}

	public Long getPvpScore() {
		return pvpScore;
	}

	public void setPvpScore(String pvpScore) {
		this.pvpScore = Long.valueOf(pvpScore);
	}

	public Long getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = Long.valueOf(resultCode);
	}

	public Long getSelectedHeroPresent() {
		return selectedHeroPresent;
	}

	public void setSelectedHeroPresent(String selectedHeroPresent) {
		this.selectedHeroPresent = Long.valueOf(selectedHeroPresent);
	}

	public Long getSendGold() {
		return sendGold;
	}

	public void setSendGold(String sendGold) {
		this.sendGold = Long.valueOf(sendGold);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = Long.valueOf(sex);
	}

	public Long getSkipFightTimes() {
		return skipFightTimes;
	}

	public void setSkipFightTimes(String skipFightTimes) {
		this.skipFightTimes = Long.valueOf(skipFightTimes);
	}

	public String getTaskTriggers() {
		return taskTriggers;
	}

	public void setTaskTriggers(String taskTriggers) {
		this.taskTriggers = taskTriggers;
	}

	public String getTasks() {
		return tasks;
	}

	public void setTasks(String tasks) {
		this.tasks = tasks;
	}

	public Long getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(String totalRecharge) {
		this.totalRecharge = Long.valueOf(totalRecharge);
	}

	public Long getUnreadLetterCount() {
		return unreadLetterCount;
	}

	public void setUnreadLetterCount(String unreadLetterCount) {
		this.unreadLetterCount = Long.valueOf(unreadLetterCount);
	}

	public String getVipItem() {
		return vipItem;
	}

	public void setVipItem(String vipItem) {
		this.vipItem = vipItem;
	}

	public Long getVipLv() {
		return vipLv;
	}

	public void setVipLv(String vipLv) {
		this.vipLv = Long.valueOf(vipLv);
	}

	public String getWorldInfo() {
		return worldInfo;
	}

	public void setWorldInfo(String worldInfo) {
		this.worldInfo = worldInfo;
	}

	public String getGuideProgress() {
		return guideProgress;
	}

	public void setGuideProgress(String guideProgress) {
		this.guideProgress = guideProgress;
	}

	public List<PlayerCitysInfo> getPlayerCitysInfoList() {
		return playerCitysInfoList;
	}

	public void setPlayerCitysInfoList(List<PlayerCitysInfo> playerCitysInfoList) {
		this.playerCitysInfoList = playerCitysInfoList;
	}

	public List<PlayerHerosInfo> getPlayerHerosInfoList() {
		return playerHerosInfoList;
	}

	public void setPlayerHerosInfoList(List<PlayerHerosInfo> playerHerosInfoList) {
		this.playerHerosInfoList = playerHerosInfoList;
	}

	public List<PlayerItemsInfo> getPlayerItemsInfoList() {
		return playerItemsInfoList;
	}

	public void setPlayerItemsInfoList(List<PlayerItemsInfo> playerItemsInfoList) {
		this.playerItemsInfoList = playerItemsInfoList;
	}






	@Override
	public String toString() {
		return "LoginGameInfo [autoFlag=" + autoFlag + ", captureHeros="
				+ captureHeros + ", challengeTimes=" + challengeTimes
				+ ", changeNameTimes=" + changeNameTimes + ", computerCitys="
				+ computerCitys + ", computerHeros=" + computerHeros
				+ ", continuousLoginDays=" + continuousLoginDays
				+ ", continuousLoginRewardRecv=" + continuousLoginRewardRecv
				+ ", errCode=" + errCode + ", errMsg=" + errMsg + ", gem="
				+ gem + ", gold=" + gold + ", goldLimitId=" + goldLimitId
				+ ", head=" + head + ", heroBoardData=" + heroBoardData
				+ ", id=" + id + ", lastChapterIsReward=" + lastChapterIsReward
				+ ", lastForceId=" + lastForceId + ", leagueId=" + leagueId
				+ ", maxMedicalCampStorage=" + maxMedicalCampStorage
				+ ", medicalCampStorage=" + medicalCampStorage
				+ ", monthCardRewardYB=" + monthCardRewardYB + ", name=" + name
				+ ", occupyPvpMainCityId=" + occupyPvpMainCityId + ", option="
				+ option + ", permitLoginTime=" + permitLoginTime
				+ ", playerCitys=" + playerCitys + ", playerHeros="
				+ playerHeros + ", playerItems=" + playerItems
				+ ", preChapterIsReward=" + preChapterIsReward
				+ ", prepareSoldierNum=" + prepareSoldierNum
				+ ", pvpMainCityOccupyHead=" + pvpMainCityOccupyHead
				+ ", pvpMainCityOccupyName=" + pvpMainCityOccupyName
				+ ", pvpScore=" + pvpScore + ", resultCode=" + resultCode
				+ ", selectedHeroPresent=" + selectedHeroPresent
				+ ", sendGold=" + sendGold + ", sessionId=" + sessionId
				+ ", sex=" + sex + ", skipFightTimes=" + skipFightTimes
				+ ", taskTriggers=" + taskTriggers + ", tasks=" + tasks
				+ ", totalRecharge=" + totalRecharge + ", unreadLetterCount="
				+ unreadLetterCount + ", vipItem=" + vipItem + ", vipLv="
				+ vipLv + ", worldInfo=" + worldInfo + ", guideProgress="
				+ guideProgress + ", hun=" + hun + "]";
	}


	private <T> String toArrayStr(List<T> objList) {
		if (objList == null) {
			return "";
		}
		return Arrays.toString(objList.toArray());
	}
}
