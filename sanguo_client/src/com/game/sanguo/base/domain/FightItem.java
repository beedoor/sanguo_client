package com.game.sanguo.base.domain;

public class FightItem {

	private String name;
	private String password;
	private String target;
	private String area;
	private int isOnlyLogin;
	private long vipLv;
	private long notDonate=1;
	private long isAutoLqLogin=1;
	
	public long getIsAutoLqLogin() {
		return isAutoLqLogin;
	}
	public void setIsAutoLqLogin(long isAutoLqLogin) {
		this.isAutoLqLogin = isAutoLqLogin;
	}
	public long getNotDonate() {
		return notDonate;
	}
	public void setNotDonate(long notDonate) {
		this.notDonate = notDonate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getIsOnlyLogin() {
		return isOnlyLogin;
	}
	public void setIsOnlyLogin(int isOnlyLogin) {
		this.isOnlyLogin = isOnlyLogin;
	}
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
	
	public long getVipLv() {
		return vipLv;
	}
	public void setVipLv(long vipLv) {
		this.vipLv = vipLv;
	}
	@Override
	public String toString() {
		return "FightItem [name=" + name + ", password=" + password
				+ ", target=" + target + ", area=" + area + ", isOnlyLogin="
				+ isOnlyLogin + ", vipLv=" + vipLv + ", notDonate=" + notDonate
				+ ", isAutoLqLogin=" + isAutoLqLogin + "]";
	}
	

}
