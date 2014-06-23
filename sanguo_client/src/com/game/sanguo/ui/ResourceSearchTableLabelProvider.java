package com.game.sanguo.ui;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.util.GameUtil;

public class ResourceSearchTableLabelProvider
  extends TableLabelProvider
{
  public String getColumnText(Object arg0, int columnIndex)
  {
    CityInfo itemInfo = (CityInfo)arg0;
    switch (columnIndex)
    {
    case 0: 
      return itemInfo.getId()+"";
    case 1: 
      return 
        (isNull(itemInfo.getOccupierName()) ? "空资源" : itemInfo.getOccupierName()) + getBattleStatus(itemInfo.getStatusAsInt());
    case 2: 
      return isNull(itemInfo.getUnionName()) ? "" : itemInfo
        .getUnionName();
    case 3: 
      return GameUtil.parseDate(itemInfo.getEndTime());
    }
    return "";
  }
  
  private String getBattleStatus(Long status)
  {
    if (status.longValue() == 4L) {
      return " 战";
    }
    return "";
  }
  
  protected boolean isNull(String s)
  {
    if ((s == null) || (s.equals("null"))) {
      return true;
    }
    return false;
  }
}
