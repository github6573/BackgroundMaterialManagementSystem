package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.LoginDAO;
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.UserDAO;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.UserInfo;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class UserService {
//用户的列表获取
	public static JSONObject SelectUserData(int page,int limit) throws Exception{
		List<UserInfo> lessonList= new ArrayList<UserInfo>();
		lessonList=UserDAO.SelectUserData(page, limit);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		int count =UserDAO.SelectUserDataCount();
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("count",count); //总数量   影响分页部件
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}	
	
	
}
