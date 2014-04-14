package com.game.sanguo.base.domain;

public class ScanResourceData {
	private Long xPosStart=0L;
	private Long yPosStart=0L;
	private Long xPosEnd=0L;
	private Long yPosEnd=0L;
	private Long isAllScanOpen=0L;

	public ScanResourceData() {
	}

	public Long getxPosStart() {
		return xPosStart;
	}

	public void setxPosStart(Long xPosStart) {
		this.xPosStart = xPosStart;
	}

	public Long getyPosStart() {
		return yPosStart;
	}

	public void setyPosStart(Long yPosStart) {
		this.yPosStart = yPosStart;
	}

	public Long getxPosEnd() {
		return xPosEnd;
	}

	public void setxPosEnd(Long xPosEnd) {
		this.xPosEnd = xPosEnd;
	}

	public Long getyPosEnd() {
		return yPosEnd;
	}

	public void setyPosEnd(Long yPosEnd) {
		this.yPosEnd = yPosEnd;
	}

	public Long getIsAllScanOpen() {
		return isAllScanOpen;
	}

	public void setIsAllScanOpen(Long isAllScanOpen) {
		this.isAllScanOpen = isAllScanOpen;
	}

	@Override
	public String toString() {
		return "ScanResourceData [xPosStart=" + xPosStart + ", yPosStart="
				+ yPosStart + ", xPosEnd=" + xPosEnd + ", yPosEnd=" + yPosEnd
				+ ", isAllScanOpen=" + isAllScanOpen + "]";
	}
	
}