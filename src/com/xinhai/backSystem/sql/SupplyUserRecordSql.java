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
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;
/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:23:31
* @ClassName 类名称
* @Description 类描述
*/
public class SupplyUserRecordSql {
	//获取SelectSupplyUserRecordByKeywords  对应的  key查询 对应物资列表
	
		public static List<SupplyUserRecordInfo>SelectSupplyUserRecordByKeywords(String methods) throws SQLException {
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT distinct(wsur."+methods+") FROM wz_supplyuserrecord  wsur where wsur.state='1' ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SupplyUserRecordInfo si= new SupplyUserRecordInfo();
				si.setSupplyTypeName(rs.getString(1));
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
	//获取所有的excel物资使用记录
		
		public static List<SupplyUserRecordInfo>SelectSupplyUserRecordAll( ) throws SQLException {
			List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM wz_supplyuserrecord  wsur where wsur.state='1' ";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					SupplyUserRecordInfo wuri= new SupplyUserRecordInfo();
					wuri.setId(rs.getInt(1));
					wuri.setDepartment(rs.getString(2));
					wuri.setUserName(rs.getString(3));
					wuri.setSupplyTypeName(rs.getString(4));
					wuri.setModel(rs.getString(5));
					wuri.setCreateTime(rs.getTimestamp(6));
					wuri.setCreateId(rs.getInt(7));
					wuri.setUpdateTime(rs.getTimestamp(8));
					wuri.setUpdateId(rs.getInt(9));
					wuri.setPhysicalAddress(rs.getString(10));
					wuri.setNote1(rs.getString(11));
					wuri.setNote2(rs.getString(12));
					wuri.setState(rs.getInt(13));
					lessonList.add(wuri);
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
	
	
	
	
	public static List<SupplyUserRecordInfo>SelectSupplyUserRecordData(int page,int limit) throws SQLException {
		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		page=limit*(page-1);
		try {
			String sql = "SELECT * FROM wz_supplyuserrecord wsur where wsur.state='1' limit "+ page + "," + limit;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SupplyUserRecordInfo wuri= new SupplyUserRecordInfo();
				wuri.setId(rs.getInt(1));
				wuri.setDepartment(rs.getString(2));
				wuri.setUserName(rs.getString(3));
			//	wuri.setSupplyType(rs.getString(4));
				wuri.setSupplyTypeName(rs.getString(4));
				wuri.setModel(rs.getString(5));
				wuri.setCreateTime(rs.getTimestamp(6));
				wuri.setCreateId(rs.getInt(7));
				wuri.setUpdateTime(rs.getTimestamp(8));
				wuri.setUpdateId(rs.getInt(9));
				wuri.setPhysicalAddress(rs.getString(10));
				wuri.setNote1(rs.getString(11));
				wuri.setNote2(rs.getString(12));
				wuri.setState(rs.getInt(13));
				lessonList.add(wuri);
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

public static int SelectSupplyUserRecordDataCount() throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_supplyuserrecord wsur where wsur.state='1'";
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

//新增 
//增加物资使用记录
public static void AddSupplyUserRecordData(String department,String userName,String supplyTypeName,String model,String userId,String physicalAddress,String note1,String note2) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "INSERT INTO `wz_supplyuserrecord`(department,userName,supplyTypeName,model,createTime,createId,physicalAddress,note1,note2,state)"+"VALUES('"+department+"','"+userName+"','"+supplyTypeName+"','"+model+"','"+now+"','"+userId+"','"+physicalAddress+"','"+note1+"','"+note2+"',1)";
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

//修改  
public static void UpdateSupplyUserRecordData(String department,String userName,String supplyTypeName,String model,String userId,String physicalAddress,String note1,String note2,String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "UPDATE  wz_supplyuserrecord wsur set department='"+department+"',userName='"+userName+"',supplyTypeName='"+supplyTypeName+"',model='"+model+"',updateTime='"+now+"',updateId='"+userId+"',physicalAddress='"+physicalAddress+"',note1='"+note1+"',note2='"+note2+"' where  wsur.id="+id;
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


//删除
public static void DelSupplyUserRecordData(String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	//Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql =" UPDATE  wz_supplyuserrecord wsur set state='0' where  wsur.id="+id;
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

//
public static List<SupplyUserRecordInfo>SelectSupplyUserRecordDataNOlimit() throws SQLException {
	
	List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT  CONCAT(wsurr.department,wsurr.userName,wsurr.supplyTypeName,wsurr.model,wsurr.physicalAddress)FROM wz_supplyuserrecord   wsurr where state=1;" ;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			SupplyUserRecordInfo surri= new SupplyUserRecordInfo();
			surri.setNote1(rs.getString(1));
			lessonList.add(surri);
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
//AddSupplyUserRecordDataByStringsql
public static void AddSupplyUserRecordDataByStringsql(String Stringsql) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	try {
		String sql = "INSERT INTO `wz_supplyuserrecord`(department,userName,supplyTypeName,model,physicalAddress,note1,note2,createTime,createId,state)"+"VALUES"+Stringsql;
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



//根据搜索条件
public static List<SupplyUserRecordInfo>supplyUserRecordDataBySelect(int page,int limit,String sqlSelect) throws SQLException {
	
	List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	page=limit*(page-1);
	try {
		//SELECT * FROM wz_supplyuserrecord wsur where wsur.state='1'
		String sql = "SELECT * FROM wz_supplyuserrecord wsur  where wsur.state='1' "+sqlSelect+" limit "+ page + "," + limit;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			SupplyUserRecordInfo wuri= new SupplyUserRecordInfo();
			wuri.setId(rs.getInt(1));
			wuri.setDepartment(rs.getString(2));
			wuri.setUserName(rs.getString(3));
		//	wuri.setSupplyType(rs.getString(4));
			wuri.setSupplyTypeName(rs.getString(4));
			wuri.setModel(rs.getString(5));
			wuri.setCreateTime(rs.getTimestamp(6));
			wuri.setCreateId(rs.getInt(7));
			wuri.setUpdateTime(rs.getTimestamp(8));
			wuri.setUpdateId(rs.getInt(9));
			wuri.setPhysicalAddress(rs.getString(10));
			wuri.setNote1(rs.getString(11));
			wuri.setNote2(rs.getString(12));
			wuri.setState(rs.getInt(13));
			lessonList.add(wuri);
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

public static int supplyUserRecordDataBySelectCount(String sqlSelect) throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_supplyuserrecord wsur where wsur.state='1' "+sqlSelect;
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
