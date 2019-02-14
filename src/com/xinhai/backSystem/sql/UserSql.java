package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.UserInfo;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:23:31
* @ClassName 类名称
* @Description 类描述
*/
public class UserSql {
	
public static List<UserInfo>SelectUserData(int page,int limit) throws SQLException {
		
		List<UserInfo> lessonList= new ArrayList<UserInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		page=limit*(page-1);
		try {
			String sql = "SELECT * FROM wz_user wu where wu.state='1' limit "+ page + "," + limit;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInfo ui= new UserInfo();
				ui.setId(rs.getInt(1));
				ui.setDepartment(rs.getString(2));
				ui.setWorkNumber(rs.getString(3));
				ui.setUserName(rs.getString(5));
				ui.setCreateTime(rs.getTimestamp(6));
				ui.setPermissions(rs.getInt(7));
				ui.setState(rs.getInt(8));
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
	


//获取日志数量

public static int SelectUserDataCount() throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_user wu where wu.state='1'";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			count=rs.getInt(1);
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
	return count;
}


}
