package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.dao.LoginDAO;
import com.xinhai.backSystem.dao.MemberDAO;
import com.xinhai.backSystem.dao.SuppliesDAO;
import com.xinhai.backSystem.dao.SupplyDAO;
import com.xinhai.backSystem.dao.UserDAO;
import com.xinhai.backSystem.entity.MemberEntity;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.UserInfo;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class MemberService {
//SelectMemberListData   员工列表
	public static JSONObject SearchListData(String type) throws Exception{
		List<MemberEntity> lessonList= new ArrayList<MemberEntity>();
		lessonList=MemberDAO.SearchListData(type);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", type+"列表");
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	
	
//异常
	public static JSONObject error() throws Exception{
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "员工数据操作异常");
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
//用户的列表获取
	public static JSONObject SelectMemberData(int page,int limit,String name,String workNumber,String tel,String department) throws Exception{		
		String SelectSql = "";
		if ("-1".equals(name) == false&&null!=name) {
			SelectSql = SelectSql + " and wm.name='" + name + "' ";
		}
		if ("-1".equals(workNumber) == false&&null!=workNumber) {
			SelectSql = SelectSql + " and wm.workNumber='" + workNumber + "' ";
		}
		if ("-1".equals(tel) == false&&null!=tel) {
			SelectSql = SelectSql + " and wm.tel='" + tel + "' ";
		}
		if ("-1".equals(department) == false&&null!=department) {
			SelectSql = SelectSql + " and wm.department='" + department + "' ";
		}

			
		List<MemberEntity> lessonList= new ArrayList<MemberEntity>();
		lessonList=MemberDAO.SelectMemberData(page, limit,SelectSql);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(lessonList));
		int count =MemberDAO.SelectMemberDataCount(SelectSql);
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		 map.put("code", 0);
		 map.put("msg", "");
		 map.put("count",count); //总数量   影响分页部件
		 map.put("data", array);
		 JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
		return json;
	}
	
	
	//用户的列表增加
		public static JSONObject AddMember(String name,String workNumber,String tel,String department,String userId) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			int count=MemberDAO.SearchWorkNumberCount(workNumber);
			if(count==1){
				 map.put("code", 10);
				 map.put("msg", "工号存在重复 ,插入失败");
			}
			else if(count==0){
				MemberDAO.AddMember(name,workNumber,tel,department,userId);
				map.put("code", 200);
				map.put("msg", "插入成功");
			}
			else{
				 map.put("code", 10);
				 map.put("msg", "工号数量异常 ,插入失败");
			}
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
//用户的增加UpdateMember
		public static JSONObject UpdateMember(String name,String workNumber,String tel,String department,String userId,String id) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();	
			String oldworkNumber=MemberDAO.SearchWorkNumberById(id);
			System.out.println("oldworkNumber:"+oldworkNumber+"workNumber:"+workNumber);
			if(oldworkNumber.equals(workNumber)==true){
				//是原来的工号
				//执行更新
				MemberDAO.UpdateMember(name,workNumber,tel,department,userId,id);
				map.put("code", 200);
				map.put("msg", "更新成功");
			}
			else{
				//不是原来的工号 判断后持续更新
				int count=MemberDAO.SearchWorkNumberCount(workNumber);
				if(count==1){
					 map.put("code", 10);
					 map.put("msg", "工号存在重复 ,更新失败");
				}
				else if(count==0){
					MemberDAO.UpdateMember(name,workNumber,tel,department,userId,id);
					map.put("code", 200);
					map.put("msg", "更新成功");
				}
				else{
					 map.put("code", 10);
					 map.put("msg", "工号数量异常 ,更新失败");
				}
			}
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
		

		//用户的删除
		//delMemberById
		//禁用物资类型
		public static JSONObject delMemberById(String userId,String id) throws Exception{
			Map<String, Object> map = new LinkedHashMap<String, Object>();
					MemberDAO.DelMember(userId, id);
					map.put("code", 200);
					map.put("msg", "删除成功");
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(map)); 
			return json;
		}
	
}
