package com.hh.rdp.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.hh.rdp.dm.model.Table;

@XmlRootElement
public class Column  {
	private String id = "";
	private String text = "";
	private String name = "";
	private String type = "String";
	private String length = "256";
	private String empty = "true";
	private String primary = "false";
	private String defaultValue = "";
	
	private Table parent;
	private Map<String, String> map = new HashMap<String, String>();
	
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@XmlAttribute
	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}

	@XmlAttribute
	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	@XmlAttribute
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	
	@XmlTransient
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	@XmlTransient
	public Table getParent() {
		return parent;
	}

	public void setParent(Table parent) {
		this.parent = parent;
	}
	@XmlTransient
	public boolean isLob() {
		return "大字段".equals(length) || "byte[]".equals(type);
	}

	
	
}
