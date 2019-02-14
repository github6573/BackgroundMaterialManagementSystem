package com.xinhai.backSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.DictionaryEntity;
import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.entity.MaterialuserecordEntity;
import com.xinhai.backSystem.entity.MemberEntity;
import com.xinhai.backSystem.entity.UserInfo;
import com.xinhai.backSystem.sql.DictionarySql;
import com.xinhai.backSystem.sql.LogSql;
import com.xinhai.backSystem.sql.MaterialUseRecordSql;
import com.xinhai.backSystem.sql.MemberSql;
import com.xinhai.backSystem.sql.SuppliesSql;
import com.xinhai.backSystem.sql.SupplySql;
import com.xinhai.backSystem.sql.UserSql;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:27:48
* @ClassName 类名称
* @Description 类描述
*/
public class UseRecordDAO {
	//获取所有的使用组别记录
	//GetGroupList
			public static  List<DictionaryEntity>GetGroupList()throws Exception{
				List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
				lessonList=MaterialUseRecordSql.GetGroupList();
				return lessonList;
			}
	//更新
	//updateDict
	public static void UpdateUseRecord(String nameSelect,String number,String unit,String nameId,String note,String userId,String id) throws Exception {
		MaterialUseRecordSql.UpdateUseRecord( nameSelect,number,unit,nameId,note,userId, id);
		LogDAO.Addlog(userId, "更新使用记录:" + nameSelect + " 数量： " + number+" 用户id："+nameId);
	}
	
	//增加使用记录
	public static void AddUseRecord(String nameSelect,String number,String unit,String nameId,String note,String userId) throws Exception {
			MaterialUseRecordSql.AddUseRecord(nameSelect,number,unit,nameId,note,userId);
			LogDAO.Addlog(userId, "添加使用记录:" + nameSelect + " 数量： " + number+" 用户id："+nameId);
		}
	
	//获取所有的物资名称
	//SearchMaterialUseRecordNameList
		public static  List<MaterialuserecordEntity>SearchMaterialUseRecordNameList()throws Exception{
			List<MaterialuserecordEntity> lessonList= new ArrayList<MaterialuserecordEntity>();
			lessonList=MaterialUseRecordSql.SearchMaterialUseRecordNameList();
			return lessonList;
		}
	
	//使用记录列表SelectMemberListData
	public static  List<MaterialuserecordEntity>SearchUseRecordListData(String SelectSql)throws Exception{
		List<MaterialuserecordEntity> lessonList= new ArrayList<MaterialuserecordEntity>();
		lessonList=MaterialUseRecordSql.SearchMaterialUseRecordSql(SelectSql);
		return lessonList;
	}
	
	//数量
		public static int SearchUseRecordListDataCount(String SelectSql)throws Exception{
			int count=MaterialUseRecordSql.SearchUseRecordListDataCount(SelectSql);
			return count;
	}
		
	
	
	
	//禁用
	public static void DelUseRecord(String userId,String id) throws Exception {
		MaterialUseRecordSql.DelUseRecord(id);
		LogDAO.Addlog(userId, "消除使用记录,记录id:"+id);
	}
//	//根据id查询对应的信息
//	public static  List<DictionaryEntity>SearchDictionaryListDataById(String id)throws Exception{
//		List<DictionaryEntity> lessonList = new ArrayList<DictionaryEntity>();
//		lessonList=DictionarySql.SearchDictionaryListDataById(id);
//		return lessonList;
//		}
//	
//	
//	//查询该类型的下的字典值
//	public static int SearchFildByValueOrName(String type,String Filed,String content) throws Exception {
//		int count=DictionarySql.SearchFildByValueOrName(  type, Filed, content);
//		return count;
//	}
//	
//	
//	//增加字典内容
//		public static void AddDict(String type,String value,String name,String note,String userId) throws Exception {
//				DictionarySql.AddDict(  type, value, name, note,userId);
//				LogDAO.Addlog(userId, "添加字典,字典类型:" + type + " 字典值： " + value+" 内容："+name);
//			}
//		//更新字典
//		//updateDict
//		public static void updateDict(String type,String value,String name,String note,String userId,String id) throws Exception {
//			DictionarySql.updateDict( type, value, name, note, userId, id);
//			LogDAO.Addlog(userId, "更新字典,字典类型:" + type + " 字典值： " + value+" 内容："+name+" id:"+id);
//		}
//		//禁用
//		public static void DelDict(String userId,String id) throws Exception {
//			DictionarySql.DelDict(id);
//			LogDAO.Addlog(userId, "禁用字典,物资类型id:"+id);
//		}
////	
//	
//	//字典列表SelectMemberListData
//	public static  List<DictionaryEntity>SearchDictionaryListData()throws Exception{
//	List<DictionaryEntity> lessonList = new ArrayList<DictionaryEntity>();
//	lessonList=DictionarySql.SearchDictionaryListData();
//	return lessonList;
//	}
	
// 字典的内容数量
//	public static int SearchDictionaryListDataCount()throws Exception{
//		int count=DictionarySql.SearchDictionaryListDataCount();
//		return count;
//		}
//	
	
}
