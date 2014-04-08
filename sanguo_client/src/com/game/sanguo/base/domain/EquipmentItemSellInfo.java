package com.game.sanguo.base.domain;

public class EquipmentItemSellInfo {
	Long equipId = 0L;
	Long errCode = 0L;
	String errMsg;
	Long gold = 0L;
	Long resultCode = 0L;

	public Long getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = Long.parseLong(equipId);
	}

	public Long getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = Long.parseLong(errCode);
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

	public Long getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = Long.parseLong(resultCode);
	}

	@Override
	public String toString() {
		return "EquipmentItemSellInfo [equipId=" + equipId + ", errCode=" + errCode + ", errMsg=" + errMsg + ", gold=" + gold + ", resultCode=" + resultCode + "]";
	}
}
