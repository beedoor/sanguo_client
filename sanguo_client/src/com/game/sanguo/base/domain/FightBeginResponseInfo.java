package com.game.sanguo.base.domain;

public class FightBeginResponseInfo {
	private Long comHeroId = 0L;
	private Long comHeros = 0L;
	private Long comWiseHeroId = 0L;
	private Long comWiseSkillId = 0L;
	private Long comWiseSkillSuccess = 0L;
	private Long errCode = 0L;
	private String errMsg;
	private Long resultCode = 0L;
	private String roundComHeros;
	private Long specialWiseSkillId = 0L;
	private Long specialWiseSkillSuccess = 0L;

	public Long getComHeroId() {
		return comHeroId;
	}

	public void setComHeroId(Long comHeroId) {
		this.comHeroId = comHeroId;
	}

	public Long getComHeros() {
		return comHeros;
	}

	public void setComHeros(Long comHeros) {
		this.comHeros = comHeros;
	}

	public Long getComWiseHeroId() {
		return comWiseHeroId;
	}

	public void setComWiseHeroId(Long comWiseHeroId) {
		this.comWiseHeroId = comWiseHeroId;
	}

	public Long getComWiseSkillId() {
		return comWiseSkillId;
	}

	public void setComWiseSkillId(Long comWiseSkillId) {
		this.comWiseSkillId = comWiseSkillId;
	}

	public Long getComWiseSkillSuccess() {
		return comWiseSkillSuccess;
	}

	public void setComWiseSkillSuccess(Long comWiseSkillSuccess) {
		this.comWiseSkillSuccess = comWiseSkillSuccess;
	}

	public Long getErrCode() {
		return errCode;
	}

	public void setErrCode(Long errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Long getResultCode() {
		return resultCode;
	}

	public void setResultCode(Long resultCode) {
		this.resultCode = resultCode;
	}

	public String getRoundComHeros() {
		return roundComHeros;
	}

	public void setRoundComHeros(String roundComHeros) {
		this.roundComHeros = roundComHeros;
	}

	public Long getSpecialWiseSkillId() {
		return specialWiseSkillId;
	}

	public void setSpecialWiseSkillId(Long specialWiseSkillId) {
		this.specialWiseSkillId = specialWiseSkillId;
	}

	public Long getSpecialWiseSkillSuccess() {
		return specialWiseSkillSuccess;
	}

	public void setSpecialWiseSkillSuccess(Long specialWiseSkillSuccess) {
		this.specialWiseSkillSuccess = specialWiseSkillSuccess;
	}
}
