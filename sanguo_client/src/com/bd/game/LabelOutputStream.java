package com.bd.game;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.custom.StyledText;

public class LabelOutputStream extends OutputStream {

	private StyledText styledText;
	
	public LabelOutputStream(StyledText styledText) {
		super();
		this.styledText = styledText;
	}

	@Override
	public void write(int b) throws IOException {
		styledText.append(""+(char)b);
	}
}
