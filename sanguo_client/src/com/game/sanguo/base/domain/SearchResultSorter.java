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
					if (o1.getOccupierName() == null
							|| o1.getOccupierName().equals("")) {
						return 1;
					}
					if (o2.getOccupierName() == null
							|| o2.getOccupierName().equals("")) {
						return -1;
					}
					return o1.getOccupierName().compareTo(o2.getOccupierName());
				}
			};
		} else if (sortType != null && sortType.equals("byId")) {
			comparator = new Comparator<CityInfo>() {
				public int compare(CityInfo o1, CityInfo o2) {
					if (o1.getId() == null
							|| o1.getId().equals("")) {
						return 1;
					}
					if (o2.getId() == null
							|| o2.getId().equals("")) {
						return -1;
					}
					return o1.getId().compareTo(o2.getId());
				}
			};
		} else {
			comparator = new Comparator<CityInfo>() {
				public int compare(CityInfo o1, CityInfo o2) {
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
}
