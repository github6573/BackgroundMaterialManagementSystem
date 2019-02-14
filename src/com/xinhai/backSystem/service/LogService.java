package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.LogDAO;
import com.xinhai.backSystem.entity.Loginfo;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:22:05
* @ClassName 类名称
* @Description 类描述
*/
public class LogService {

	   //日志的插入
		//管理日志的记录
		public static void Addlog(String userId,String logContent) throws Exception{
			LogDAO.Addlog(userId, logContent);
		}
		
		//获取日志列表
				public static JSONObject SearchMainLogData(int page,int limit) throws Exception{
					List<Loginfo> lessonList= new ArrayList<Loginfo>();
					lessonList=LogDAO.SearchMainLogData(page, limit);
					JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
					int count =LogDAO.SearchtMainLogDataCount();
					//String str = JSON.toJSONString(lessonList); // List转json
					Map<String, Object> map = new LinkedHashMap<String, Object>();	
					 map.put("code", 0);
					 map.put("msg", "");
					 map.put("count",count); //总数量   影响分页部件
					 map.put("data", array);
					 	JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
					//map
					return json;
				}	
}
