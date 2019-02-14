package com.xinhai.backSystem.service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.SupplyTypeDAO;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class SupplyTypeService {
	//获取物资类型列表
	public static JSONObject SelectSupplyTypeData(int page,int limit) throws Exception{
		List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
		lessonList=SupplyTypeDAO.SelectSupplyTypeData(page, limit);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		int count =SupplyTypeDAO.SelectSupplyTypeDataCount();
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("count",count); //总数量   影响分页部件
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
	//根据要求获取物资类型列表
			public static JSONObject SelectSupplyTypeDataBySelect(int page,int limit,String type,String typeName) throws Exception{
				String sqlSelect="";
				if ("-1".equals(type) == false) {
					sqlSelect = sqlSelect + " and wst.type='" + type + "' ";
				}
				if ("-1".equals(typeName) == false) {
					sqlSelect = sqlSelect + " and wst.typeName='" + typeName + "' ";
				}
				List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
				lessonList=SupplyTypeDAO.SelectSupplyTypeDataBySelect(page, limit,sqlSelect);
				JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
				int count =SupplyTypeDAO.SelectSupplyTypeDataBySelectCount(sqlSelect);
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
				 map.put("code", 0);
				 map.put("msg", "");
				 map.put("count",count); //总数量   影响分页部件
				 map.put("data", array);
				 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}	
	//增加物资类型
		public static JSONObject AddSupplyType(String type,String typeName,String createId) throws Exception{
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
				int count=SupplyTypeDAO.SearchFieldCount(typeName);
				if(count!=0){
					 map.put("code", 10);
					 map.put("msg", "物资名称重复 ,插入失败");
				}
				else{
					SupplyTypeDAO.AddSupplyType(type, typeName, createId);
					map.put("code", 200);
					map.put("msg", "插入成功");
				}
				JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}
	//更新物资类型
		public static JSONObject UpdateSupplyTypeData(String type,String typeName,String userId,String id) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			//获取原来的物资类型名称
			String oldTypeName=null;
			oldTypeName=SupplyTypeDAO.SearchTypeNameByid(id);
			if(oldTypeName.equals(typeName)){
				//是本身
				SupplyTypeDAO.UpdateSupplyTypeData(type, oldTypeName, userId, id);
				map.put("code", 200);
				map.put("msg", "更新成功");
			}
			else{
				//不是本身
				int count=SupplyTypeDAO.SearchFieldCount(typeName);
				if(count!=0){
					map.put("code", 10);
					map.put("msg", "物资名称重复 ,更新失败");
				}
				else{
					SupplyTypeDAO.UpdateSupplyTypeData(type,typeName, userId, id);
					map.put("code", 200);
					map.put("msg", "更新成功");
					}
				}
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
		
		
		//禁用物资类型
				public static JSONObject DelSupplyType(String userId,String id) throws Exception{
					Map<String, Object> map = new LinkedHashMap<String, Object>();
							SupplyTypeDAO.DelSupplyType(userId, id);
							map.put("code", 200);
							map.put("msg", "禁用成功");
					JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
					return json;
				}
		//获取物资类型
				public static JSONObject SelectSupplyTypeAllDataByMethods(String keywords) throws Exception{
					 System.out.println("keywords: "+keywords);
					if("typeName".equals(keywords)==true){
						keywords="typeName";
					}
					else if("type".equals(keywords)==true)
					{
						keywords="type";
					}
					else{
						keywords="id";
					}
					List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
					lessonList=SupplyTypeDAO.SelectSupplyTypeAllDataByMethods(keywords);
					JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
					Map<String, Object> map = new LinkedHashMap<String, Object>();	
					 map.put("code", 0);
					 map.put("msg", "");
					 map.put("data", array);
					 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
					return json;
				}			
	
}
