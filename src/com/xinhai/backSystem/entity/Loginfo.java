package com.xinhai.backSystem.entity;
/**
* @author Tony
* @version 创建时间：2018年8月16日 下午4:52:09
* @ClassName 类名称
* @Description 类描述
*/

import java.sql.Timestamp;

public class Loginfo {
private int id;
private int userId;
private Timestamp logTime;
private String content;
private int state;
private String userName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public Timestamp getLogTime() {
	return logTime;
}
public void setLogTime(Timestamp logTime) {
	this.logTime = logTime;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}





}
