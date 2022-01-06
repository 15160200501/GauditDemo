package com.example.gauditdemo.vo;

import java.io.Serializable;

public class AuditItemVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String propertyName;
	private String propertyNameClassKey;
	private String propertyNameResourceKey;
	private String oldValue;
	private String oldValueClassKey;
	private String oldValueResourceKey;

	private String newValue;
	private String newValueClassKey;
	private String newValueResourceKey;

	public String getPropertyName() { return propertyName; }

	public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

	public String getPropertyNameClassKey() { return propertyNameClassKey; }

	public void setPropertyNameClassKey(String propertyNameClassKey) { this.propertyNameClassKey = propertyNameClassKey; }

	public String getPropertyNameResourceKey() { return propertyNameResourceKey; }

	public void setPropertyNameResourceKey(String propertyNameResourceKey) { this.propertyNameResourceKey = propertyNameResourceKey; }

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getOldValueClassKey() {
		return oldValueClassKey;
	}

	public void setOldValueClassKey(String oldValueClassKey) {
		this.oldValueClassKey = oldValueClassKey;
	}

	public String getOldValueResourceKey() {
		return oldValueResourceKey;
	}

	public void setOldValueResourceKey(String oldValueResourceKey) {
		this.oldValueResourceKey = oldValueResourceKey;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getNewValueClassKey() {
		return newValueClassKey;
	}

	public void setNewValueClassKey(String newValueClassKey) {
		this.newValueClassKey = newValueClassKey;
	}

	public String getNewValueResourceKey() {
		return newValueResourceKey;
	}

	public void setNewValueResourceKey(String newValueResourceKey) {
		this.newValueResourceKey = newValueResourceKey;
	}

}
