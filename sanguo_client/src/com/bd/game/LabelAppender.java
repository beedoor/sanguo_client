package com.bd.game;

import java.io.IOException;

import ch.qos.logback.core.OutputStreamAppender;

public class LabelAppender<E> extends OutputStreamAppender<E> {

	@Override
	protected void writeOut(E event) throws IOException {
		super.writeOut(event);
	}
}
