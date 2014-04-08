package com.game.sanguo.base.domain;

public class CityItem {
	Long id = 0L;
	String city_name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	@Override
	public String toString() {
		return "CityItem [id=" + id + ", city_name=" + city_name + "]";
	}
}
