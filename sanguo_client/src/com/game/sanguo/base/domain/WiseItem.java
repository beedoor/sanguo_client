package com.game.sanguo.base.domain;

public class WiseItem {
	Long id = 0L;
	String name;
	Long wise_level;
	String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getWise_level() {
		return wise_level;
	}
	public void setWise_level(Long wise_level) {
		this.wise_level = wise_level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "WiseItem [id=" + id + ", name=" + name + ", wise_level="
				+ wise_level + ", description=" + description + "]";
	}

	
}
