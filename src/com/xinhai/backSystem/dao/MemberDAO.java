package com.xinhai.backSystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.Loginfo;
import com.xinhai.backSystem.entity.MemberEntity;
import com.xinhai.backSystem.entity.UserInfo;
import com.xinhai.backSystem.sql.LogSql;
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
public class MemberDAO {
	//员工名称列表SelectMemberListData
	public static  List<MemberEntity>SearchListData(String type)throws Exception{
	List<MemberEntity> lessonList = new ArrayList<MemberEntity>();
	lessonList=MemberSql.SearchListData(type);
	return lessonList;
	}
	

	//员工表单
	public static  List<MemberEntity>SelectMemberData(int page, int limit,String SelectSql)throws Exception{
	List<MemberEntity> lessonList = new ArrayList<MemberEntity>();
	lessonList=MemberSql.SelectMemberData(page, limit,SelectSql);
	return lessonList;
	}
	
	//用户列表数量
		public static  int SelectMemberDataCount(String SelectSql)throws Exception{
		int  count=0;
		count=MemberSql.SelectMemberDataCount(SelectSql);
		return count;
		}
	//查询工号数量
		public static  int SearchWorkNumberCount(String workNumber)throws Exception{
			int  count=0;
			count=MemberSql.SearchWorkNumberCount(workNumber);
			return count;
	}
	//增加员工信息AddMember
		public static void AddMember(String name,String workNumber,String tel,String department,String userId) throws Exception {
			MemberSql.AddMember(name,workNumber,tel,department,userId);
			LogDAO.Addlog(userId, "添加人员,员工名称:" + name + " 工号： " + workNumber);
		}	
	//根据id查询对应的员工的工号
		public static  String SearchWorkNumberById(String id)throws Exception{
			String workNumber=null;
			workNumber=MemberSql.SearchWorkNumberById(id);
			return workNumber;
	}
	//更新员工信息
		public static void UpdateMember(String name,String workNumber,String tel,String department,String userId,String id) throws Exception {
			MemberSql.UpdateMember( name, workNumber, tel, department, userId, id);
			LogDAO.Addlog(userId, "更新员工,员工名称:" + name + " 工号： " + workNumber);
		}
		//禁用
		public static void DelMember(String userId,String id) throws Exception {
			MemberSql.DelMemberData(id);
			LogDAO.Addlog(userId, "禁用人员,人员id:"+id);
		}
	
}
