package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.UserInfo;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:23:31
* @ClassName 类名称
* @Description 类描述
*/
public class LoginSql {
	
public static List<UserInfo> LoginValidation(String workerNumber,String password) throws SQLException {
		
		List<UserInfo> lessonList= new ArrayList<UserInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT wu.id,wu.permissions,wu.state ,wu.userName,wu.workNumber FROM wz_user wu where wu.workNumber='"+workerNumber+"' and wu.password='"+password+"'  and  wu.state='1'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInfo ui= new UserInfo();
				ui.setId(rs.getInt(1));
				ui.setPermissions(rs.getInt(2));
				ui.setState(rs.getInt(3));
				ui.setUserName(rs.getString(4));
				ui.setWorkNumber(rs.getString(5));
				lessonList.add(ui);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}
	

}
