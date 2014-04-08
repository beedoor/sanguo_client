package com.bd.game;

import com.game.sanguo.domain.CityInfo;
import com.game.sanguo.util.GameUtil;

public class ResourceSearchTableLabelProvider extends TableLabelProvider {
	@Override
	public String getColumnText(Object arg0, int columnIndex) {
		CityInfo itemInfo = (CityInfo) arg0;
		switch (columnIndex) {
		case 0:
			return itemInfo.getId() + "";
		case 1:
			return itemInfo.getOccupierName() == null?"空资源":itemInfo.getOccupierName();
		case 2:
			return itemInfo.getOccupierHeroCount() + "";
		case 3:
			return GameUtil.parseDate(itemInfo.getEndTime());
		default:
			return "";
		}
	}
}
