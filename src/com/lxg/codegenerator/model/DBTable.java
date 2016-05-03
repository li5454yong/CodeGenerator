package com.lxg.codegenerator.model;

import java.sql.Timestamp;

public class DBTable {

	private String dateBase;
	
	private String tableName;
	
	private String tableComment;
	
	private Timestamp timestamp;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDateBase() {
		return dateBase;
	}

	public void setDateBase(String dateBase) {
		this.dateBase = dateBase;
	}
	
	
}
