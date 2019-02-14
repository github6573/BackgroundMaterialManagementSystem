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
public class SupplySql {
	
public static List<SupplyInfo>SelectSupplyData(int page,int limit) throws SQLException {
		
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		page=limit*(page-1);
		try {
			String sql = "SELECT * FROM wz_supply ws  where ws.state='1' limit "+ page + "," + limit;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SupplyInfo si= new SupplyInfo();
				si.setId(rs.getInt(1));
				si.setType(rs.getString(2));
				si.setTypeName(rs.getString(3));
				si.setModel(rs.getString(4));
				si.setNumber(rs.getInt(5));
				si.setCreateTime(rs.getTimestamp(6));
				si.setCreateId(rs.getInt(7));
				si.setUpdateTime(rs.getTimestamp(8));
				si.setUpdateId(rs.getInt(9));
				si.setNote(rs.getString(10));
				si.setState(rs.getInt(11));
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
//根据搜索条件
public static List<SupplyInfo>SelectSupplyDataBySelect(int page,int limit,String sqlSelect) throws SQLException {
	
	List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	page=limit*(page-1);
	try {
		String sql = "SELECT * FROM wz_supply ws  where ws.state='1' "+sqlSelect+" limit "+ page + "," + limit;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			SupplyInfo si= new SupplyInfo();
			si.setId(rs.getInt(1));
			si.setType(rs.getString(2));
			si.setTypeName(rs.getString(3));
			si.setModel(rs.getString(4));
			si.setNumber(rs.getInt(5));
			si.setCreateTime(rs.getTimestamp(6));
			si.setCreateId(rs.getInt(7));
			si.setUpdateTime(rs.getTimestamp(8));
			si.setUpdateId(rs.getInt(9));
			si.setNote(rs.getString(10));
			si.setState(rs.getInt(11));
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


//获取日志数量

public static int SelectSupplyDataCount() throws SQLException {
	int count=0;
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT count(*) FROM wz_supply ws where ws.state='1'";
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
//增加基础物资类型
public static void AddSelectSupplyData(String type,String typeName,String model,String number,String note,String userId) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "INSERT INTO `wz_supply`(type,typeName,model,number,createTime,createId,note,state)"+"VALUES('"+type+"','"+typeName+"','"+model+"','"+number+"','"+now+"','"+userId+"','"+note+"',1)";
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
public static void UpdateSupplyData(String type,String typeName,String model,String number,String note,String userId,String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "UPDATE  wz_supply ws set type='"+type+"',typeName='"+typeName+"',model='"+model+"',updateTime='"+now+"',updateId='"+userId+"',note='"+note+"' where  ws.id="+id;
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
public static void DelSupplyData(String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	//Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql =" UPDATE  wz_supply ws set state='0' where  ws.id="+id;
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
//批量上传

//检索字段
public static int SearchFieldCount(String type,String typeName,String model) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	int count=0;
	try {
		String sql = "SELECT count(*) FROM wz_supply  ws  where  ws.type='"+type+"'and ws.typeName='"+typeName+"' and ws.model='"+model+"'";
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


//按照id检索内容

public static String SearchFieldById(String methods,String id) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
//	int count=0;
	String File=null;
	try {
		String sql = "SELECT ws."+methods+" FROM wz_supply  ws  where ws.id='"+id+"' ";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			File=rs.getString(1);
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
	return File;
}

//获取所有的物资类型 不分页去重
	public static List<SupplyInfo>SelectSupplyAllDataByMethods(String methods) throws SQLException {
	List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	try {
		String sql = "SELECT distinct(ws."+methods+") FROM wz_supply  ws where ws.state='1' ";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			SupplyInfo si= new SupplyInfo();
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
//根据物资类型获取对应的数据库里存在 物资类型名称
	public static List<SupplyInfo>selectSupplytypeNameDataByType(String type) throws SQLException {
		List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT distinct(wst.typeName) FROM wz_supplytype  wst where wst.state='1' and wst.type='"+type+"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SupplyInfo si= new SupplyInfo();
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
	
	
	
	//根据物资类型获取对应的数据库里存在 物资类型名称
		public static List<SupplyInfo>selectSupplyNameDataByType(String type) throws SQLException {
			List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT distinct(ws.typeName) FROM wz_supply  ws where ws.state='1' and ws.type='"+type+"'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					SupplyInfo si= new SupplyInfo();
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
		
		
		
		//根据物资类型名称获取对应的数据库里存在 物型号
				public static List<SupplyInfo>selectSupplyModelDataByTypeName(String typeName) throws SQLException {
					List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
					Connection conn = ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					try {
						String sql = "SELECT distinct(ws.model) FROM wz_supply  ws where ws.state='1' and ws.typeName='"+typeName+"'";
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							SupplyInfo si= new SupplyInfo();
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
				
				
	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				public static List<SupplyInfo>SelectSupplyDataNOlimit() throws SQLException {
					
					List<SupplyInfo> lessonList= new ArrayList<SupplyInfo>();
					Connection conn = ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					try {
						String sql = "SELECT  CONCAT(ws.typeName,ws.model)FROM wz_supply   ws where state=1;" ;
						System.out.println(sql);
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							SupplyInfo si= new SupplyInfo();
							si.setModel(rs.getString(1));
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
				
	//批量插入
		//		AddSelectSupplyDataByStringsql
				//批量增加基础物资类型
				public static void AddSelectSupplyDataByStringsql(String Stringsql) throws SQLException {
					Connection conn = ConnectionFactory.makeConnection();
					Statement stmt = conn.createStatement();
					try {
						String sql = "INSERT INTO `wz_supply`(type,typeName,model,number,createTime,createId,state)"+"VALUES"+Stringsql;
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
