package com.game.sanguo.base.domain;

public class ClientUpdateInfo {
	String delFiles;
	String downloadFiles;
	Long latestVersion = 0L;
	Long result = 0L;

	public String getDelFiles() {
		return delFiles;
	}

	public void setDelFiles(String delFiles) {
		this.delFiles = delFiles;
	}

	public String getDownloadFiles() {
		return downloadFiles;
	}

	public void setDownloadFiles(String downloadFiles) {
		this.downloadFiles = downloadFiles;
	}

	public Long getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = Long.parseLong(latestVersion);
	}

	public Long getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = Long.parseLong(result);
	}

	@Override
	public String toString() {
		return "ClientUpdateInfo [delFiles=" + delFiles + ", downloadFiles=" + downloadFiles + ", latestVersion=" + latestVersion + ", result=" + result + "]";
	}
}
