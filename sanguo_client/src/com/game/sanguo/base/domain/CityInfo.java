package com.game.sanguo.base.domain;

import java.util.Date;

public class CityInfo
{
  Long areaId = Long.valueOf(0L);
  Long badge = Long.valueOf(0L);
  String cityName;
  Long citySrc = Long.valueOf(0L);
  Long color = Long.valueOf(0L);
  Long heroCount = Long.valueOf(0L);
  Long id = Long.valueOf(0L);
  Long leagueId = Long.valueOf(0L);
  Long lv = Long.valueOf(0L);
  Long maxHeroCount = Long.valueOf(0L);
  Long occupierHeroCount = Long.valueOf(0L);
  Long occupierId = Long.valueOf(0L);
  String occupierName;
  Long occupierVipLv = Long.valueOf(0L);
  Date occupyTime;
  Long playerId = Long.valueOf(0L);
  Long protectMsLeft = Long.valueOf(0L);
  Long statusAsInt = Long.valueOf(0L);
  Long typeAsInt = Long.valueOf(0L);
  String unionName;
  Long zoneId = Long.valueOf(0L);
  Date endTime;
  Long vipLv = Long.valueOf(0L);
  
  public Long getVipLv()
  {
    return this.vipLv;
  }
  
  public void setVipLv(String vipLv)
  {
    this.vipLv = Long.valueOf(vipLv);
  }
  
  public Date getEndTime()
  {
    return this.endTime;
  }
  
  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }
  
  public Long getAreaId()
  {
    return this.areaId;
  }
  
  public void setAreaId(String areaId)
  {
    this.areaId = Long.valueOf(Long.parseLong(areaId));
  }
  
  public Long getBadge()
  {
    return this.badge;
  }
  
  public void setBadge(String badge)
  {
    this.badge = Long.valueOf(Long.parseLong(badge));
  }
  
  public String getCityName()
  {
    return this.cityName;
  }
  
  public void setCityName(String cityName)
  {
    this.cityName = cityName;
  }
  
  public Long getCitySrc()
  {
    return this.citySrc;
  }
  
  public void setCitySrc(String citySrc)
  {
    this.citySrc = Long.valueOf(Long.parseLong(citySrc));
  }
  
  public Long getColor()
  {
    return this.color;
  }
  
  public void setColor(String color)
  {
    this.color = Long.valueOf(Long.parseLong(color));
  }
  
  public Long getHeroCount()
  {
    return this.heroCount;
  }
  
  public void setHeroCount(String heroCount)
  {
    this.heroCount = Long.valueOf(Long.parseLong(heroCount));
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = Long.valueOf(Long.parseLong(id));
  }
  
  public Long getLeagueId()
  {
    return this.leagueId;
  }
  
  public void setLeagueId(String leagueId)
  {
    this.leagueId = Long.valueOf(Long.parseLong(leagueId));
  }
  
  public Long getLv()
  {
    return this.lv;
  }
  
  public void setLv(String lv)
  {
    this.lv = Long.valueOf(Long.parseLong(lv));
  }
  
  public Long getMaxHeroCount()
  {
    return this.maxHeroCount;
  }
  
  public void setMaxHeroCount(String maxHeroCount)
  {
    this.maxHeroCount = Long.valueOf(Long.parseLong(maxHeroCount));
  }
  
  public Long getOccupierHeroCount()
  {
    return this.occupierHeroCount;
  }
  
  public void setOccupierHeroCount(String occupierHeroCount)
  {
    this.occupierHeroCount = Long.valueOf(Long.parseLong(occupierHeroCount));
  }
  
  public Long getOccupierId()
  {
    return this.occupierId;
  }
  
  public void setOccupierId(String occupierId)
  {
    this.occupierId = Long.valueOf(Long.parseLong(occupierId));
  }
  
  public String getOccupierName()
  {
    return this.occupierName;
  }
  
  public void setOccupierName(String occupierName)
  {
    this.occupierName = occupierName;
  }
  
  public Long getOccupierVipLv()
  {
    return this.occupierVipLv;
  }
  
  public void setOccupierVipLv(String occupierVipLv)
  {
    this.occupierVipLv = Long.valueOf(Long.parseLong(occupierVipLv));
  }
  
  public Date getOccupyTime()
  {
    return this.occupyTime;
  }
  
  public void setOccupyTime(String occupyTime)
  {
    if ((occupyTime != null) && (!occupyTime.equals("")) && (!occupyTime.endsWith("null"))) {
      this.occupyTime = new Date(Long.parseLong(occupyTime.substring(occupyTime.indexOf("(") + 1, occupyTime.indexOf(")"))));
    }
  }
  
  public Long getPlayerId()
  {
    return this.playerId;
  }
  
  public void setPlayerId(String playerId)
  {
    this.playerId = Long.valueOf(Long.parseLong(playerId));
  }
  
  public Long getProtectMsLeft()
  {
    return this.protectMsLeft;
  }
  
  public void setProtectMsLeft(String protectMsLeft)
  {
    this.protectMsLeft = Long.valueOf(Long.parseLong(protectMsLeft));
  }
  
  public Long getStatusAsInt()
  {
    return this.statusAsInt;
  }
  
  public void setStatusAsInt(String statusAsInt)
  {
    this.statusAsInt = Long.valueOf(Long.parseLong(statusAsInt));
  }
  
  public Long getTypeAsInt()
  {
    return this.typeAsInt;
  }
  
  public void setTypeAsInt(String typeAsInt)
  {
    this.typeAsInt = Long.valueOf(Long.parseLong(typeAsInt));
  }
  
  public String getUnionName()
  {
    return this.unionName;
  }
  
  public void setUnionName(String unionName)
  {
    this.unionName = unionName;
  }
  
  public Long getZoneId()
  {
    return this.zoneId;
  }
  
  public void setZoneId(String zoneId)
  {
    this.zoneId = Long.valueOf(Long.parseLong(zoneId));
  }
  
  public String toString()
  {
    return 
      "CityInfo [areaId=" + this.areaId + ", badge=" + this.badge + ", cityName=" + this.cityName + ", citySrc=" + this.citySrc + ", color=" + this.color + ", heroCount=" + this.heroCount + ", id=" + this.id + ", leagueId=" + this.leagueId + ", lv=" + this.lv + ", maxHeroCount=" + this.maxHeroCount + ", occupierHeroCount=" + this.occupierHeroCount + ", occupierId=" + this.occupierId + ", occupierName=" + this.occupierName + ", occupierVipLv=" + this.occupierVipLv + ", occupyTime=" + this.occupyTime + ", playerId=" + this.playerId + ", protectMsLeft=" + this.protectMsLeft + ", statusAsInt=" + this.statusAsInt + ", typeAsInt=" + this.typeAsInt + ", unionName=" + this.unionName + ", zoneId=" + this.zoneId + "]";
  }
}
