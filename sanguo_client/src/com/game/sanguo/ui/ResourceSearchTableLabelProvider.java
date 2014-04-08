package com.game.sanguo.ui;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.util.GameUtil;

public class ResourceSearchTableLabelProvider extends TableLabelProvider {
	@Override
	public String getColumnText(Object arg0, int columnIndex) {
		CityInfo itemInfo = (CityInfo) arg0;
		switch (columnIndex) {
		case 0:
			return itemInfo.getId() + "";
		case 1:
			return isNull(itemInfo.getOccupierName()) ? "空资源" : itemInfo
					.getOccupierName();
		case 2:
			return itemInfo.getOccupierHeroCount() + "";
		case 3:
			return GameUtil.parseDate(itemInfo.getEndTime());
		default:
			return "";
		}
	}
	
	private boolean isNull(String s)
	{
		if(s == null || s.equals("null"))
		{
			return true;
		}
		return false;
	}
}
