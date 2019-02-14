package com.xinhai.backSystem.entity;
/**
* @author Tony
* @version 创建时间：2018年12月24日 下午2:02:44
* @ClassName 类名称
* @Description 类描述
*/
public class MemberEntity {
private int  id;
private String name;
private String workNumber;
private String tel;
private String department;
private int state;
private String extraField;
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
public String getWorkNumber() {
	return workNumber;
}
public void setWorkNumber(String workNumber) {
	this.workNumber = workNumber;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getExtraField() {
	return extraField;
}
public void setExtraField(String extraField) {
	this.extraField = extraField;
}





}
