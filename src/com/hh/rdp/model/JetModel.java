package com.hh.rdp.model;

import java.util.List;
import java.util.Map;

public class JetModel {

	private List<Map<String, Object>> mapList;
	private String basePackName;
	private String packName;
	private String className;
	private String tableName;
	
	private String extendClassName;
	
	private List<Column> columnList;
	
	
	private String servicePackName;
	private String actionPackName;
	
	private String modelName;
	private String formJsonText;

	public List<Map<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getExtendClassName() {
		return extendClassName;
	}

	public void setExtendClassName(String extendClassName) {
		this.extendClassName = extendClassName;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public String getServicePackName() {
		return servicePackName;
	}

	public void setServicePackName(String servicePackName) {
		this.servicePackName = servicePackName;
	}

	public String getActionPackName() {
		return actionPackName;
	}

	public void setActionPackName(String actionPackName) {
		this.actionPackName = actionPackName;
	}

	public String getBasePackName() {
		return basePackName;
	}

	public void setBasePackName(String basePackName) {
		this.basePackName = basePackName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getFormJsonText() {
		return formJsonText;
	}

	public void setFormJsonText(String formJsonText) {
		this.formJsonText = formJsonText;
	}
	
}
