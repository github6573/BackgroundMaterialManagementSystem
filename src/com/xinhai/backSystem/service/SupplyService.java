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
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.SupplyTypeDAO;
import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class SupplyService {
	//获取物资类型列表
	public static JSONObject SelectSupplyData(int page,int limit) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplyDAO.SelectSupplyData(page, limit);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		int count =SupplyDAO.SelectSupplyDataCount();
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("count",count); //总数量   影响分页部件
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
	//根据要求获取物资类型列表
		public static JSONObject SelectSupplyDataBySelect(int page,int limit,String type,String typeName,String model) throws Exception{
			String sqlSelect="";
			if ("-1".equals(type) == false) {
				sqlSelect = sqlSelect + " and ws.type='" + type + "' ";
			}
			if ("-1".equals(typeName) == false) {
				sqlSelect = sqlSelect + " and ws.typeName='" + typeName + "' ";
			}
			if ("-1".equals(model) == false) {
				sqlSelect = sqlSelect + " and ws.model='" + model + "' ";
			}
			List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
			lessonList=SupplyDAO.SelectSupplyDataBySelect(page, limit,sqlSelect);
			JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
			int count =SupplyDAO.SelectSupplyDataCount();
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			 map.put("code", 0);
			 map.put("msg", "");
			 map.put("count",count); //总数量   影响分页部件
			 map.put("data", array);
			 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}	
	
	//增加物资类型
			public static JSONObject AddSupply(String type,String typeName,String model,String number,String note,String userId) throws Exception{
					Map<String, Object> map = new LinkedHashMap<String, Object>();	
					int count=SupplyDAO.SearchFieldCount(type,typeName,model);
					if(count!=0){
						 map.put("code", 10);
						 map.put("msg", "物资名称和名类型存在重复 ,插入失败");
					}
					else{
						SupplyDAO.AddSupply(type,typeName, model, number, note, userId);
						map.put("code", 200);
						map.put("msg", "插入成功");
					}
					JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
					return json;
				}
		//更新物资类型
			public static JSONObject UpdateSupplyData(String type,String typeName,String model,String number,String note,String userId,String id) throws Exception{
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				//获取原来的物资类型名称
				String oldType=null;
				String oldTypeName=null;
				String oldModel=null;
				
				oldType=SupplyDAO.SearchFieldById("type", id);
				oldTypeName=SupplyDAO.SearchFieldById("typeName", id);
				oldModel=SupplyDAO.SearchFieldById("model", id);
				if(oldType.equals(type)&&oldTypeName.equals(typeName)&&oldModel.equals(model)){
					//是本身
					//不进行 操作
					SupplyDAO.UpdateSupplyData(type,typeName, model, number, note, userId, id);
					map.put("code", 200);
					map.put("msg", "更新成功");
				}
				else{
					//不是本身
					int count=SupplyDAO.SearchFieldCount(type,typeName, model);
					System.out.println("不是本身但是和别人重复");
					if(count!=0){
						map.put("code", 10);
						map.put("msg", "物资 类型 名称 型号重复 ,更新失败");
					}
					else{
						SupplyDAO.UpdateSupplyData(type,typeName, model, number, note, userId, id);
						map.put("code", 200);
						map.put("msg", "更新成功");
						}
					}
				JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}
			//禁用物资类型
					public static JSONObject DelSupply(String userId,String id) throws Exception{
						Map<String, Object> map = new LinkedHashMap<String, Object>();
								SupplyDAO.DelSupply(userId, id);
								map.put("code", 200);
								map.put("msg", "禁用成功");
						JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
						return json;
					}
	//获取物资类型
		public static JSONObject SelectSupplyAllDataByMethods(String keywords) throws Exception{
						 System.out.println("keywords: "+keywords);
						if("typeName".equals(keywords)==true){
							keywords="typeName";
						}
						else if("model".equals(keywords)==true)
						{
							keywords="model";
						}
						else if("type".equals(keywords)==true){
							keywords="type";
						}
						else{
							System.out.println("本次key为："+keywords);
							keywords="id";
						}
						List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
						lessonList=SupplyDAO.SelectSupplyAllDataByMethods(keywords);
						JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
						Map<String, Object> map = new LinkedHashMap<String, Object>();	
						 map.put("code", 0);
						 map.put("msg", "");
						 map.put("data", array);
						 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
						return json;
					}
		
		//根据物资类型获取物资类型名称
				public static JSONObject selectSupplytypeNameDataByType(String type) throws Exception{
					List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
				lessonList=SupplyDAO.selectSupplytypeNameDataByType(type);
				JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
								 map.put("code", 0);
								 map.put("msg", "");
								 map.put("data", array);
								 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
								return json;
							}
			//selectSupplyNameDataByType
				public static JSONObject selectSupplyNameDataByType(String type) throws Exception{
					List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
				lessonList=SupplyDAO.selectSupplyNameDataByType(type);
				JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
								 map.put("code", 0);
								 map.put("msg", "");
								 map.put("data", array);
								 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
								return json;
							}	
			
				//selectSupplyNameDataByType
				public static JSONObject selectSupplyModelDataByTypeName(String typeName) throws Exception{
					List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
				lessonList=SupplyDAO.selectSupplyModelDataByTypeName(typeName);
				JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
								 map.put("code", 0);
								 map.put("msg", "");
								 map.put("data", array);
								 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
								return json;
							}	
			
	
}
