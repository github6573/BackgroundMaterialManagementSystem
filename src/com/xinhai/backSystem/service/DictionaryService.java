package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.DictionaryDAO;
import com.xinhai.backSystem.dao.LoginDAO;
import com.xinhai.backSystem.dao.MemberDAO;
import com.xinhai.backSystem.dao.SuppliesDAO;
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.UseRecordDAO;
import com.xinhai.backSystem.dao.UserDAO;
import com.xinhai.backSystem.entity.DictionaryEntity;
import com.xinhai.backSystem.entity.MemberEntity;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.UserInfo;
import com.xinhai.backSystem.sql.DictionarySql;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class DictionaryService {
	//获取字典中的所有类型
	//GetDictTypeList
	public static JSONObject GetDictTypeList() throws Exception{
		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
		lessonList=DictionaryDAO.GetDictTypeList();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	//获取各个字段的表
	public static JSONObject GetTypeList(String type) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
		String 	condition="";
		if(null !=type  && !type.equals(""))
			System.out.println("type:"+type);
		switch (type) {
			
		case "state":
			condition=type;	
			lessonList=DictionaryDAO.GetTypeList(condition);
		break;
		case "position":
			condition=type;	
			lessonList=DictionaryDAO.GetTypeList(condition);
		break;
		default:
			 map.put("msg", "type非法");
		break;
		}
			
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	
	
	public static JSONObject GetPositionList() throws Exception{
		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
		lessonList=DictionaryDAO.GetPositionList();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	
	
	//禁用
		public static JSONObject DelDict(String userId,String id) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();
				DictionaryDAO.DelDict(userId, id);
					map.put("code", 200);
					map.put("msg", "删除成功");
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
	
	//更新
		public static JSONObject UpdateDict(String type,String value,String name,String note,String userId,String id) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
			lessonList=DictionaryDAO.SearchDictionaryListDataById(id);
			//优先判断type 是否改变
			String oldtype=lessonList.get(0).getType();
			String oldValue=lessonList.get(0).getValue();
			if(oldtype.equals(type)){
				//type没有改变
				//value是否重复    
				if(oldValue.equals(value)==true){
					 //是value 不变   name可重复  不进行检验 直接更新
					 map.put("msg", "更新成功");
					DictionaryDAO.updateDict( type, value, name,note, userId,id);				
				}
				else{
					//value发生改变
					int valueCount=DictionaryDAO.SearchFildByValueOrName(type, "value", value);
					if(valueCount==0){
						//该type下value没有该值
						 DictionaryDAO.updateDict( type, value, name,note, userId,id);
						 map.put("code", 200);
						 map.put("msg", "更新成功");
					}
					else if(valueCount==1){
						//DictionaryDAO.updateDict( type, value, name,note, userId,id);
						map.put("code", 10);
						map.put("msg", "字段数据关键值存在重复 ,更新失败");
					}
					else{
						 map.put("code", 10);
						 map.put("msg", "字段数据关键值存在异常 ,更新失败");
					}
				}
				
			}
			else{
				//type改变
				//value发生改变
				int valueCount=DictionaryDAO.SearchFildByValueOrName(type, "value", value);
				if(valueCount==0){
					//该type下value没有该值
					 DictionaryDAO.updateDict( type, value, name,note, userId,id);
					 map.put("code", 200);
					 map.put("msg", "更新成功");
				}
				else if(valueCount==1){
					//DictionaryDAO.updateDict( type, value, name,note, userId,id);
					map.put("code", 10);
					map.put("msg", "字段数据关键值存在重复 ,更新失败");
				}
				else{
					 map.put("code", 10);
					 map.put("msg", "字段数据关键值存在异常 ,更新失败");
				}
				
				
			}
			
		
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
		
	
	
	//增加字典
			public static JSONObject AddDict(String type,String value,String name,String note,String userId) throws Exception{
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
				int valueCount=DictionaryDAO.SearchFildByValueOrName(type, "value", value);
				int nameCount=DictionaryDAO.SearchFildByValueOrName(type, "name", name);
				if(valueCount==0&&nameCount==0){
					DictionaryDAO.AddDict( type, value, name,note, userId);
					 map.put("code", 200);
					 map.put("msg", "插入成功");
				}
				else if(valueCount==1||nameCount==1){
					//DictionaryDAO.AddDict( type, value, name,note, userId);
					map.put("code", 10);
					map.put("msg", "字段数据关键值存在重复 ,插入失败");
				}
				else{
					 map.put("code", 10);
					 map.put("msg", "字段数据关键值存在异常 ,插入失败");
				}
				JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}
	
	
	//异常
		public static JSONObject error() throws Exception{
			
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			 map.put("code", 0);
			 map.put("msg", "字典数据操作异常");
			 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
	
	public static JSONObject SearchDictionaryData(int page,int limit) throws Exception{
		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
		lessonList=DictionaryDAO.SearchDictionaryListData(page,limit);
		int count = DictionaryDAO.SearchDictionaryListDataCount();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("count", count);
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	
		
	
	
}
