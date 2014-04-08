package com.game.sanguo.base.domain;

import java.util.Date;

public class LoginByEmailInfo {

	String checkId;
	String desktopType;
	String desktopUrl;
	Long desktopVersion = 0L;
	Date disuseEndTime;
	String logOnServers;
	String name;
	Long resultCode = 0L;
	Long serverId = 0L;
	Long sex = 0L;
	Long type = 0L;
	Long userId = 0L;

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getDesktopType() {
		return desktopType;
	}

	public void setDesktopType(String desktopType) {
		this.desktopType = desktopType;
	}

	public String getDesktopUrl() {
		return desktopUrl;
	}

	public void setDesktopUrl(String desktopUrl) {
		this.desktopUrl = desktopUrl;
	}

	public Long getDesktopVersion() {
		return desktopVersion;
	}

	public void setDesktopVersion(String desktopVersion) {
		this.desktopVersion = Long.valueOf(desktopVersion);
	}

	public Date getDisuseEndTime() {
		return disuseEndTime;
	}

	public void setDisuseEndTime(String disuseEndTime) {
		// this.disuseEndTime = disuseEndTime;
	}

	public String getLogOnServers() {
		return logOnServers;
	}

	public void setLogOnServers(String logOnServers) {
		this.logOnServers = logOnServers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = Long.parseLong(resultCode);
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = Long.parseLong(serverId);
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = Long.parseLong(sex);
	}

	public Long getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Long.parseLong(type);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = Long.parseLong(userId);
	}

	@Override
	public String toString() {
		return "LoginByEmailInfo [checkId=" + checkId + ", desktopType=" + desktopType + ", desktopUrl=" + desktopUrl + ", desktopVersion=" + desktopVersion + ", disuseEndTime=" + disuseEndTime
				+ ", logOnServers=" + logOnServers + ", name=" + name + ", resultCode=" + resultCode + ", serverId=" + serverId + ", sex=" + sex + ", type=" + type + ", userId=" + userId + "]";
	}
}
