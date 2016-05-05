package com.lxg.codegenerator.creater;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lxg.codegenerator.db.DBUtil;
import com.lxg.codegenerator.model.AttrEntity;
import com.lxg.codegenerator.model.DBTable;
import com.lxg.codegenerator.model.DBTableColumn;
import com.lxg.codegenerator.util.Consts;
import com.lxg.codegenerator.util.FileUtil;
import com.lxg.codegenerator.util.Utils;

/**
 * java文件生成器
 * @author 李新广
 *
 * 2016年5月4日上午10:01:58
 */
public class Creater {

	private List<DBTable> tableList = null;
	private List<DBTableColumn> tableColumnList = null;
	private DBUtil dbUtil = new DBUtil();
	private Logger logger = LoggerFactory.getLogger(Creater.class);
	private static String dir = "template/";
	private AttrEntity entity = new AttrEntity();
	private boolean hasDate = false, hasTimestamp = false;
	
	public Creater(){
		this.tableList = dbUtil.getTableList("filesys");
	}
	
	@Test
	public void create(){
		
		for(DBTable table : tableList){
			tableColumnList = dbUtil.getDBTableColumnList(table.getDateBase(), table.getTableName());
			entity = entity.init(entity);
			entity.replaceAll(table.getTableName());
			createEntity();
			logger.info(table.getTableName()+".java生成完毕");
			createMyBatisDao();
		}
		createIBaseService();
		createIBaseServiceImpl();
		createIBaseMapper();
	}
	
	/**
	 * 生成实体类
	 * @return
	 */
	public void createEntity(){
		try {
			String path = FileUtil.createFile("d:/test", entity.getEntityFilePath());
			
			String content = FileUtil.readResourceFile(dir+"entity.tlp");
			content = Utils.parseTemplate(content, "package", entity.getEntityPackage());
			content = Utils.parseTemplate(content, "EntityName", entity.getEntityName());
			content = Utils.parseTemplate(content, "attr_list", createAttrList());
			content = Utils.parseTemplate(content, "importDate", hasDate ? Consts.IMPORT_DATE : "");
			content = Utils.parseTemplate(content, "importTimestamp", hasTimestamp ? Consts.IMPORT_TIMESTAMP : "");
			content = Utils.parseTemplate(content, "attr_getset_list", createAttrGetsetList());
			
			FileUtil.writeContentToFile(path, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成MyBatisDao
	 */
	public void createMyBatisDao(){
		try {
			String path = FileUtil.createFile("d:/test", entity.getDaoFilePath());
			
			String content = FileUtil.readResourceFile(dir+"mybatis_dao.tlp");
			
			content = Utils.parseTemplate(content, "package", entity.getDaoPackage());
			content = Utils.parseTemplate(content, "importEntity", entity.getEntityPackage()+"."+entity.getEntityName());
			content = Utils.parseTemplate(content, "entity", entity.getEntityName());
			FileUtil.writeContentToFile(path, content);
			logger.info(entity.getEntityName()+"Dao.java生成完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成IBaseMapper
	 */
	public void createIBaseMapper(){
		try {
			String path = FileUtil.createFile("d:/test", FileUtil.getFileDir(entity.getDaoFilePath())+"IBaseMapper.java");
			String content = FileUtil.readResourceFile(dir+"ibasemapper.tlp");
			content = Utils.parseTemplate(content, "package", entity.getDaoPackage());
			FileUtil.writeContentToFile(path, content);
			logger.info("IBaseMapper.java生成完毕");
		} catch (Exception e) {
			logger.error("IBaseMapper.java生成失败");
			e.printStackTrace();
		}
	}
	/**
	 * 生成baseservice
	 */
	public void createIBaseService(){
		try {
			String path = FileUtil.createFile("d:/test", FileUtil.getFileDir(entity.getIserviceFilePath())+"IBaseService.java");
			String content = FileUtil.readResourceFile(dir+"ibaseservice.tlp");
			content = Utils.parseTemplate(content, "package", entity.getIservicePackage());
			FileUtil.writeContentToFile(path, content);
			logger.info("IBaseService.java生成完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成baseserviceImpl
	 */
	public void createIBaseServiceImpl(){
		try {
			String path = FileUtil.createFile("d:/test", FileUtil.getFileDir(entity.getServiceFilePath())+"BaseServiceImpl.java");
			String content = FileUtil.readResourceFile(dir+"ibaseserviceImpl.tlp");
			content = Utils.parseTemplate(content, "package", entity.getServicePackage());
			content = Utils.parseTemplate(content, "importIBaseService", entity.getIservicePackage()+".IBaseService");
			content = Utils.parseTemplate(content, "importIBaseMapper", entity.getDaoPackage()+".IBaseMapper");
			FileUtil.writeContentToFile(path, content);
			logger.info("BaseServiceImpl.java生成完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成实体类中属性列表
	 * @return
	 */
	public String createAttrList(){
		StringBuilder sb = new StringBuilder();
		if(tableColumnList.size() != 0){
			for(DBTableColumn column : tableColumnList){
				if (column != null) {
					if (column.getDataType().equalsIgnoreCase("date") || column.getDataType().equalsIgnoreCase("datetime")) {
						hasDate = true;
					}
					if (column.getDataType().equalsIgnoreCase("timestamp")
							|| column.getDataType().equalsIgnoreCase("time")) {
						hasTimestamp = true;
					}
					
					sb.append(Consts.TAB1).append(Utils.getAttrDeclare(Utils.getVarJavaType(column.getDataType()), 
							Utils.columnToFeild(column.getColumnName()),column.getColumnDefault()))
							.append("//").append(StringUtils.isNotEmpty(column.getColumnComment())?column.getColumnComment()
    						: column.getColumnName()).append(Consts.ENTER);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成get、set函数
	 * @return
	 */
	public String createAttrGetsetList(){
		StringBuilder sb = new StringBuilder();
		String attr = null;
		if(tableColumnList.size() != 0){
			for(DBTableColumn column : tableColumnList){
				String content = FileUtil.readResourceFile(dir+"getset.tlp");
				attr = Utils.tableToEntity(column.getColumnName());
				content = Utils.parseTemplate(content, "EntityName", column.getTableName());
				content = Utils.parseTemplate(content, "AttrName", Utils.firstCharToUpperCase(attr));
				content = Utils.parseTemplate(content, "attrName", Utils.firstCharToLowerCase(attr));
				content = Utils.parseTemplate(content, "comment", StringUtils.isNotEmpty(column.getColumnComment()) ? column.getColumnComment() : column.getColumnName());
				content = Utils.parseTemplate(content, "JavaType", Utils.getVarJavaType(column.getDataType()));
				sb.append(content).append(Consts.ENTER);
			}
		}
		return sb.toString();
	}
}
