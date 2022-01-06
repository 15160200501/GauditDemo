package com.example.gauditdemo.vo;

import java.io.Serializable;

public class AuditObjectKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String nameClassKey;
	private String nameResourceKey;
	private String value;
	private String valueClassKey;
	private String valueResourceKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameClassKey() { return nameClassKey; }

	public void setNameClassKey(String nameClassKey) { this.nameClassKey = nameClassKey; }

	public String getNameResourceKey() { return nameResourceKey; }

	public void setNameResourceKey(String nameResourceKey) {
		this.nameResourceKey = nameResourceKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueClassKey() {
		return valueClassKey;
	}

	public void setValueClassKey(String valueClassKey) {
		this.valueClassKey = valueClassKey;
	}

	public String getValueResourceKey() {
		return valueResourceKey;
	}

	public void setValueResourceKey(String valueResourceKey) {
		this.valueResourceKey = valueResourceKey;
	}

}
