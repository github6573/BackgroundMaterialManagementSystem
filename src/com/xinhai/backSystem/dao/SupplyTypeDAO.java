package com.xinhai.backSystem.dao;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.sql.SupplySql;
import com.xinhai.backSystem.sql.SupplyTypeSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class SupplyTypeDAO {
	
	//物资类型列表的查询根据查询条件
	public static List<SupplyTypeInfo> SelectSupplyTypeDataBySelect(int page,int limit,String sqlSelect) throws Exception{
		List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
		lessonList=SupplyTypeSql.SelectSupplyTypeDataBySelect(page,limit,sqlSelect);
		return lessonList;
	}
	//物资类型列表数量的查询
	public static int SelectSupplyTypeDataBySelectCount(String sqlSelect)throws Exception{
	int count=0;
	count=SupplyTypeSql.SelectSupplyTypeDataBySelectCount(sqlSelect);
	return count;
	}
	
	
	
	//物资类型列表的查询
	public static List<SupplyTypeInfo> SelectSupplyTypeData(int page,int limit) throws Exception{
		List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
		lessonList=SupplyTypeSql.SelectSupplyTypeData(page,limit);
		return lessonList;
	}
	//物资类型列表数量的查询
	public static int SelectSupplyTypeDataCount()throws Exception{
	int count=0;
	count=SupplyTypeSql.SelectSupplyTypeDataCount();
	return count;
	}
	//增加物资类型
	public static void AddSupplyType(String type,String typeName,String createId) throws Exception {
			SupplyTypeSql.AddSelectSupplyTypeData(type, typeName, createId);
			LogDAO.Addlog(createId, "添加物资类型,物资类型:" + type + " 物资名称： " + typeName);
		}
	
	//更新物资类型
	//UpdateSupplyTypeData
	public static void UpdateSupplyTypeData(String type,String typeName,String userId,String id) throws Exception {
		SupplyTypeSql.UpdateSupplyTypeData(type, typeName, userId, id);
		LogDAO.Addlog(userId, "更新物资类型,物资类型:" + type + " 物资名称： " + typeName);
	}
	//禁用
	public static void DelSupplyType(String userId,String id) throws Exception {
		SupplyTypeSql.DelSupplyType(id);
		LogDAO.Addlog(userId, "禁用物资类型,物资类型id:"+id);
	}
	
	//判断物资名称是否重复
			public static int SearchFieldCount(String typeName) throws Exception{
				int count=0;
				count =SupplyTypeSql.SearchFieldCount(typeName);
				return count;
			}
	//根据id搜索物资类型名称
			public static String SearchTypeNameByid(String id) throws Exception{
				String typeName=null;
				typeName =SupplyTypeSql.SearchTypeNameByid(id);
				return typeName;
			}
	//获取当前物资列表
			//SelectSupplyTypeAllDataByMethods
			public static List<SupplyTypeInfo> SelectSupplyTypeAllDataByMethods(String methods) throws Exception{
				List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
				lessonList=SupplyTypeSql.SelectSupplyTypeAllDataByMethods(methods);
				return lessonList;
			}
	//
			
}
