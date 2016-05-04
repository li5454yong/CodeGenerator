package com.lxg.codegenerator.util;

import org.apache.commons.lang3.StringUtils;



/**
 * 
 * @author 李新广
 *
 * 2016年5月4日上午11:02:22
 */
public class Utils {

	public static String tableToEntity(String table) {
        String entity = "";
        if (table != null && table.length() > 1) {
            if (table.indexOf("_") != -1) {
                String[] strs = table.split("_");
                for (int i = 0; i < strs.length; i++) {
                    if (strs[i] != null && strs[i].length() > 0) {
                        entity += firstCharToUpperCase(strs[i]);
                    }
                }
            } else {
                entity = firstCharToUpperCase(table);
            }
            if (entity.matches("[A-Z]+")) {
                entity = firstCharToUpperCase(entity.toLowerCase());
            }
        }
        return entity;
    }
	
	/**
	 * 首字母转大写
	 * @param str
	 * @return
	 */
	public static String firstCharToUpperCase(String str){
		if(StringUtils.isNotEmpty(str)){
            String tou = str.substring(0, 1);
            String wei = str.substring(1);
            str = tou.toUpperCase() + wei;
        }
        return str;
	}
	/**
	 * 把包转换为文件存储路径
	 * @param str
	 * @return
	 */
	public static String packToFilePath(String str){
		if(StringUtils.isNotEmpty(str)){
			str = str.replaceAll("[.]", "/")+"/";
		}
		return str;
		
	}
	
	/**
	 * 替换模板文件中的字段
	 * @param content 要替换的模板
	 * @param key 要被替换的字段
	 * @param value 替换成的字段
	 * @return
	 */
	public static String parseTemplate(String content, String key, String value) {
        try {
            if (content != null) {
                content = content.replaceAll("\\{" + key + "\\}", value);
            }
        } catch (Exception e) {
        }
        return content;
    }
	
	/**
	 * 把数据库中的字段类型转换为java类型
	 * @param jdbcType
	 * @return
	 */
	public static String getVarJavaType(String jdbcType) {
        String javaType = Consts.PRIVATE_OBJECT;
        if (jdbcType.equalsIgnoreCase("varchar") || jdbcType.equalsIgnoreCase("char")) {
            javaType = Consts.PRIVATE_STRING;
        } else if (jdbcType.equalsIgnoreCase("int") || jdbcType.equalsIgnoreCase("integer")) {
            javaType = Consts.PRIVATE_INTEGER;
        } else if (jdbcType.equalsIgnoreCase("long") || jdbcType.equalsIgnoreCase("bigint")) {
            javaType = Consts.PRIVATE_LONG;
        } else if (jdbcType.equalsIgnoreCase("float") || jdbcType.equalsIgnoreCase("number")) {
            javaType = Consts.PRIVATE_FLOAT;
        } else if (jdbcType.equalsIgnoreCase("double") || jdbcType.equalsIgnoreCase("decimal")) {
            javaType = Consts.PRIVATE_DOUBLE;
        } else if (jdbcType.equalsIgnoreCase("date") || jdbcType.equalsIgnoreCase("datetime")) {
            javaType = Consts.PRIVATE_DATE;
        } else if (jdbcType.equalsIgnoreCase("timestamp") || jdbcType.equalsIgnoreCase("time")) {
            javaType = Consts.PRIVATE_TIMESTAMP;
        } else if (jdbcType.equalsIgnoreCase("blob")) {
            javaType = Consts.PRIVATE_LBYTE;
        }
        return javaType;
    }
	
	public static String columnToFeild(String column) {
        return firstCharToLowerCase(tableToEntity(column));
    }
	
	public static String firstCharToLowerCase(String content) {
        if (StringUtils.isNotEmpty(content)) {
            String tou = content.substring(0, 1);
            String wei = content.substring(1);
            content = tou.toLowerCase() + wei;
        }
        return content;
    }
	
	/**
	 * 拼接entity中变量的声明
	 * @param javaType
	 * @param attr
	 * @param value
	 * @return
	 */
	public static String getAttrDeclare(String javaType, String attr, Object value) {
        String declare = "";
        if (javaType.indexOf(" String") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = \"" + value.toString() + "\"";
            }
        } else if (javaType.indexOf(" Integer") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = " + value.toString();
            }
        } else if (javaType.indexOf(" Long") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = " + value.toString() + "l";
            }
        } else if (javaType.indexOf(" Float") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = " + value.toString() + "f";
            }
        } else if (javaType.indexOf(" Double") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = " + value.toString() + "d";
            }
        } else if (javaType.indexOf(" Date") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = new Date()";
            }
        } else if (javaType.indexOf(" Timestamp") != -1) {
            declare = javaType + attr;
            if (value != null) {
                declare += " = new Timestamp(new Date().getTime())";
            }
        } else {
            declare = javaType + attr;
        }
        return declare + ";";
    }
}
