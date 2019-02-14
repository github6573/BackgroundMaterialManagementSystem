package com.xinhai.backSystem.entity;
/**
* @author Tony
* @version 创建时间：2018年8月14日 下午4:01:55
* @ClassName 类名称
* @Description 类描述
*/

import java.sql.Timestamp;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class UserInfo {

	private int id;
	private String department;
	private String workNumber;
	private String userName;
	private Timestamp createTime;
	private int permissions;
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
	public String getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getPermissions() {
		return permissions;
	}
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
	
	
	
}
