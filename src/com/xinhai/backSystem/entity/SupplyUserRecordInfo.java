package com.xinhai.backSystem.entity;

import java.sql.Timestamp;

/**
* @author Tony
* @version 创建时间：2018年10月23日 下午1:04:35
* @ClassName 类名称
* @Description 类描述
*/
public class SupplyUserRecordInfo {
	private  int id;
	private String department;
	private String userName;
//	private String  supplyType;
	private String  supplyTypeName;
	private String model;
	private Timestamp createTime;
	private int createId;
	private Timestamp updateTime;
	private int updateId;
	private String physicalAddress;
	private String note1;
	private String note2;
	private int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSupplyTypeName() {
		return supplyTypeName;
	}
	public void setSupplyTypeName(String supplyTypeName) {
		this.supplyTypeName = supplyTypeName;
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

	
	
	
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
//	public String getSupplyType() {
//		return supplyType;
//	}
//	public void setSupplyType(String supplyType) {
//		this.supplyType = supplyType;
//	}
	
	
}
