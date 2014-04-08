package com.game.sanguo.base.domain;

public class SkillItem {
	Long id = 0L;
	String name;
	Long mp_consume = 0L;
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

	public Long getMp_consume() {
		return mp_consume;
	}

	public void setMp_consume(Long mp_consume) {
		this.mp_consume = mp_consume;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SkillItem [id=" + id + ", name=" + name + ", mp_consume=" + mp_consume + ", description=" + description + "]";
	}
}
