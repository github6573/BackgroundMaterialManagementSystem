package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.MainWzDAO;
import com.xinhai.backSystem.dao.MemberDAO;
import com.xinhai.backSystem.dao.SuppliesDAO;
import com.xinhai.backSystem.dao.TouristDAO;
import com.xinhai.backSystem.entity.MainWzEntity;
import com.xinhai.backSystem.entity.SuppliesInfo;

/**
* @author Tony
* @version 创建时间：2019年1月28日 上午10:34:22
* @ClassName 类名称
* @Description 类描述
*/
public class TouristService {
	//获取物资列表
	public static JSONObject SearchTouristData(int page,int limit,String suppliesName) throws Exception{
		String SelectSql="";
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=TouristDAO.SearchTouristData(page, limit, SelectSql);
		int count =TouristDAO.SearchTouristDataCount(SelectSql);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		map.put("code", 0);
		map.put("data", array);
		 map.put("count",count); //总数量   影响分页部件
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
}
