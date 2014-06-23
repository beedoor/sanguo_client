package com.game.sanguo.base.domain;

import com.game.sanguo.base.util.ItemConfig;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserBean
{
  private AtomicInteger numberId = new AtomicInteger(0);
  private AtomicInteger batchId = new AtomicInteger(0);
  private String sessionId;
  private String chatSessionId;
  private String userName;
  private String password;
  private Long userID = Long.valueOf(0L);
  private String checkId;
  private Long areaId;
  private Long reLoginTime = Long.valueOf(0L);
  private ClientUpdateInfo clientInfo;
  private LoginGameInfo loginGameInfo = new LoginGameInfo();
  private Boolean isSuspend = Boolean.valueOf(false);
  private Map<Long, GameAreaInfo> gameAreaInfoMap = new HashMap();
  private Configure configure;
  private ItemConfig itemConfig;
  private Map<Long, Long> itemIdToSrcIdMap = new HashMap();
  private Map<Long, Long> heroIdToSrcIdMap = new HashMap();
  private String areaName = "";
  private String scanExclude;
  
  public String getScanExclude()
  {
    return this.scanExclude;
  }
  
  public void setScanExclude(String scanExclude)
  {
    this.scanExclude = scanExclude;
  }
  
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
    this);
  
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    this.propertyChangeSupport.addPropertyChangeListener(propertyName, 
      listener);
  }
  
  public String getAreaName()
  {
    return this.areaName;
  }
  
  public void setAreaName(String areaName)
  {
	  if(areaName != null)
	  {
		  List<GameAreaInfo> allGameList = getAllGameList();
		  for(GameAreaInfo ga:allGameList)  
		  {
			  if(ga.getName().equals(areaName))
			  {
				  this.areaId = ga.getId();
				  break;
			  }
		  }
	  }
    this.areaName = areaName;
  }
  
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    this.propertyChangeSupport.removePropertyChangeListener(propertyName, 
      listener);
  }
  
  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
  {
    this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, 
      newValue);
  }
  
  public Map<Long, Long> getItemIdToSrcIdMap()
  {
    return this.itemIdToSrcIdMap;
  }
  
  public void setItemIdToSrcIdMap(Map<Long, Long> itemIdToSrcIdMap)
  {
    this.itemIdToSrcIdMap = itemIdToSrcIdMap;
  }
  
  public Map<Long, Long> getHeroIdToSrcIdMap()
  {
    return this.heroIdToSrcIdMap;
  }
  
  public void setHeroIdToSrcIdMap(Map<Long, Long> heroIdToSrcIdMap)
  {
    this.heroIdToSrcIdMap = heroIdToSrcIdMap;
  }
  
  public void putItemIdToSrcId(Long itemId, Long srcId)
  {
    this.itemIdToSrcIdMap.put(itemId, srcId);
  }
  
  public void putHeroIdToSrcId(Long itemId, Long srcId)
  {
    this.heroIdToSrcIdMap.put(itemId, srcId);
  }
  
  public Long decodeItemSrcIdByUseId(Long userId)
  {
    return (Long)this.itemIdToSrcIdMap.get(userId);
  }
  
  public Long decodeHeroSrcIdByUseId(Long useId)
  {
    return (Long)this.heroIdToSrcIdMap.get(useId);
  }
  
  public UserBean() {}
  
  public ItemConfig getItemConfig()
  {
    return this.itemConfig;
  }
  
  public void setItemConfig(ItemConfig itemConfig)
  {
    this.itemConfig = itemConfig;
  }
  
  public UserBean(String userName, String password)
  {
    this.userName = userName;
    this.password = password;
  }
  
  public LoginGameInfo getLoginGameInfo()
  {
    return this.loginGameInfo;
  }
  
  public void setLoginGameInfo(LoginGameInfo loginGameInfo)
  {
    Object oldValue = this.loginGameInfo;
    Object newValue = loginGameInfo;
    this.loginGameInfo = loginGameInfo;
    firePropertyChange("loginGameInfo", oldValue, newValue);
  }
  
  public void reSetNumberIdAndBatchId()
  {
    this.numberId.set(0);
  }
  
  public Configure getConfigure()
  {
    return this.configure;
  }
  
  public void setConfigure(Configure configure)
  {
    this.configure = configure;
  }
  
  public boolean isSuspend()
  {
    return this.isSuspend.booleanValue();
  }
  
  public Long getReLoginTime()
  {
    return this.reLoginTime;
  }
  
  public void setReLoginTime(Long reLoginTime)
  {
    this.reLoginTime = reLoginTime;
  }
  
  public void setSuspend(boolean isSuspend)
  {
    this.isSuspend = Boolean.valueOf(isSuspend);
  }
  
  public List<GameAreaInfo> getAllGameList()
  {
    List<GameAreaInfo> aList = new ArrayList();
    Collection<GameAreaInfo> gInfoCo = this.gameAreaInfoMap.values();
    aList.addAll(gInfoCo);
    return aList;
  }
  
  public GameAreaInfo getGameAreaInfo(String areaId)
  {
    return (GameAreaInfo)this.gameAreaInfoMap.get(areaId);
  }
  
  public ClientUpdateInfo getClientInfo()
  {
    return this.clientInfo;
  }
  
  public void setClientInfo(ClientUpdateInfo clientInfo)
  {
    this.clientInfo = clientInfo;
  }
  
  public String getUrlPrx()
  {
    return String.format("http://%s", new Object[] { ((GameAreaInfo)this.gameAreaInfoMap.get(this.areaId)).getUrl() });
  }
  
  public void putGameAreaInfo(GameAreaInfo gameAreaInfo)
  {
    if (gameAreaInfo.getName().equals(this.areaName)) {
      this.areaId = gameAreaInfo.getId();
    }
    this.gameAreaInfoMap.put(gameAreaInfo.getId(), gameAreaInfo);
  }
  
  public Long getAreaId()
  {
	  
    return this.areaId;
  }
  
  public void setAreaId(Long areaId)
  {
    this.areaId = areaId;
  }
  
  public String getCheckId()
  {
    return this.checkId;
  }
  
  public void setCheckId(String checkId)
  {
    this.checkId = checkId;
  }
  
  public String getChatSessionId()
  {
    return this.chatSessionId;
  }
  
  public void setChatSessionId(String chatSessionId)
  {
    this.chatSessionId = chatSessionId;
  }
  
  public int getNumberId()
  {
    return this.numberId.getAndIncrement();
  }
  
  public int getNumberIdNoIncrement()
  {
    return this.numberId.get();
  }
  
  public int getBatchIdNoIncrement()
  {
    return this.batchId.get();
  }
  
  public int getBatchId()
  {
    return this.batchId.getAndIncrement();
  }
  
  public String getSessionId()
  {
    return this.sessionId;
  }
  
  public void setSessionId(String sessionId)
  {
    this.sessionId = sessionId;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public Long getUserID()
  {
    return this.userID;
  }
  
  public void setUserID(Long userID)
  {
    this.userID = userID;
  }
  
  public String toString()
  {
    return 
    










      "UserBean [numberId=" + this.numberId + ", batchId=" + this.batchId + ", sessionId=" + this.sessionId + ", chatSessionId=" + this.chatSessionId + ", userName=" + this.userName + ", password=" + this.password + ", userID=" + this.userID + ", checkId=" + this.checkId + ", areaId=" + this.areaId + ", reLoginTime=" + this.reLoginTime + ", clientInfo=" + this.clientInfo + ", loginGameInfo=" + this.loginGameInfo + ", isSuspend=" + this.isSuspend + ", gameAreaInfoMap=" + this.gameAreaInfoMap + ", configure=" + this.configure + ", itemConfig=" + this.itemConfig + ", itemIdToSrcIdMap=" + this.itemIdToSrcIdMap + ", heroIdToSrcIdMap=" + this.heroIdToSrcIdMap + ", areaName=" + this.areaName + ", propertyChangeSupport=" + this.propertyChangeSupport + "]";
  }
}
