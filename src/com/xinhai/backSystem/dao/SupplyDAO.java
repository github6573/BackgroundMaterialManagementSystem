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
public class SupplyDAO {
	//物资类型列表的查询
	public static List<SupplyInfo> SelectSupplyData(int page,int limit) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplySql.SelectSupplyData(page,limit);
		return lessonList;
	}
	
	
	//物资类型列表的查询根据查询条件
	public static List<SupplyInfo> SelectSupplyDataBySelect(int page,int limit,String sqlSelect) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplySql.SelectSupplyDataBySelect(page,limit,sqlSelect);
		return lessonList;
	}
	//物资类型列表数量的查询
	public static int SelectSupplyDataCount()throws Exception{
		int count=0;
		count=SupplySql.SelectSupplyDataCount();
		return count;
	}
	//增加物资
	public static void AddSupply(String type,String typeName,String model,String number,String note,String userId) throws Exception {
			SupplySql.AddSelectSupplyData(type,typeName, model, number, note, userId);
			LogDAO.Addlog(userId, "添加物资,物资名称:" + typeName + " 物资型号： " + model+" 数量："+number);
		}
	
	//更新物资类型
	//UpdateSupplyTypeData
	public static void UpdateSupplyData(String type,String typeName,String model,String number,String note,String userId,String id) throws Exception {
		SupplySql.UpdateSupplyData(type,typeName, model, number, note, userId, id);
		LogDAO.Addlog(userId, "更新物资,物资名称:" + typeName + " 物资型号： " + model+" 物资数量： "+number);
	}
	//禁用
	public static void DelSupply(String userId,String id) throws Exception {
		SupplySql.DelSupplyData(id);
		LogDAO.Addlog(userId, "禁用物资类型,物资类型id:"+id);
	}
	
	//检测验重
//	SearchFieldCount
	//判断物资名称是否重复
	public static int SearchFieldCount(String type,String typeName,String model) throws Exception{
		int count=0;
		count =SupplySql.SearchFieldCount(type,typeName,model);
		return count;
	}
	
	
	//根据id 获取内容
	public static String SearchFieldById(String methods,String id) throws Exception{
		String File=null;
		File =SupplySql.SearchFieldById(methods, id);
		return File;
	}
	
	//获取当前物资列表根绝方法
	//SelectSupplyTypeAllDataByMethods
	public static List<SupplyInfo> SelectSupplyAllDataByMethods(String methods) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplySql.SelectSupplyAllDataByMethods(methods);
		return lessonList;
	}
	
	//根据物资类型 获取对应的物资名称列表
	//selectSupplytypeNameDataByType
	public static List<SupplyInfo> selectSupplytypeNameDataByType(String type) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplySql.selectSupplytypeNameDataByType(type);
		return lessonList;
	}
	
	
	//selectSupplyNameDataByType
	public static List<SupplyInfo> selectSupplyNameDataByType(String type) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplySql.selectSupplyNameDataByType(type);
		return lessonList;
	}
	//selectSupplyModelDataByTypeName
	public static List<SupplyInfo> selectSupplyModelDataByTypeName(String typeName) throws Exception{
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		lessonList=SupplySql.selectSupplyModelDataByTypeName(typeName);
		return lessonList;
	}
	
}
