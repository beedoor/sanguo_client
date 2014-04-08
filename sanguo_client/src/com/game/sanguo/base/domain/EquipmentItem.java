package com.game.sanguo.base.domain;

public class EquipmentItem {
	Long id = 0L;
	String name;
	Long intelligence = 0L;
	Long max_hp = 0L;
	Long max_mp = 0L;
	Long speed = 0L;
	String description;
	Long strength = 0L;
	Long price = 0L;
	Long max_smith = 0L;
	Long need_level = 0L;

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

	public Long getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Long intelligence) {
		this.intelligence = intelligence;
	}

	public Long getMax_hp() {
		return max_hp;
	}

	public void setMax_hp(Long max_hp) {
		this.max_hp = max_hp;
	}

	public Long getMax_mp() {
		return max_mp;
	}

	public void setMax_mp(Long max_mp) {
		this.max_mp = max_mp;
	}

	public Long getSpeed() {
		return speed;
	}

	public void setSpeed(Long speed) {
		this.speed = speed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStrength() {
		return strength;
	}

	public void setStrength(Long strength) {
		this.strength = strength;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getMax_smith() {
		return max_smith;
	}

	public void setMax_smith(Long max_smith) {
		this.max_smith = max_smith;
	}

	public Long getNeed_level() {
		return need_level;
	}

	public void setNeed_level(Long need_level) {
		this.need_level = need_level;
	}

	@Override
	public String toString() {
		return "EquipmentItem [id=" + id + ", name=" + name + ", intelligence=" + intelligence + ", max_hp=" + max_hp + ", max_mp=" + max_mp + ", speed=" + speed + ", description=" + description
				+ ", strength=" + strength + ", price=" + price + ", max_smith=" + max_smith + ", need_level=" + need_level + "]";
	}


}
