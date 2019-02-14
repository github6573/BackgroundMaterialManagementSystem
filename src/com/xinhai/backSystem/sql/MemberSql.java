package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.MemberEntity;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.UserInfo;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:23:31
* @ClassName 类名称
* @Description 类描述
*/
public class MemberSql {
	//	人员禁用

public static void DelMemberData(String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	//Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql =" UPDATE  wz_member wm set state='0' where  wm.id="+id;
		System.out.println(sql);
		stmt.execute(sql);
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
	
	//获取各种列表   SelectMemberListData
	public static List<MemberEntity> SearchListData(String type) throws SQLException {
		List<MemberEntity> lessonList= new ArrayList<MemberEntity>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT wm."+type+" ,wm.id FROM wz_member wm  where wm.state='1' group by wm."+type;
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberEntity me= new MemberEntity();
				me.setExtraField(rs.getString(1));	
				me.setId(rs.getInt(2));
				lessonList.add(me);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lessonList;
	}
	
	
	
	
	
	//根据id查询对应的工号
	public static String SearchWorkNumberById(String id) throws SQLException {
		String workNumber="";
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT wm.workNumber  FROM wz_member wm  where wm.state='1' and wm.id='"+id+"'";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				workNumber=rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return workNumber;
	}
	
	
	
	
	
public static List<MemberEntity>SelectMemberData(int page,int limit,String SelectSql) throws SQLException {
		
		List<MemberEntity> lessonList= new ArrayList<MemberEntity>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		page=limit*(page-1);
		try {
			String sql = "SELECT * FROM wz_member wm  where wm.state='1' "+SelectSql+" limit "+ page + "," + limit;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberEntity me= new MemberEntity();
				me.setId(rs.getInt(1));
				me.setName(rs.getString(2));
				me.setWorkNumber(rs.getString(3));
				me.setTel(rs.getString(4));
				me.setDepartment(rs.getString(5));
				me.setState(rs.getInt(10));
				lessonList.add(me);
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

public static int SelectMemberDataCount(String SelectSql) throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_member wm  where wm.state='1'   "+SelectSql;
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


//SearchWorkNumberCount 查询工号的数量
public static int SearchWorkNumberCount(String workNumber) throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_member wm  where wm.state='1' and wm.workNumber='"+workNumber+"'";
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
			e.printStackTrace();
		}
	}
	return count;
}

//添加员工
public static void AddMember(String name,String workNumber,String tel,String department,String userId) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "INSERT INTO `wz_member`(name,workNumber,tel,department,createTime,createId,state)"+"VALUES('"+name+"','"+workNumber+"','"+tel+"','"+department+"','"+now+"','"+userId+"',1)";
		System.out.println(sql);
		stmt.execute(sql);
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
//更新员工信息UpdateMember
public static void UpdateMember(String name,String workNumber,String tel,String department,String userId,String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "UPDATE  wz_member wm set name='"+name+"',workNumber='"+workNumber+"',tel='"+tel+"',department='"+department+"',updateTime='"+now+"',updateId='"+userId+"' where  wm.id="+id;
		System.out.println(sql);
		stmt.execute(sql);
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





}
