package com.xinhai.backSystem.dao;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.xinhai.backSystem.entity.MainWzEntity;
import com.xinhai.backSystem.entity.SuppliesInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.sql.MainWzSql;
import com.xinhai.backSystem.sql.SuppliesSql;
import com.xinhai.backSystem.sql.SupplySql;
import com.xinhai.backSystem.sql.SupplyTypeSql;
import com.xinhai.backSystem.sql.TouristSql;
import com.xinhai.backSystem.sql.UserSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class TouristDAO {
	//使用者列表
	//SearchUserList
	public static List<MainWzEntity> SearchTouristData(int page,int limit,String SelectSql) throws Exception{
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=TouristSql.SearchTouristData(page, limit, SelectSql);
		return lessonList;
	}
	//SearchTouristDataCount
	
	//用户列表数量
		public static  int SearchTouristDataCount(String SelectSql)throws Exception{
		int  count=0;
		count=TouristSql.SearchTouristDataCount(SelectSql);
		return count;
		}
	
	
}
