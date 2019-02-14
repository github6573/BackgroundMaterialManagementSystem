package com.xinhai.backSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.entity.UserInfo;
import com.xinhai.backSystem.sql.LogSql;
import com.xinhai.backSystem.sql.UserSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class UserDAO {
	
	
//	public static void Addlog(String userId,String logContent) throws Exception{
//		LogSql.Addlog(userId, logContent);
//	}
	
	//用户列表
	public static  List<UserInfo>SelectUserData(int page, int limit)throws Exception{
	List<UserInfo> lessonList = new ArrayList<UserInfo>();
	lessonList=UserSql.SelectUserData(page, limit);
	return lessonList;
	}
	
	//用户列表数量
		public static  int SelectUserDataCount()throws Exception{
		int  count=0;
		count=UserSql.SelectUserDataCount();
		return count;
		}
	
	
}
