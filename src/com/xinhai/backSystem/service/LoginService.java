package com.xinhai.backSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.dao.LoginDAO;
import com.xinhai.backSystem.entity.UserInfo;





/**
* @author Tony
* @version 创建时间：2018年9月5日 上午8:42:21
* @ClassName 类名称
* @Description 类描述
*/
//实现业务逻辑

public class LoginService {

	//登入获取用户信息
	public static List<UserInfo> LoginValidation(String workerNumber,String password) throws Exception{
		List<UserInfo> lessonList= new ArrayList<UserInfo>();
		lessonList=LoginDAO.LoginValidation(workerNumber, password);
		return lessonList;
	}	
	
	
}
