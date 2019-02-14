package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.LogDAO;
import com.xinhai.backSystem.dao.LoginDAO;
import com.xinhai.backSystem.dao.MainWzDAO;
import com.xinhai.backSystem.dao.SuppliesDAO;
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.SupplyTypeDAO;
import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.entity.MainWzEntity;
import com.xinhai.backSystem.entity.SuppliesInfo;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class MainWzService {
	//Normal 正常执行
	//禁用物资类型
	public static JSONObject Normal() throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("code", 200);
				map.put("msg", "删除成功");
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	//使用者列表
	public static JSONObject SearchUserList() throws Exception{		
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=MainWzDAO.SearchUserList();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
	//获取select框中物资列表
	//SearchMainWzDateAllName
	public static JSONObject SearchMainWzDateAllName() throws Exception{		
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=MainWzDAO.SearchMainWzDateAllName();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
	
	//获取物资列表
	public static JSONObject SearchMainWzDate(int page,int limit,String nameSelect,String position,String groupId,String userName) throws Exception{
		String SelectSql = "";
		if ("-1".equals(nameSelect) == false&&null!=nameSelect) {
			SelectSql = SelectSql + " and wm.name='" + nameSelect + "' ";
		}
		if ("-1".equals(groupId) == false&&null!=groupId) {
			SelectSql = SelectSql + " and wm.groupId='" + groupId + "' ";
		}
		if ("-1".equals(position) == false&&null!=position) {
			SelectSql = SelectSql + " and wm.position='" + position + "' ";
		}
		if ("-1".equals(userName) == false&&null!=userName) {
			SelectSql = SelectSql + " and wm.userName='" + userName + "' ";
		}
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=MainWzDAO.SearchMainWzDate(page, limit,SelectSql);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		int count =MainWzDAO.SearchMainWzDataCount(SelectSql);
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "MainWzList");
		 map.put("count",count); //总数量   影响分页部件
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
	
	//增加物资
	public static JSONObject AddMainWzData(String name,String supplier, String model, String unit, String number,
			String userName, int state, String position, String note,int groupId, String userId) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			MainWzDAO.AddMainWzData(name,supplier, model,  unit,  number,userName,  state,  position,  note, groupId,  userId);
			map.put("code", 200);
			map.put("msg", "插入成功");
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
	
	
	//更新物资类型
	public static JSONObject UpdateMainWzData(String name,String supplier, String model, String unit, String number,
			String userName, int state, String position, String note,int groupId, String userId, String id) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		//获取原来的物资类型名称
		MainWzDAO.UpdateMainWzData(  name, supplier,  model,  unit,  number,
				 userName,  state,  position,  note, groupId,  userId,  id);
		map.put("code", 200);
		map.put("msg", "更新成功");
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	
	//禁用物资类型
	public static JSONObject DelMainWzData(String userId,String id) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		MainWzDAO.DelMainWzData(userId, id);
				map.put("code", 200);
				map.put("msg", "删除成功");
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}

			
	
}
