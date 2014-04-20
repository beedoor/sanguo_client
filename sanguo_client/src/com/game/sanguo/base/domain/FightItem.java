package com.game.sanguo.base.domain;

public class FightItem {

	private String name;
	private String password;
	private String target;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "FightItem [name=" + name + ", password=" + password
				+ ", target=" + target + "]";
	}
	
	
}
