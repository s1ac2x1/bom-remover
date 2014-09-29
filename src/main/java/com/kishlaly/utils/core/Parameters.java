package com.kishlaly.utils.core;

import com.kishlaly.utils.config.Type;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class Parameters {

	private String src;
	private String mask;
	private Type type;
	private boolean deep;

	private Parameters(String src, String mask, Type type, boolean deep) {
		this.src = src;
		this.mask = mask;
		this.type = type;
		this.deep = deep;
	}

	public static class Builder {
		private String src;
		private String mask;
		private Type type;
		private boolean deep;

		public Builder folder(String src) {
			this.src = src;
			return this;
		}

		public Builder mask(String mask) {
			this.mask = mask;
			return this;
		}

		public Builder type(Type type) {
			this.type = type;
			return this;
		}

		public Builder deep(boolean deep) {
			this.deep = deep;
			return this;
		}

		public Parameters build() {
			return new Parameters(src, mask, type, deep);
		}

	}

	public String getSrc() {
		return src;
	}

	public String getMask() {
		return mask;
	}

	public Type getType() {
		return type;
	}

	public boolean isDeep() {
		return deep;
	}

}
