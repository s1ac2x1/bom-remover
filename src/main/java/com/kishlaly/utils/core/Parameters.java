package com.kishlaly.utils.core;

import com.kishlaly.utils.config.Type;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class Parameters {

	private String src;
	private String[] masks;
	private boolean recursively;

	private Parameters(String src, String[] masks, boolean deep) {
		this.src = src;
		this.masks = masks;
		this.recursively = deep;
	}

	public static class Builder {
		private String src;
		private String[] masks;
		private boolean recursively;

		public Builder folder(String src) {
			this.src = src;
			return this;
		}

		public Builder mask(String[] masks) {
			this.masks = masks;
			return this;
		}

		public Builder recursively(boolean recursively) {
			this.recursively = recursively;
			return this;
		}

		public Parameters build() {
			return new Parameters(src, masks, recursively);
		}

	}

	public String getSrc() {
		return src;
	}

	public String[] getMasks() {
		return masks;
	}

	public boolean isRecursively() {
		return recursively;
	}

}
