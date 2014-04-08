package com.game.sanguo.base.domain;

public class Configure {

	private ScanResource scanResource;
	private Long searchResource = 0L;
	private SellConfig sellConfig;

	public Configure() {
		super();
	}

	public Long getSearchResource() {
		return searchResource;
	}

	public void setSearchResource(Long searchResource) {
		this.searchResource = searchResource;
	}

	public ScanResource getScanResource() {
		return scanResource;
	}

	public void setScanResource(ScanResource scanResource) {
		this.scanResource = scanResource;
	}

	public SellConfig getSellConfig() {
		return sellConfig;
	}

	public void setSellConfig(SellConfig sellConfig) {
		this.sellConfig = sellConfig;
	}

	@Override
	public String toString() {
		return "Configure [scanResource=" + scanResource + ", searchResource=" + searchResource + ", sellConfig=" + sellConfig + "]";
	}

}
