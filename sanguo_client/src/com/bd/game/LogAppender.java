package com.bd.game;

import org.eclipse.swt.custom.StyledText;

public class LogAppender {

	StyledText styledText = null;

	public LogAppender(StyledText styledText) {
		super();
		this.styledText = styledText;
	}
	
	public void logger(String s)
	{
		styledText.append(s);
	}
}
