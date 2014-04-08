package com.game.sanguo.base.domain;

import java.util.Date;

import com.game.sanguo.base.util.GameUtil;

public class PlayerItemsInfo {
	private Date createDate;
	private String ext;
	private Long frozenLevel = 0L;
	private Long heroPos = 0L;
	private Long heroUseId = 0L;
	private Long id = 0L;
	private Long num = 0L;
	private Long openHeroSrcId = 0L;
	private Long sendItemFlag = 0L;
	private Long sendItemNum = 0L;
	private Long sourceId = 0L;
	private Long storagePos = 0L;
	private Long strengthenLevel = 0L;
	private Long type = 0L;
	private Date updateDate;

	private String itemName;
	private String heroName;
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = GameUtil.parseDate(createDate);
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Long getFrozenLevel() {
		return frozenLevel;
	}

	public void setFrozenLevel(String frozenLevel) {
		this.frozenLevel = Long.parseLong(frozenLevel);
	}

	public Long getHeroPos() {
		return heroPos;
	}

	public void setHeroPos(String heroPos) {
		this.heroPos = Long.parseLong(heroPos);
	}

	public Long getHeroUseId() {
		return heroUseId;
	}

	public void setHeroUseId(String heroUseId) {
		this.heroUseId = Long.parseLong(heroUseId);
	}

	public Long getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	public Long getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = Long.parseLong(num);
	}

	public Long getOpenHeroSrcId() {
		return openHeroSrcId;
	}

	public void setOpenHeroSrcId(String openHeroSrcId) {
		this.openHeroSrcId = Long.parseLong(openHeroSrcId);
	}

	public Long getSendItemFlag() {
		return sendItemFlag;
	}

	public void setSendItemFlag(String sendItemFlag) {
		this.sendItemFlag = Long.parseLong(sendItemFlag);
	}

	public Long getSendItemNum() {
		return sendItemNum;
	}

	public void setSendItemNum(String sendItemNum) {
		this.sendItemNum = Long.parseLong(sendItemNum);
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = Long.parseLong(sourceId);
	}

	public Long getStoragePos() {
		return storagePos;
	}

	public void setStoragePos(String storagePos) {
		this.storagePos = Long.parseLong(storagePos);
	}

	public Long getStrengthenLevel() {
		return strengthenLevel;
	}

	public void setStrengthenLevel(String strengthenLevel) {
		this.strengthenLevel = Long.parseLong(strengthenLevel);
	}

	public Long getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Long.parseLong(type);
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = GameUtil.parseDate(updateDate);
	}

	@Override
	public String toString() {
		return "PlayerItemsInfo [createDate=" + createDate + ", ext=" + ext + ", frozenLevel=" + frozenLevel + ", heroPos=" + heroPos + ", heroUseId=" + heroUseId + ", id=" + id + ", num=" + num
				+ ", openHeroSrcId=" + openHeroSrcId + ", sendItemFlag=" + sendItemFlag + ", sendItemNum=" + sendItemNum + ", sourceId=" + sourceId + ", storagePos=" + storagePos
				+ ", strengthenLevel=" + strengthenLevel + ", type=" + type + ", updateDate=" + updateDate + ", itemName=" + itemName + ", heroName=" + heroName + "]";
	}

}
