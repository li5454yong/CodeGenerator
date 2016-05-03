package com.lxg.codegenerator.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesUtils {
	
	public static void add(String filePath,String key,String value){
		try {
			Properties   prop = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(filePath);	
			prop.setProperty(key, value);
			prop.store(fos, "set");
			fos.close();
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modify(String filePath,String key,String value){
		add(filePath,key,value);
	}
	
	public static void remove(String filePath,String key){
		try {
			Properties   prop = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(filePath);	
			prop.remove(key);
			prop.store(fos, "set");
			fos.close();
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String filePath,String key){
		try {
			Properties   prop = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			fis.close();
			return prop.getProperty(key);
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getKeys(String filePath){
		try {
			Properties   prop = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			fis.close();
			Enumeration enums = prop.keys();
			if(enums == null){
				return null;
			}
			
			List<String> list = new ArrayList<String>();
			while(enums.hasMoreElements()){
				list.add((String)enums.nextElement());
			}
			return list;
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
