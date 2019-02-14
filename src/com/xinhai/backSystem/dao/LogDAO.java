package com.xinhai.backSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.sql.LogSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class LogDAO {
	
	//日志的插入
	public static void Addlog(String userId,String logContent) throws Exception{
		LogSql.Addlog(userId, logContent);
	}
	
	//日志的查询
	public static  List<Loginfo>SearchMainLogData(int page, int limit)throws Exception{
	List<Loginfo> lessonList = new ArrayList<Loginfo>();
	lessonList=LogSql.SearchMainLogData(page, limit);
	return lessonList;
	}
	
	//日志的数量查询
		public static  int SearchtMainLogDataCount()throws Exception{
		int  count=0;
		count=LogSql.SearchtMainLogDataCount();
		return count;
		}
	
	
}
