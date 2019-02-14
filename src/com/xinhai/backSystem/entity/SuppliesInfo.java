package com.xinhai.backSystem.entity;
/**
* @author Tony
* @version 创建时间：2018年8月20日 上午9:42:14
* @ClassName 类名称
* @Description 类描述
*/
import java.sql.Timestamp;
public class SuppliesInfo {
	private int id;
	//private int serialNumber;
	private String suppliesName;
	//private String supplier;
	private String model;
	//private String unit;
	private Timestamp receiveTime;
	private String source;
	private String digest;
	private String unit;
	private int number;
	private int inventory;
	private double  price;
	private double money;
	private String place;
	private String userName;
	private String note;
	private Timestamp createTime;
	private int createId;
	private Timestamp updateTime;
	private int updateId;
	private String groupName;
	private int state;
	private String extraFile;
	
	private int extraNumber;
	
	
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSuppliesName() {
		return suppliesName;
	}
	public void setSuppliesName(String suppliesName) {
		this.suppliesName = suppliesName;
	}

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

	public void setNumber(int number) {
		this.number = number;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	
	
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getNumber() {
		return number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
