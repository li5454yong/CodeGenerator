package com.lxg.codegenerator.model;

import com.lxg.codegenerator.util.Utils;

public class AttrEntity {

	private String daoFrame;
	private String conFrame;
	private String entityName;
	private String entityPackage;
	private String entityFilePath;
	private String daoName;
	private String daoPackage;
	private String daoFilePath;
	private String serviceName;
	private String iserviceName;
	private String servicePackage;
	private String serviceFilePath;
	private String iservicePackage;
	private String iserviceFilePath;
	private String mapName;
	private String mapPackage;
	private String mapFilePath;
	private String hbmName;
	private String hbmPackage;
	private String hbmFilePath;
	private String controllerName;
	private String controllerPackage;
	private String controllerFilePath;
	private String actionName;
	private String actionPackage;
	private String actionFilePath;
	private String saveDir;
	private String saveDir2;
	public String getDaoFrame() {
		return daoFrame;
	}
	public void setDaoFrame(String daoFrame) {
		this.daoFrame = daoFrame;
	}
	public String getConFrame() {
		return conFrame;
	}
	public void setConFrame(String conFrame) {
		this.conFrame = conFrame;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityPackage() {
		return entityPackage;
	}
	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}
	public String getEntityFilePath() {
		return entityFilePath;
	}
	public void setEntityFilePath(String entityFilePath) {
		this.entityFilePath = entityFilePath;
	}
	public String getDaoName() {
		return daoName;
	}
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	public String getDaoPackage() {
		return daoPackage;
	}
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	public String getDaoFilePath() {
		return daoFilePath;
	}
	public void setDaoFilePath(String daoFilePath) {
		this.daoFilePath = daoFilePath;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIserviceName() {
		return iserviceName;
	}
	public void setIserviceName(String iserviceName) {
		this.iserviceName = iserviceName;
	}
	public String getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	public String getServiceFilePath() {
		return serviceFilePath;
	}
	public void setServiceFilePath(String serviceFilePath) {
		this.serviceFilePath = serviceFilePath;
	}
	public String getIservicePackage() {
		return iservicePackage;
	}
	public void setIservicePackage(String iservicePackage) {
		this.iservicePackage = iservicePackage;
	}
	public String getIserviceFilePath() {
		return iserviceFilePath;
	}
	public void setIserviceFilePath(String iserviceFilePath) {
		this.iserviceFilePath = iserviceFilePath;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getMapPackage() {
		return mapPackage;
	}
	public void setMapPackage(String mapPackage) {
		this.mapPackage = mapPackage;
	}
	public String getMapFilePath() {
		return mapFilePath;
	}
	public void setMapFilePath(String mapFilePath) {
		this.mapFilePath = mapFilePath;
	}
	public String getHbmName() {
		return hbmName;
	}
	public void setHbmName(String hbmName) {
		this.hbmName = hbmName;
	}
	public String getHbmPackage() {
		return hbmPackage;
	}
	public void setHbmPackage(String hbmPackage) {
		this.hbmPackage = hbmPackage;
	}
	public String getHbmFilePath() {
		return hbmFilePath;
	}
	public void setHbmFilePath(String hbmFilePath) {
		this.hbmFilePath = hbmFilePath;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getControllerPackage() {
		return controllerPackage;
	}
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
	public String getControllerFilePath() {
		return controllerFilePath;
	}
	public void setControllerFilePath(String controllerFilePath) {
		this.controllerFilePath = controllerFilePath;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionPackage() {
		return actionPackage;
	}
	public void setActionPackage(String actionPackage) {
		this.actionPackage = actionPackage;
	}
	public String getActionFilePath() {
		return actionFilePath;
	}
	public void setActionFilePath(String actionFilePath) {
		this.actionFilePath = actionFilePath;
	}
	public String getSaveDir() {
		return saveDir;
	}
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	public String getSaveDir2() {
		return saveDir2;
	}
	public void setSaveDir2(String saveDir2) {
		this.saveDir2 = saveDir2;
	}
	
	public void replaceAll(String table) {
		this.entityName = Utils.tableToEntity(table);
		this.entityPackage = Utils.parseTemplate(this.entityPackage, "EntityName", this.entityName);
		this.entityFilePath = Utils.parseTemplate(this.entityFilePath, "EntityName", this.entityName);

		this.daoName = Utils.parseTemplate(this.daoName, "EntityName", this.entityName);
		this.daoPackage = Utils.parseTemplate(this.daoPackage, "EntityName", this.entityName);
		this.daoFilePath = Utils.parseTemplate(this.daoFilePath, "EntityName", this.entityName);

		this.serviceName = Utils.parseTemplate(this.serviceName, "EntityName", this.entityName);
		this.servicePackage = Utils.parseTemplate(this.servicePackage, "EntityName", this.entityName);
		this.serviceFilePath = Utils.parseTemplate(this.serviceFilePath, "EntityName", this.entityName);
		this.iserviceName = Utils.parseTemplate(this.iserviceName, "EntityName", this.entityName);
		this.iservicePackage = Utils.parseTemplate(this.iservicePackage, "EntityName", this.entityName);
		this.iserviceFilePath = Utils.parseTemplate(this.iserviceFilePath, "EntityName", this.entityName);

		this.mapName = Utils.parseTemplate(this.mapName, "EntityName", this.entityName);
		this.mapPackage = Utils.parseTemplate(this.mapPackage, "EntityName", this.entityName);
		this.mapFilePath = Utils.parseTemplate(this.mapFilePath, "EntityName", this.entityName);

		this.hbmName = Utils.parseTemplate(this.hbmName, "EntityName", this.entityName);
		this.hbmPackage = Utils.parseTemplate(this.hbmPackage, "EntityName", this.entityName);
		this.hbmFilePath = Utils.parseTemplate(this.hbmFilePath, "EntityName", this.entityName);

		this.controllerName = Utils.parseTemplate(this.controllerName, "EntityName", this.entityName);
		this.controllerPackage = Utils.parseTemplate(this.controllerPackage, "EntityName", this.entityName);
		this.controllerFilePath = Utils.parseTemplate(this.controllerFilePath, "EntityName", this.entityName);

		this.actionName = Utils.parseTemplate(this.actionName, "EntityName", this.entityName);
		this.actionPackage = Utils.parseTemplate(this.actionPackage, "EntityName", this.entityName);
		this.actionFilePath = Utils.parseTemplate(this.actionFilePath, "EntityName", this.entityName);
	}
	
	public AttrEntity init(AttrEntity entity){
		String entityName = "{EntityName}";
		String entityPackage = "com.lxg.";
		
		entity.setEntityName(entityName);
		entity.setEntityPackage(entityPackage+"entity");
		entity.setEntityFilePath(Utils.packToFilePath(entityPackage+"entity")+entityName+".java");
		
		entity.setDaoName(entityName + "Dao");
		entity.setDaoPackage(entityPackage+"dao");
		entity.setDaoFilePath(Utils.packToFilePath(entityPackage+"dao")+entityName+"Dao.java");
		
		entity.setServiceName(entityName+"ServiceImpl");
		entity.setServicePackage(entityPackage+"service.impl");
		entity.setServiceFilePath(Utils.packToFilePath(entityPackage+"service.impl")+entityName+"ServiceImpl.java");
		entity.setIserviceName("I"+entityPackage+"Service");
		entity.setIservicePackage(entityPackage+"service");
		entity.setIserviceFilePath(Utils.packToFilePath(entityPackage+"service")+"I"+entityName+"Service.java");
		
		entity.setMapName(entityName+"Mapper");
		entity.setMapFilePath(entityName+"Mapper.xml");
		
		return entity;
	}
}
