package com.xinhai.backSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.DictionaryEntity;
import com.xinhai.backSystem.entity.Loginfo;
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
public class DictionaryDAO {
	//获取字典中的所有类型
	//GetDictTypeList
	public static  List<DictionaryEntity>GetDictTypeList()throws Exception{
		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
		lessonList=DictionarySql.GetDictTypeList();
		return lessonList;
	}
	//查询字段值
	public static String SearchDictionaryDataByName(String name) throws Exception {
		String dictValue=DictionarySql.SearchDictionaryDataByName( name);
		return dictValue;
	}
	
	
	//获取对应type的list
	public static  List<DictionaryEntity>GetTypeList(String type)throws Exception{
			List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
			lessonList=DictionarySql.GetTypeList(type);
			return lessonList;
		}
	
	
	//获取地区表
	public static  List<DictionaryEntity>GetPositionList()throws Exception{
		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
		lessonList=DictionarySql.GetPositionList();
		return lessonList;
	}
	
	
	
	//根据id查询对应的信息
	public static  List<DictionaryEntity>SearchDictionaryListDataById(String id)throws Exception{
		List<DictionaryEntity> lessonList = new ArrayList<DictionaryEntity>();
		lessonList=DictionarySql.SearchDictionaryListDataById(id);
		return lessonList;
		}
	
	
	//查询该类型的下的字典值
	public static int SearchFildByValueOrName(String type,String Filed,String content) throws Exception {
		int count=DictionarySql.SearchFildByValueOrName(  type, Filed, content);
		return count;
	}
	
	
	//增加字典内容
		public static void AddDict(String type,String value,String name,String note,String userId) throws Exception {
				DictionarySql.AddDict(  type, value, name, note,userId);
				LogDAO.Addlog(userId, "添加字典,字典类型:" + type + " 字典值： " + value+" 内容："+name);
			}
		//更新字典
		//updateDict
		public static void updateDict(String type,String value,String name,String note,String userId,String id) throws Exception {
			DictionarySql.updateDict( type, value, name, note, userId, id);
			LogDAO.Addlog(userId, "更新字典,字典类型:" + type + " 字典值： " + value+" 内容："+name+" id:"+id);
		}
		//禁用
		public static void DelDict(String userId,String id) throws Exception {
			DictionarySql.DelDict(id);
			LogDAO.Addlog(userId, "禁用字典,物资类型id:"+id);
		}
//	
	
	//字典列表SelectMemberListData
	public static  List<DictionaryEntity>SearchDictionaryListData(int page,int limit)throws Exception{
	List<DictionaryEntity> lessonList = new ArrayList<DictionaryEntity>();
	lessonList=DictionarySql.SearchDictionaryListData(page,limit);
	return lessonList;
	}
	
// 字典的内容数量
	public static int SearchDictionaryListDataCount()throws Exception{
		int count=DictionarySql.SearchDictionaryListDataCount();
		return count;
		}
	
	
}
