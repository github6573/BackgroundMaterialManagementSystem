package com.xinhai.backSystem.entity;
/**
* @author Tony
* @version 创建时间：2018年12月26日 下午4:25:20
* @ClassName 类名称
* @Description 类描述
*/
public class DictionaryEntity {
	private int id;
	private String type;
	private String value;
	private String name;
	private String note;
	private int state;
	private String chineseName;
	
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	
	
}
