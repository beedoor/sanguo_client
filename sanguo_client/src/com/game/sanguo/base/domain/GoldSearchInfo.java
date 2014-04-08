package com.game.sanguo.base.domain;

import java.util.Date;

import com.game.sanguo.base.util.GameUtil;

public class GoldSearchInfo {
	Long addGold = new Long(0);
	String cityIdList;
	String errCode;
	String errMsg;
	Long gold = new Long(0);
	Date resDate;

	public Long getAddGold() {
		return addGold;
	}

	public void setAddGold(String addGold) {
		this.addGold = Long.parseLong(addGold);
	}

	public String getCityIdList() {
		return cityIdList;
	}

	public void setCityIdList(String cityIdList) {
		this.cityIdList = cityIdList;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Long getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = Long.parseLong(gold);
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		if (resDate != null && resDate.indexOf("Date") != -1) {
			this.resDate = new Date(Long.parseLong(resDate.substring(resDate.indexOf("(") + 1, resDate.indexOf(")"))));
		}
	}

	@Override
	public String toString() {
		return "GoldSearchInfo [addGold=" + addGold + ", cityIdList=" + cityIdList + ", errCode=" + errCode + ", errMsg=" + errMsg + ", gold=" + gold + ", resDate=" + GameUtil.parseDate(resDate)
				+ "]";
	}
}
