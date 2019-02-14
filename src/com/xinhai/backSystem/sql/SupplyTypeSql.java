package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.UserInfo;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:23:31
* @ClassName 类名称
* @Description 类描述
*/
public class SupplyTypeSql {
	
public static List<SupplyTypeInfo>SelectSupplyTypeData(int page,int limit) throws SQLException {
		
		List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		page=limit*(page-1);
		try {
			String sql = "SELECT * FROM wz_supplytype  wst where wst.state='1' limit "+ page + "," + limit;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SupplyTypeInfo si= new SupplyTypeInfo();
				si.setId(rs.getInt(1));
				si.setType(rs.getString(2));
				si.setTypeName(rs.getString(3));
				si.setCreateTime(rs.getTimestamp(4));
				si.setCreateId(rs.getInt(5));
				si.setUpdateTime(rs.getTimestamp(6));
				si.setUpdateId(rs.getInt(7));
				lessonList.add(si);
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
//获取物资类型数量

public static int SelectSupplyTypeDataCount() throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_supplytype  wst where wst.state='1'";
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
//根据搜索条件
public static List<SupplyTypeInfo>SelectSupplyTypeDataBySelect(int page,int limit,String sqlSelect) throws SQLException {
	
	List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	page=limit*(page-1);
	try {
		String sql = "SELECT * FROM wz_supplytype wst  where wst.state='1' "+sqlSelect+" limit "+ page + "," + limit;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			SupplyTypeInfo si= new SupplyTypeInfo();
			si.setId(rs.getInt(1));
			si.setType(rs.getString(2));
			si.setTypeName(rs.getString(3));
			si.setCreateTime(rs.getTimestamp(4));
			si.setCreateId(rs.getInt(5));
			si.setUpdateTime(rs.getTimestamp(6));
			si.setUpdateId(rs.getInt(7));
			lessonList.add(si);
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


//获取物资类型数量

public static int SelectSupplyTypeDataBySelectCount(String sqlSelect) throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql ="SELECT count(*) FROM wz_supplytype wst  where wst.state='1' "+sqlSelect;
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


//增加基础物资类型
public static void AddSelectSupplyTypeData(String type,String typeName,String createId) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "INSERT INTO `wz_supplytype`(type,typeName,createTime,createId,state)"+"VALUES('"+type+"','"+typeName+"','"+now+"','"+createId+"',1)";
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


//更新基础类型信息
public static void  UpdateSupplyTypeData(String type,String typeName,String userId,String id) throws SQLException{
	Connection conn= ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	
	try{
		String sql="UPDATE  wz_supplytype wst set type='"+type+"',typeName='"+typeName+"',updateId='"+userId+"',updateTime='"+now+"' where  wst.id="+id;
		System.out.println(sql);
		stmt.executeUpdate(sql);			
	}catch(Exception e){e.printStackTrace();
	}
}

//禁用基础类型信息
public static void  DelSupplyType(String id) throws SQLException{
	Connection conn= ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	try{
		String sql="UPDATE  wz_supplytype wst set state='0' where  wst.id="+id;
		System.out.println(sql);
		stmt.executeUpdate(sql);			
		}catch(Exception e){
			e.printStackTrace();
	}
}

//检索字段
public static int SearchFieldCount(String Filed) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	int count=0;
	try {
		String sql = "SELECT count(*) FROM wz_supplytype  wst  where wst.typeName='"+Filed+"' ";
			
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
//根据  id检索 物资类型 名称
public static String SearchTypeNameByid(String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	//int count=0;
	String typeName=null;
	
	try {
		String sql = "SELECT wst.typeName FROM wz_supplytype  wst  where wst.id='"+id+"' ";
			
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			//count=rs.getInt(1);
		typeName=rs.getString(1);
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
	return typeName;
}

//获取所有的物资类型 不分页去重
	public static List<SupplyTypeInfo>SelectSupplyTypeAllDataByMethods(String methods) throws SQLException {
	List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT distinct(wst."+methods+") FROM wz_supplytype  wst where wst.state='1' ";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			SupplyTypeInfo si= new SupplyTypeInfo();
			si.setTypeName(rs.getString(1));
			lessonList.add(si);
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

	//批量增加
	//批量增加基础物资类型
	public static void AddSelectSupplyTypeDataByStringsql(String Stringsql) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		//Timestamp now = new Timestamp(System.currentTimeMillis()); 	
		try {
			String sql = "INSERT INTO `wz_supplytype`(type,typeName,createTime,createId,state)"+"VALUES"+Stringsql;
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
