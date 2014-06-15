package com.game.sanguo.base.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SearchResultSorter {
	public static void sort(List<CityInfo> second, final String sortType) {
		Comparator<CityInfo> comparator = null;
		if (sortType != null && sortType.equals("byName")) {
			comparator = new Comparator<CityInfo>() {
				public int compare(CityInfo o1, CityInfo o2) {
					if(isNull(o1.getOccupierName()) && isNull(o2.getOccupierName())){
						return 0;
					}
					if (isNull(o1.getOccupierName())) {
						return 1;
					}
					if (isNull(o2.getOccupierName())) {
						return -1;
					}
					return o1.getOccupierName().compareTo(o2.getOccupierName());
				}
			};
		} else if (sortType != null && sortType.equals("byId")) {
			comparator = new Comparator<CityInfo>() {
				public int compare(CityInfo o1, CityInfo o2) {
					if(isNull(o1.getId()) && isNull(o2.getId())){
						return 0;
					}
					if (isNull(o1.getId())) {
						return 1;
					}
					if (isNull(o2.getId())) {
						return -1;
					}
					return o1.getId().compareTo(o2.getId());
				}
			};
		}else if (sortType != null && sortType.equals("byUnion")) {
			comparator = new Comparator<CityInfo>() {
				public int compare(CityInfo o1, CityInfo o2) {
					if(isNull(o1.getUnionName()) && isNull(o2.getUnionName())){
						return 0;
					}
					if (isNull(o1.getUnionName())) {
						return 1;
					}
					if (isNull(o2.getUnionName())) {
						return -1;
					}
					
					return o1.getUnionName().compareTo(o2.getUnionName());
				}
			};
		} else {
			comparator = new Comparator<CityInfo>() {
				public int compare(CityInfo o1, CityInfo o2) {
					if (o1.getEndTime() == null && o2.getEndTime() == null) {
						return 0;
					}
					if (o1.getEndTime() == null) {
						return -1;
					}
					if (o2.getEndTime() == null) {
						return 1;
					}
					Date o1End = o1.getEndTime();
					Date o2End = o2.getEndTime();
					return o1End.compareTo(o2End);
				}
			};
		}
		Collections.sort(second, comparator);
	}
	
	private static boolean isNull(String s)
	{
		return s == null?true:s.equals("")?true:false;
	}
	private static boolean isNull(Long s)
	{
		return s == null?true:false;
	}
}
