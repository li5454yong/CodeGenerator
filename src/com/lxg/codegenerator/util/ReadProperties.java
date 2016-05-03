package com.lxg.codegenerator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * 创建日期：2015-2-11上午11:33:00
 * 作者：李新广
 *TODO
 **/
public class ReadProperties {
	
	public static String getString(String str){
		InputStream in = ReadProperties.class.getResourceAsStream("/jdbc.properties");  
    	Properties p = new Properties();   
    	try {
			p.load(in);
			str = p.getProperty(str);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return str;
	}
	
}
