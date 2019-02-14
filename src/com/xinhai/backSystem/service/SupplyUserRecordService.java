package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.commom.WriteExcel;
import com.xinhai.backSystem.dao.LogDAO;
import com.xinhai.backSystem.dao.LoginDAO;
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.SupplyTypeDAO;
import com.xinhai.backSystem.dao.SupplyUserRecordDAO;
import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class SupplyUserRecordService {
	//导出excel获取导出名字
		public static JSONObject getExcelpath(String tempPath) throws Exception{
			List<SupplyUserRecordInfo> lessonLists= new ArrayList<SupplyUserRecordInfo>();
			lessonLists=SupplyUserRecordDAO.SelectSupplyUserRecordAll();
			String path=WriteExcel.MakeExcelTest(lessonLists,tempPath);
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			 map.put("code", 0);
			 map.put("msg", "生成Excel成功");
			 map.put("success", true);
			 map.put("path",path);
			 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
	
	//获取物资类型列表
	public static JSONObject SelectSupplyUserRecordData(int page,int limit) throws Exception{
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		lessonList=SupplyUserRecordDAO.SelectSupplyUserRecordData(page, limit);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		int count =SupplyUserRecordDAO.SelectSupplyUserRecordDataCount();
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("count",count); //总数量   影响分页部件
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	//增加物资类型
	public static JSONObject AddSupplyUserRecordData(String department,String userName,String supplyTypeName,String model,String userId,String physicalAddress,String note1,String note2) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
				SupplyUserRecordDAO.AddSupplyUserRecordData( department, userName, supplyTypeName, model, userId, physicalAddress, note1, note2);
				map.put("code", 200);
				map.put("msg", "插入成功");
				JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
		}
//更新物资类型
	public static JSONObject UpdateSupplyUserRecordData(String department,String userName,String supplyTypeName,String model,String userId,String physicalAddress,String note1,String note2,String id) throws Exception{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("code", 200);
			map.put("msg", "更新成功");
			SupplyUserRecordDAO.UpdateSupplyUserRecordData( department, userName, supplyTypeName, model, userId, physicalAddress, note1, note2, id);
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	//禁用物资类型
			public static JSONObject DelSupplyUserRecordData(String userId,String id) throws Exception{
				Map<String, Object> map = new LinkedHashMap<String, Object>();
					SupplyUserRecordDAO.DelSupplyUserRecordData(userId, id);
						map.put("code", 200);
						map.put("msg", "禁用成功");
				JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}
	
		//获取key对应的物资列表
		//	SelectSupplyUserRecordByKeywords
			//获取物资类型列表
			public static JSONObject SelectSupplyUserRecordByKeywords(String keywords) throws Exception{
				List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
				lessonList=SupplyUserRecordDAO.SelectSupplyUserRecordByKeywords(keywords);
				JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
				 map.put("code", 0);
				 map.put("msg", "");
				 map.put("data", array);
				 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}
			
			
			//supplyUserRecordDataBySelect
			public static JSONObject supplyUserRecordDataBySelect(int page,int limit,String department,String userName ,String supplyTypeName,String model) throws Exception{
				String sqlSelect="";
				if ("-1".equals(department) == false) {
					sqlSelect = sqlSelect + " and wsur.department='" + department + "' ";
				}
				if ("-1".equals(userName) == false) {
					sqlSelect = sqlSelect + " and wsur.userName='" + userName + "' ";
				}
				if ("-1".equals(supplyTypeName) == false) {
					sqlSelect = sqlSelect + " and wsur.supplyTypeName='" + supplyTypeName + "' ";
				}
				if ("-1".equals(model) == false) {
					sqlSelect = sqlSelect + " and wsur.model='" + model + "' ";
				}
				System.out.println("sqlSelect:"+sqlSelect);
				List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
				lessonList=SupplyUserRecordDAO.supplyUserRecordDataBySelect(page,limit,sqlSelect);
				JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
				int count =SupplyUserRecordDAO.supplyUserRecordDataBySelectCount(sqlSelect);
				Map<String, Object> map = new LinkedHashMap<String, Object>();	
				 map.put("code", 0);
				 map.put("msg", "");
				 map.put("count",count); //总数量   影响分页部件
				 map.put("data", array);
				 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
				return json;
			}	
	
	
}
