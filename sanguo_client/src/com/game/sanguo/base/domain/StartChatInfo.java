package com.game.sanguo.base.domain;

public class StartChatInfo {

	private Long maxIndex = 0L;
	private String msgs;
	private String sessionId;

	public Long getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(String maxIndex) {
		this.maxIndex = Long.parseLong(maxIndex);
	}

	public String getMsgs() {
		return msgs;
	}

	public void setMsgs(String msgs) {
		this.msgs = msgs;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "StartChatInfo [maxIndex=" + maxIndex + ", msgs=" + msgs + ", sessionId=" + sessionId + "]";
	}
}
