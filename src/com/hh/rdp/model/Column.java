package com.hh.rdp.model;


public class Column  {
	private String text = "";
	private String name = "";
	private String type = "String";
	private String length = "";
	private String defaultValue = "";
	
	
	private boolean lob =false;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public boolean isLob() {
		return lob;
	}
	public void setLob(boolean lob) {
		this.lob = lob;
	}
	
}
