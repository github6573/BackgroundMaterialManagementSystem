package com.xinhai.backSystem.dao;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.entity.UserInfo;
import com.xinhai.backSystem.sql.LoginSql;




/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:02
* @ClassName 类名称
* @Description 类描述
*/

//持久层 用于 操作数据库
public class LoginDAO {

	//登入验证
		public static List<UserInfo> LoginValidation(String workerNumber,String password) throws Exception{
			List<UserInfo> lessonList= new ArrayList<UserInfo>();
			lessonList=LoginSql.LoginValidation(workerNumber, password);
			return lessonList;
		}
	
	
}
