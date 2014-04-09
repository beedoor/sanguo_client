package com.game.sanguo.base.domain;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.game.sanguo.base.util.ItemConfig;

public class UserBean {

	private AtomicInteger numberId = new AtomicInteger(0);
	private AtomicInteger batchId = new AtomicInteger(0);
	private String sessionId;
	private String chatSessionId;
	private String userName;
	private String password;
	private Long userID=0L;
	private String checkId;
	private Long areaId;
	private Long reLoginTime=0L;
	private ClientUpdateInfo clientInfo;

	private LoginGameInfo loginGameInfo = new LoginGameInfo();
	
	private Boolean isSuspend=false;
	private Map<Long,GameAreaInfo> gameAreaInfoMap = new HashMap<Long,GameAreaInfo>();
	
	private Configure configure;
	private ItemConfig itemConfig;
	
	private Map<Long,Long> itemIdToSrcIdMap = new HashMap<Long,Long>();
	private Map<Long,Long> heroIdToSrcIdMap = new HashMap<Long,Long>();
	private String areaName;
	
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName,
				listener);
	}
 
 
	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	
	public Map<Long, Long> getItemIdToSrcIdMap() {
		return itemIdToSrcIdMap;
	}
	public void setItemIdToSrcIdMap(Map<Long, Long> itemIdToSrcIdMap) {
		this.itemIdToSrcIdMap = itemIdToSrcIdMap;
	}
	public Map<Long, Long> getHeroIdToSrcIdMap() {
		return heroIdToSrcIdMap;
	}
	public void setHeroIdToSrcIdMap(Map<Long, Long> heroIdToSrcIdMap) {
		this.heroIdToSrcIdMap = heroIdToSrcIdMap;
	}
	public void putItemIdToSrcId(Long itemId,Long srcId)
	{
		itemIdToSrcIdMap.put(itemId, srcId);
	}
	public void putHeroIdToSrcId(Long itemId,Long srcId)
	{
		heroIdToSrcIdMap.put(itemId, srcId);
	}
	public Long decodeItemSrcIdByUseId(Long userId)
	{
		return itemIdToSrcIdMap.get(userId);
	}
	public Long decodeHeroSrcIdByUseId(Long useId)
	{
		return heroIdToSrcIdMap.get(useId);
	}
	public UserBean() {
		super();
	}

	public ItemConfig getItemConfig() {
		return itemConfig;
	}

	public void setItemConfig(ItemConfig itemConfig) {
		this.itemConfig = itemConfig;
	}

	public UserBean(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public LoginGameInfo getLoginGameInfo() {
		return loginGameInfo;
	}

	public void setLoginGameInfo(LoginGameInfo loginGameInfo) {
		Object oldValue = this.loginGameInfo;
		Object newValue = loginGameInfo;
		this.loginGameInfo = loginGameInfo;
		firePropertyChange("loginGameInfo", oldValue, newValue);
	}

	public void reSetNumberIdAndBatchId()
	{
		numberId.set(0);
//		batchId.set(0);
	}
	public Configure getConfigure() {
		return configure;
	}

	public void setConfigure(Configure configure) {
		this.configure = configure;
	}

	public boolean isSuspend() {
		return isSuspend;
	}

	public Long getReLoginTime() {
		return reLoginTime;
	}

	public void setReLoginTime(Long reLoginTime) {
		this.reLoginTime = reLoginTime;
	}

	public void setSuspend(boolean isSuspend) {
		this.isSuspend = isSuspend;
	}

	public GameAreaInfo getGameAreaInfo(String areaId) {
		return gameAreaInfoMap.get(areaId);
	}

	public ClientUpdateInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientUpdateInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getUrlPrx()
	{
		return String.format("http://%s", gameAreaInfoMap.get(areaId).getUrl());
	}
	public void putGameAreaInfo(GameAreaInfo gameAreaInfo) {
		if(gameAreaInfo.getName().equals(areaName))
		{
			this.areaId = gameAreaInfo.getId();
		}
		gameAreaInfoMap.put(gameAreaInfo.getId(), gameAreaInfo);
	}

	public Long getAreaId() {
		return areaId;
	}


	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}


	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getChatSessionId() {
		return chatSessionId;
	}

	public void setChatSessionId(String chatSessionId) {
		this.chatSessionId = chatSessionId;
	}

	public int getNumberId() {
		return numberId.getAndIncrement();
	}
	public int getNumberIdNoIncrement() {
		return numberId.get();
	}
	public int getBatchIdNoIncrement() {
		return batchId.get();
	}

	public int getBatchId() {
		return batchId.getAndIncrement();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}


	@Override
	public String toString() {
		return "UserBean [numberId=" + numberId + ", batchId=" + batchId
				+ ", sessionId=" + sessionId + ", chatSessionId="
				+ chatSessionId + ", userName=" + userName + ", password="
				+ password + ", userID=" + userID + ", checkId=" + checkId
				+ ", areaId=" + areaId + ", reLoginTime=" + reLoginTime
				+ ", clientInfo=" + clientInfo + ", loginGameInfo="
				+ loginGameInfo + ", isSuspend=" + isSuspend
				+ ", gameAreaInfoMap=" + gameAreaInfoMap + ", configure="
				+ configure + ", itemConfig=" + itemConfig
				+ ", itemIdToSrcIdMap=" + itemIdToSrcIdMap
				+ ", heroIdToSrcIdMap=" + heroIdToSrcIdMap + ", areaName="
				+ areaName + ", propertyChangeSupport=" + propertyChangeSupport
				+ "]";
	}
	
}
