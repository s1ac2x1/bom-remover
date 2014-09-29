package com.kishlaly.utils.config;

import java.io.File;

import com.kishlaly.utils.core.Parameters;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class AbstractRemover implements Remover {

	public static int PROCESSED;
	public static int UPDATED;

	protected Parameters parameters;

	public AbstractRemover(Parameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public int checkBOM(File file) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int checkBOM(String str) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int checkBOM(StringBuilder builder) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void work() {
		throw new UnsupportedOperationException();
	}
}
