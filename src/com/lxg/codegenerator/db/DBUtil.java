package com.lxg.codegenerator.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lxg.codegenerator.model.DBTable;
import com.lxg.codegenerator.model.DBTableColumn;

/**
 * @author lxg
 *
 * 2016年5月3日下午8:16:21
 */
public class DBUtil {

	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);
	
	private DBConnection connection = new DBConnection();

	/**
	 * 获取表信息
	 * @param table_schema
	 * @return
	 *  oracle下获取表信息：SELECT * FROM all_tables WHERE OWNER='BD_RMM'
	 */
	public List<DBTable> getTableList(String table_schema) {
		String sql = "SELECT * FROM information_schema.tables WHERE table_schema = ?";
		List<DBTable> list = new ArrayList<DBTable>();
		try {
			PreparedStatement statement = connection.getPreparedStatement(sql);
			if (statement != null) {
				statement.setString(1, table_schema);
				ResultSet set = statement.executeQuery();
				if (set != null) {
					DBTable table = null;
					while (set.next()) {
						table = new DBTable();
						table.setTableName(set.getString("table_name"));
						table.setDateBase(table_schema);
						table.setTableComment(set.getString("table_comment"));
						table.setTimestamp(set.getTimestamp("create_time"));
						list.add(table);
					}
				}
				logger.info("表信息加载完成");
				connection.close(set, statement);
			}
		} catch (SQLException e) {
			logger.info("表信息加载失败");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取表结构
	 * oracle下获取表结构 select * from user_tab_columns where table_name='RBKCARD'
	 */
	public List<DBTableColumn> getDBTableColumnList(String schema,String table){
		List<DBTableColumn> list = new ArrayList<DBTableColumn>();
		String sql = "SELECT DISTINCT * FROM information_schema.COLUMNS WHERE "
				+ "table_schema = ? AND table_name = ? ORDER BY ORDINAL_POSITION";
		try {
			PreparedStatement state = connection.getPreparedStatement(sql);
			state.setString(1, schema);
			state.setString(2, table);
			if(state != null){
				ResultSet rs = state.executeQuery();
				if(rs != null){
					DBTableColumn column = null;
					while(rs.next()){
						column = new DBTableColumn();
                        column.setTableSchema(schema);
                        column.setTableName(table);
                        column. setColumnName(rs.getString("column_name"));
                        column.setColumnType(rs.getString("column_type"));
                        column.setColumnDefault(rs.getObject("column_default"));
                        column.setColumnKey(rs.getString("column_key"));
                        column.setColumnComment(rs.getString("column_comment"));
                        column.setDataType(rs.getString("data_type"));
                        column.setCharacterMaximumLength(rs.getInt("character_maximum_length"));
                        column.setCharacterOctetLength(rs.getInt("character_octet_length"));
                        column.setExtra(rs.getString("extra"));
                        column.setIsNullable(rs.getString("is_nullable"));
                        column.setPrivileges(rs.getString("privileges"));
                        column.setOrdinalPosition(rs.getInt("ordinal_position"));
                        list.add(column);
					}
				}
				connection.close(rs, state);
			}
			logger.info(table+"表字段加载完成");
		} catch (SQLException e) {
			logger.info(table+"表字段加载失败");
			e.printStackTrace();
		}
		return list;
	}
}
