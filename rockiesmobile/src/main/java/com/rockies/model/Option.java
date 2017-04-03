package com.rockies.model;

public class Option {
	private String key = "";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value = "";

	public Option(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
