package com.xinhai.backSystem.dao;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.MainWzEntity;
import com.xinhai.backSystem.entity.SuppliesInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.sql.MainWzSql;
import com.xinhai.backSystem.sql.SuppliesSql;
import com.xinhai.backSystem.sql.SupplySql;
import com.xinhai.backSystem.sql.SupplyTypeSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class MainWzDAO {
	//使用者列表
	//SearchUserList
	public static List<MainWzEntity> SearchUserList() throws Exception{
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=MainWzSql.SearchUserList();
		return lessonList;
	}
	
	//SearchMainWzDateAllName
	//获取物资列表 整合
	public static List<MainWzEntity> SearchMainWzDateAllName() throws Exception{
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=MainWzSql.SearchMainWzDateAllName();
		return lessonList;
	}
	
	//物资类型列表的查询
	public static List<MainWzEntity> SearchMainWzDate(int page,int limit,String SelectSql) throws Exception{
		List<MainWzEntity> lessonList= new ArrayList<MainWzEntity>();
		lessonList=MainWzSql.SearchMainWzDate(page,limit,SelectSql);
		return lessonList;
	}
	
	//物资类型列表数量的查询
	public static int SearchMainWzDataCount(String SelectSql)throws Exception{
		int count=0;
		count=MainWzSql.SearchMainWzDataCount(SelectSql);
		return count;
	}	
	//增加物资
	public static void AddMainWzData(String name,String supplier, String model, String unit, String number,
			String userName, int state, String position, String note,int groupId, String userId) throws Exception {
			MainWzSql.AddMainWzData( name, supplier,model,unit,number,userName,state,position,note,groupId,userId);
		LogDAO.Addlog(userId, "添加物资,物资名称:" + name + " 物资型号： " + model+" 数量："+number);
		}
	//更新物资类型
	//UpdateSupplyTypeData
	public static void UpdateMainWzData(String name,String supplier, String model, String unit, String number,
			String userName, int state, String position, String note,int groupId, String userId, String id) throws Exception {
			MainWzSql.UpdateMainWzData(  name, supplier,model,unit,number,userName,state,position,note,groupId,userId, id);
			LogDAO.Addlog(userId, "更新物资,物资名称:" + name + " 物资型号： " + model+" 物资编号： "+id);
	}
	//禁用
	public static void DelMainWzData(String userId,String id) throws Exception {
		MainWzSql.DelMainWzData(id);
		LogDAO.Addlog(userId, "禁用物资,物资类型id:"+id);
	}
	//批量上传
	public static void BatchUploadMainWzDataByStringsql(String userId,String Stringsql) throws Exception {
			MainWzSql.BatchUploadMainWzDataByStringsql(Stringsql);
			LogDAO.Addlog(userId, "批量上传物资");
	}
	
}
