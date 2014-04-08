package com.game.sanguo.base.domain;

public class GameAreaInfo {
	Long id=0L;
	String name;
	Long recommend=0L;
	Long statusAsInt=0L;
	String url;
	public Long getId() {
		return id;
	}
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = Long.parseLong(recommend);
	}
	public Long getStatusAsInt() {
		return statusAsInt;
	}
	public void setStatusAsInt(String statusAsInt) {
		this.statusAsInt = Long.parseLong(statusAsInt);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GameAreaInfo [id=" + id + ", name=" + name + ", recommend=" + recommend + ", statusAsInt=" + statusAsInt + ", url=" + url + "]";
	}
	
}
