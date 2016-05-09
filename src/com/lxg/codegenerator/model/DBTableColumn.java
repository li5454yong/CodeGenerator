package com.lxg.codegenerator.model;

/**
 * 表字段
 * @author lxg
 *
 * 2016年5月3日下午8:30:36
 */
public class DBTableColumn {

	private String tableSchema; // 所属库
    private String tableName; // 所属表
    private String columnName; // 列名
    private String columnType; // bigint(11)，varchar(30)
    private String dataType; // bigint->Long，int->Integer，varchar/char->String，timestamp->Timestamp，date/time->Date，float->Float，double/decimal->Double，blob->byte[]
    private Object columnDefault; // 列缺省值
    private Integer characterOctetLength; // 列最大字节长度，utf-8：
                                          // characterMaximumLength*
                                          // 3，gbk：characterMaximumLength* 2
    private Integer characterMaximumLength; // 列最大长度
    private Integer ordinalPosition; // 字节顺序码，从1开始
    private String isNullable; // 是否可为空，YES：可空，NO：非空
    private String columnKey; // 列键值,PRI主键
    private String extra; // auto_increment 自增长
    private String columnComment; // 列描述
    private String privileges; // 列权限集合，以逗号隔开
    
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Object getColumnDefault() {
		return columnDefault;
	}
	public void setColumnDefault(Object columnDefault) {
		this.columnDefault = columnDefault;
	}
	public Integer getCharacterOctetLength() {
		return characterOctetLength;
	}
	public void setCharacterOctetLength(Integer characterOctetLength) {
		this.characterOctetLength = characterOctetLength;
	}
	public Integer getCharacterMaximumLength() {
		return characterMaximumLength;
	}
	public void setCharacterMaximumLength(Integer characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}
	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}
	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
    
    /**
     * 判断是否是主键
     * @return
     */
	public boolean isPrimaryKey(){
        return columnKey != null && columnKey.equalsIgnoreCase("PRI");
    }
}
