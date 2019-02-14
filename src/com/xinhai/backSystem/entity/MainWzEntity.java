package com.xinhai.backSystem.entity;

import java.sql.Timestamp;

/**
 * @author Tony
 * @version 创建时间：2019年1月3日 下午2:04:15
 * @ClassName 类名称
 * @Description 类描述
 */

public class MainWzEntity {
	private int id;
	private String name;
	private String supplier;
	private String model;
	private String unit;
	private int number;
	private String userName;
	private int state;
	private String position;
	private String note;
	private int groupId;
	private Timestamp createTime;
	private int createId;
	private Timestamp updateTime;
	private int updateId;
	
	
	private String extraFile;
	private int extraNumber;
	//组别真名
	private String groupName;

	
	
	
	
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public String getExtraFile() {
		return extraFile;
	}

	public void setExtraFile(String extraFile) {
		this.extraFile = extraFile;
	}

	public int getExtraNumber() {
		return extraNumber;
	}

	public void setExtraNumber(int extraNumber) {
		this.extraNumber = extraNumber;
	}
	

}
