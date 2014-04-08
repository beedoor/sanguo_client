package com.game.sanguo.base.domain;

public class SellConfig {
	Long sell = 0L;
	Long level = 0L;

	public Long getSell() {
		return sell;
	}

	public void setSell(Long sell) {
		this.sell = sell;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "SellConfig [sell=" + sell + ", level=" + level + "]";
	}

}
