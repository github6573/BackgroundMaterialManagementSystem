package com.xinhai.backSystem.dao;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.SuppliesInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.sql.SuppliesSql;
import com.xinhai.backSystem.sql.SupplySql;
import com.xinhai.backSystem.sql.SupplyTypeSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class SuppliesDAO {
	//物资类型列表的查询
	public static List<SuppliesInfo> SelectSuppliesData(int page,int limit,String SelectSql) throws Exception{
		List<SuppliesInfo> lessonList= new ArrayList<SuppliesInfo>();
		lessonList=SuppliesSql.SelectSuppliesData(page,limit,SelectSql);
		return lessonList;
	}
	
	//物资类型列表数量的查询
	public static int SelectSuppliesDataCount(String SelectSql)throws Exception{
		int count=0;
		count=SuppliesSql.SelectSuppliesDataCount(SelectSql);
		return count;
	}	
	
	//增加物资
	public static void AddSupplies(String suppliesName,String model,String receiveTime,String source, String digest,String number, String unit,String price,String money,String place,String userName,String note,String groupName,String userId) throws Exception {
			SuppliesSql.AddSuppliesData(  suppliesName, model, receiveTime, source,  digest, number,  unit, price, money, place, userName, note, groupName, userId);
			//LogDAO.Addlog(userId, "添加物资,物资名称:" + typeName + " 物资型号： " + model+" 数量："+number);
		}
	//更新物资类型
	//UpdateSupplyTypeData
	public static void UpdateSuppliesData(String suppliesName,String model,String receiveTime,String source, String digest,String number, String unit,String price,String money,String place,String userName,String note,String groupName,String userId,String id) throws Exception {
		SuppliesSql.UpdateSuppliesData( suppliesName, model, receiveTime, source,  digest, number,  unit, price, money, place, userName, note, groupName, userId, id);
		LogDAO.Addlog(userId, "更新物资,物资名称:" + suppliesName + " 物资型号： " + model+" 物资编号： "+id);
	}
	//禁用
	public static void DelSupplies(String userId,String id) throws Exception {
		SuppliesSql.DelSuppliesData(id);
		LogDAO.Addlog(userId, "禁用物资,物资类型id:"+id);
	}
//	//物资类型列表的查询根据查询条件
//	public static List<SuppliesInfo> SelectSupplyDataBySelect(int page,int limit,String sqlSelect) throws Exception{
//		List<SuppliesInfo> lessonList= new ArrayList<SuppliesInfo>();
//		lessonList=SupplySql.SelectSupplyDataBySelect(page,limit,sqlSelect);
//		return lessonList;
//	}

//	//增加物资
//	public static void AddSupply(String type,String typeName,String model,String number,String note,String userId) throws Exception {
//			SupplySql.AddSelectSupplyData(type,typeName, model, number, note, userId);
//			LogDAO.Addlog(userId, "添加物资,物资名称:" + typeName + " 物资型号： " + model+" 数量："+number);
//		}
//	
//	//更新物资类型
//	//UpdateSupplyTypeData
//	public static void UpdateSupplyData(String type,String typeName,String model,String number,String note,String userId,String id) throws Exception {
//		SupplySql.UpdateSupplyData(type,typeName, model, number, note, userId, id);
//		LogDAO.Addlog(userId, "更新物资,物资名称:" + typeName + " 物资型号： " + model+" 物资数量： "+number);
//	}
//	//禁用
//	public static void DelSupply(String userId,String id) throws Exception {
//		SupplySql.DelSupplyData(id);
//		LogDAO.Addlog(userId, "禁用物资类型,物资类型id:"+id);
//	}
//	
//	//检测验重
////	SearchFieldCount
//	//判断物资名称是否重复
//	public static int SearchFieldCount(String type,String typeName,String model) throws Exception{
//		int count=0;
//		count =SupplySql.SearchFieldCount(type,typeName,model);
//		return count;
//	}
//	
//	
//	//根据id 获取内容
//	public static String SearchFieldById(String methods,String id) throws Exception{
//		String File=null;
//		File =SupplySql.SearchFieldById(methods, id);
//		return File;
//	}
//	
//	//获取当前物资列表根绝方法
//	//SelectSupplyTypeAllDataByMethods
//	public static List<SuppliesInfo> SelectSupplyAllDataByMethods(String methods) throws Exception{
//		List<SuppliesInfo> lessonList= new ArrayList<SuppliesInfo>();
//		lessonList=SupplySql.SelectSupplyAllDataByMethods(methods);
//		return lessonList;
//	}
//	
//	//根据物资类型 获取对应的物资名称列表
//	//selectSupplytypeNameDataByType
//	public static List<SuppliesInfo> selectSupplytypeNameDataByType(String type) throws Exception{
//		List<SuppliesInfo> lessonList= new ArrayList<SuppliesInfo>();
//		lessonList=SupplySql.selectSupplytypeNameDataByType(type);
//		return lessonList;
//	}
//	
//	
//	//selectSupplyNameDataByType
//	public static List<SuppliesInfo> selectSupplyNameDataByType(String type) throws Exception{
//		List<SuppliesInfo> lessonList= new ArrayList<SuppliesInfo>();
//		lessonList=SupplySql.selectSupplyNameDataByType(type);
//		return lessonList;
//	}
//	//selectSupplyModelDataByTypeName
//	public static List<SuppliesInfo> selectSupplyModelDataByTypeName(String typeName) throws Exception{
//		List<SuppliesInfo> lessonList= new ArrayList<SuppliesInfo>();
//		lessonList=SupplySql.selectSupplyModelDataByTypeName(typeName);
//		return lessonList;
//	}
	
}
