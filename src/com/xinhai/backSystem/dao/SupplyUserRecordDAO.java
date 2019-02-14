package com.xinhai.backSystem.dao;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.functions.Function;
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;
import com.xinhai.backSystem.sql.SupplyTypeSql;
import com.xinhai.backSystem.sql.SupplyUserRecordSql;
/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class SupplyUserRecordDAO {
	//根据key去对应的列表
	//SelectSupplyUserRecordByKeywords
	public static List<SupplyUserRecordInfo> SelectSupplyUserRecordByKeywords(String keywords) throws Exception{
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		lessonList=SupplyUserRecordSql.SelectSupplyUserRecordByKeywords(keywords);
		return lessonList;
	}
	//查询所有的 正常物资管理记录
	public static List<SupplyUserRecordInfo> SelectSupplyUserRecordAll( ) throws Exception{
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		lessonList=SupplyUserRecordSql.SelectSupplyUserRecordAll();
		return lessonList;
	}
	
	
	//物资类型列表的查询
	public static List<SupplyUserRecordInfo> SelectSupplyUserRecordData(int page,int limit) throws Exception{
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		lessonList=SupplyUserRecordSql.SelectSupplyUserRecordData(page,limit);
		return lessonList;
	}
	//物资类型列表数量的查询
	public static int SelectSupplyUserRecordDataCount()throws Exception{
	int count=0;
	count=SupplyUserRecordSql.SelectSupplyUserRecordDataCount();
	return count;
	}
	//增加物资使用记录
		public static void AddSupplyUserRecordData(String department,String userName,String supplyTypeName,String model,String userId,String physicalAddress,String note1,String note2) throws Exception {
			SupplyUserRecordSql.AddSupplyUserRecordData( department, userName, supplyTypeName, model, userId, physicalAddress, note1, note2);
			LogDAO.Addlog(userId, "添加物资使用记录,物资名称:" +supplyTypeName  + " 物资型号： " + model +" 使用者："+userName);
			}
		
	//更新物资使用记录
		public static void UpdateSupplyUserRecordData(String department,String userName,String supplyTypeName,String model,String userId,String physicalAddress,String note1,String note2,String id) throws Exception {
			SupplyUserRecordSql.UpdateSupplyUserRecordData( department, userName, supplyTypeName, model, userId, physicalAddress, note1, note2, id);
			LogDAO.Addlog(userId, "更新物资使用记录,物资名称:" +supplyTypeName  + " 物资型号： " + model +" 使用者："+userName);
		}
	//禁用物资使用记录
	public static void DelSupplyUserRecordData(String userId,String id) throws Exception {
			SupplyUserRecordSql.DelSupplyUserRecordData(id);
			LogDAO.Addlog(userId, "禁用物资使用记录,物资类型id:"+id);
		}
	
	
	//按條件搜索物資使用記錄
	public static List<SupplyUserRecordInfo> supplyUserRecordDataBySelect(int page,int limit,String sqlSelect) throws Exception{
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		lessonList=SupplyUserRecordSql.supplyUserRecordDataBySelect(page,limit,sqlSelect);
		return lessonList;
	}
	public static int supplyUserRecordDataBySelectCount(String sqlSelect)throws Exception{
		int count=0;
		count=SupplyUserRecordSql.supplyUserRecordDataBySelectCount(sqlSelect);
		return count;
	}
	
	public static int result(int str,int i) throws Exception {
		int x=0;
		if(str==i){
			x=0;
		}
		return x;
	}
	
	public static void main(String[] args) {
		
		for(int i=0;i<10000;i++){
			int b1 = i/1000;
			int b2 = (i-b1*1000)/100;
			int b3 = (i-b1*1000-b2*100)/10;
			int b4 = (i-b1*1000-b2*100-b3*10)/1;
			//System.out.println(a+"--"+b+"---"+c+"----"+d);
			if(i*9==b4*1000+b3*100+b2*10+b1){
				System.out.println("满足条件的i："+i);
			}
		}
	}
	
	
	
}
