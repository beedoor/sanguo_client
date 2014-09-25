package com.game.sanguo.base.domain;

public class PveFightResultInfo {
	private long atkerYbLeft;
	private long comHeroId;
	private long comWiseHeroId;
	private long comWiseSkillId;
	private long comWiseSkillSuccess;
	private long errCode;
	private String errMsg;
	private long pvpOccupyTime;
	private long resultCode;
	private String roundComHeros;
	private long specialWiseSkillId;
	private long specialWiseSkillSuccess;
	private String targetHeros;
	public long getAtkerYbLeft() {
		return atkerYbLeft;
	}
	public void setAtkerYbLeft(String atkerYbLeft) {
		this.atkerYbLeft = Long.parseLong(atkerYbLeft);
	}
	public long getComHeroId() {
		return comHeroId;
	}
	public void setComHeroId(String comHeroId) {
		this.comHeroId = Long.parseLong(comHeroId);
	}
	public long getComWiseHeroId() {
		return comWiseHeroId;
	}
	public void setComWiseHeroId(String comWiseHeroId) {
		this.comWiseHeroId = Long.parseLong(comWiseHeroId);
	}
	public long getComWiseSkillId() {
		return comWiseSkillId;
	}
	public void setComWiseSkillId(String comWiseSkillId) {
		this.comWiseSkillId = Long.parseLong(comWiseSkillId);
	}
	public long getComWiseSkillSuccess() {
		return comWiseSkillSuccess;
	}
	public void setComWiseSkillSuccess(String comWiseSkillSuccess) {
		this.comWiseSkillSuccess = Long.parseLong(comWiseSkillSuccess);
	}
	public long getErrCode() {
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
	public long getPvpOccupyTime() {
		return pvpOccupyTime;
	}
	public void setPvpOccupyTime(String pvpOccupyTime) {
		this.pvpOccupyTime = Long.parseLong(pvpOccupyTime);
	}
	public long getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = Long.parseLong(resultCode);
	}
	public String getRoundComHeros() {
		return roundComHeros;
	}
	public void setRoundComHeros(String roundComHeros) {
		this.roundComHeros = roundComHeros;
	}
	public long getSpecialWiseSkillId() {
		return specialWiseSkillId;
	}
	public void setSpecialWiseSkillId(String specialWiseSkillId) {
		this.specialWiseSkillId = Long.parseLong(specialWiseSkillId);
	}
	public long getSpecialWiseSkillSuccess() {
		return specialWiseSkillSuccess;
	}
	public void setSpecialWiseSkillSuccess(String specialWiseSkillSuccess) {
		this.specialWiseSkillSuccess = Long.parseLong(specialWiseSkillSuccess);
	}
	public String getTargetHeros() {
		return targetHeros;
	}
	public void setTargetHeros(String targetHeros) {
		this.targetHeros = targetHeros;
	}
	
}
