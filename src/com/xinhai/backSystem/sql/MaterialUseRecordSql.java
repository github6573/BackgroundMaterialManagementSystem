package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.DictionaryEntity;
import com.xinhai.backSystem.entity.MaterialuserecordEntity;
import com.xinhai.backSystem.entity.MemberEntity;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.UserInfo;



/**
* @author Tony
* @version 创建时间：2018年9月5日 上午11:23:31
* @ClassName 类名称
* @Description 类描述
*/
public class MaterialUseRecordSql {
	//获取 字典中的组别
	//GetGroupList
	//搜索数据库中所有的物资的名称
		public static List<DictionaryEntity> GetGroupList() throws SQLException {
			List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql="SELECT wd.name,wd.value FROM  wz_dictionary  wd  where wd.state='1' and wd.type='groupId'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					DictionaryEntity de= new DictionaryEntity();
					de.setName(rs.getString(1));
					de.setValue(rs.getString(2));
					lessonList.add(de);
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
	
	
	//UpdateUseRecord
	public static void UpdateUseRecord(String nameSelect,String number,String unit,String nameId,String note,String userId,String id) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		Timestamp now = new Timestamp(System.currentTimeMillis()); 	
		try {
			String sql = "UPDATE  wz_materialuserecord wd set name='"+nameSelect+"',number='"+number+"',unit='"+unit+"',createTime='"+now+"',userId='"+nameId+"',note='"+note+"' where  wd.id='"+id+"'";
			
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
	
	
	//AddUseRecord
	public static void AddUseRecord(String nameSelect,String number,String unit,String nameId,String note,String userId) throws SQLException {
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
	try {
		String sql = "INSERT INTO `wz_materialuserecord`(name,number,unit,createTime,userId,note,state)"+"VALUES('"+nameSelect+"','"+number+"','"+unit+"','"+now+"','"+nameId+"','"+note+"',1)";
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
	
	//搜索数据库中所有的物资的名称(/改 已经换成  用户使用的记录中出现的所有物资)
	public static List<MaterialuserecordEntity> SearchMaterialUseRecordNameList() throws SQLException {
		List<MaterialuserecordEntity> lessonList= new ArrayList<MaterialuserecordEntity>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
//			String sql="SELECT ws.suppliesName FROM  wz_supplies  ws  where ws.state='1' group by ws.suppliesName";
			String sql="SELECT wmu.name FROM  wz_materialuserecord  wmu  where wmu.state='1' group by wmu.name";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MaterialuserecordEntity me= new MaterialuserecordEntity();
				me.setName(rs.getString(1));
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
	
	
	//DelUseRecord
//	//消除使用记录
	//
	public static void DelUseRecord(String id) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		try {
			String sql =" UPDATE  wz_materialuserecord wm set state='0' where  wm.id="+id;
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
		public static List<MaterialuserecordEntity> SearchMaterialUseRecordSql(String SelectSql) throws SQLException {
			List<MaterialuserecordEntity> lessonList= new ArrayList<MaterialuserecordEntity>();
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql="SELECT wm.*,wmb.name FROM wz_materialuserecord wm left join wz_member wmb on wm.userId=wmb.id where wm.state='1'  "+SelectSql;
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					MaterialuserecordEntity me= new MaterialuserecordEntity();
					me.setId(rs.getInt(1));
					me.setName(rs.getString(2));
					me.setNumber(rs.getInt(3));
					me.setUnit(rs.getString(4));
					me.setCreateTime(rs.getTimestamp(5));
					me.setUserId(rs.getInt(6));
					me.setNote(rs.getString(7));
					me.setState(rs.getInt(8));
					me.setUserName(rs.getString(9));
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
	
	
//SearchUseRecordListDataCount	
		public static int SearchUseRecordListDataCount(String SelectSql) throws SQLException {
			int count=0;
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT count(*) FROM wz_materialuserecord wm where wm.state='1' "+SelectSql;
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
			
		
//	
//	//禁用字典字段
//	//
//	public static void DelDict(String id) throws SQLException {
//		Connection conn = ConnectionFactory.makeConnection();
//		Statement stmt = conn.createStatement();
//		try {
//			String sql =" UPDATE  wz_dictionary wd set state='0' where  wd.id="+id;
//			System.out.println(sql);
//			stmt.execute(sql);
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//		
//	
//	
//	//根据字段类型查询对应的值
//	//SearchFildByValueOrName
//	public static int SearchFildByValueOrName(String type,String Filed,String content) throws SQLException {
//	
//	int count=0;
//	Connection conn = ConnectionFactory.makeConnection();
//	Statement stmt = conn.createStatement();
//	ResultSet rs = null;
//	//page=limit*(page-1);
//	try {
//		String sql = "SELECT count(*) FROM wz_dictionary wd  where wd.state='1' and wd.type='"+type+"' and wd."+Filed+"='"+content+"'";
//		System.out.println(sql);
//		rs = stmt.executeQuery(sql);
//		while (rs.next()) {
//			count=rs.getInt(1);
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	} finally {
//		try {
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	return count;
//}
//	
//	//更新字典内容
//	public static void updateDict(String type,String value,String name,String note,String userId,String id) throws SQLException {
//		Connection conn = ConnectionFactory.makeConnection();
//		Statement stmt = conn.createStatement();
//	//	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
//		try {
//			String sql = "UPDATE  wz_dictionary wd set type='"+type+"',value='"+value+"',name="+name+",note='"+note+"' where  wd.id="+id;
//			System.out.println(sql);
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	
//	//增加字典内容
//	public static void AddDict(String type,String value,String name,String note,String userId) throws SQLException {
//		Connection conn = ConnectionFactory.makeConnection();
//		Statement stmt = conn.createStatement();
//	//	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
//		try {
//			String sql = "INSERT INTO `wz_dictionary`(type,value,name,note,state)"+"VALUES('"+type+"','"+value+"','"+name+"','"+note+"',1)";
//			System.out.println(sql);
//			stmt.execute(sql);
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	
//	
////获取字典数量
//public static int SearchDictionaryListDataCount() throws SQLException {
//	int count=0;
//	Connection conn = ConnectionFactory.makeConnection();
//	Statement stmt = conn.createStatement();
//	ResultSet rs = null;
//	try {
//		String sql = "SELECT count(*) FROM wz_dictionary wd where wd.state='1'";
//		System.out.println(sql);
//		rs = stmt.executeQuery(sql);
//		while (rs.next()) {
//			count=rs.getInt(1);
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	} finally {
//		try {
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	return count;
//}
//	
//	
//	//获取各种列表   SelectMemberListData
//	public static List<DictionaryEntity> SearchDictionaryListData() throws SQLException {
//		List<DictionaryEntity> lessonList= new ArrayList<DictionaryEntity>();
//		Connection conn = ConnectionFactory.makeConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = null;
//		try {
//			String sql="SELECT * FROM wz_dictionary wd where wd.state='1'";
//			//String sql = "SELECT wm."+type+"  FROM wz_member wm  where wm.state='1' group by wm."+type;
//			System.out.println(sql);
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				DictionaryEntity de= new DictionaryEntity();
//				de.setId(rs.getInt(1));
//				de.setType(rs.getString(2));
//				de.setValue(rs.getInt(3));
//				de.setName(rs.getString(4));
//				de.setNote(rs.getString(5));
//				de.setState(rs.getInt(6));
//				//de.setExtraField(rs.getString(1));				
//				lessonList.add(de);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return lessonList;
//	}
//	
	
	
	
	
//	//根据id查询对应的工号
//	public static String SearchWorkNumberById(String id) throws SQLException {
//		String workNumber="";
//		Connection conn = ConnectionFactory.makeConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = null;
//		try {
//			String sql = "SELECT wm.workNumber  FROM wz_member wm  where wm.state='1' and wm.id='"+id+"'";
//			//System.out.println(sql);
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				workNumber=rs.getString(1);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return workNumber;
//	}
//	
//	
//	
//	
//	
//public static List<MemberEntity>SelectMemberData(int page,int limit,String SelectSql) throws SQLException {
//		
//		List<MemberEntity> lessonList= new ArrayList<MemberEntity>();
//		Connection conn = ConnectionFactory.makeConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = null;
//		page=limit*(page-1);
//		try {
//			String sql = "SELECT * FROM wz_member wm  where wm.state='1' "+SelectSql+" limit "+ page + "," + limit;
//			System.out.println(sql);
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				MemberEntity me= new MemberEntity();
//				me.setId(rs.getInt(1));
//				me.setName(rs.getString(2));
//				me.setWorkNumber(rs.getString(3));
//				me.setTel(rs.getString(4));
//				me.setDepartment(rs.getString(5));
//				me.setState(rs.getInt(10));
//				lessonList.add(me);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return lessonList;
//	}
//	
//
//
////获取日志数量
//
//public static int SelectMemberDataCount(String SelectSql) throws SQLException {
//	int count=0;
//	Connection conn = ConnectionFactory.makeConnection();
//	Statement stmt = conn.createStatement();
//	ResultSet rs = null;
//	try {
//		String sql = "SELECT count(*) FROM wz_member wm  where wm.state='1'   "+SelectSql;
//		System.out.println(sql);
//		rs = stmt.executeQuery(sql);
//		while (rs.next()) {
//			count=rs.getInt(1);
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	} finally {
//		try {
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	return count;
//}
//
//
////SearchWorkNumberCount 查询工号的数量
//public static int SearchWorkNumberCount(String workNumber) throws SQLException {
//	int count=0;
//	Connection conn = ConnectionFactory.makeConnection();
//	Statement stmt = conn.createStatement();
//	ResultSet rs = null;
//	try {
//		String sql = "SELECT count(*) FROM wz_member wm  where wm.state='1' and wm.workNumber='"+workNumber+"'";
//		System.out.println(sql);
//		rs = stmt.executeQuery(sql);
//		while (rs.next()) {
//			count=rs.getInt(1);
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	} finally {
//		try {
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	return count;
//}
//
////添加员工
//public static void AddMember(String name,String workNumber,String tel,String department,String userId) throws SQLException {
//	Connection conn = ConnectionFactory.makeConnection();
//	Statement stmt = conn.createStatement();
//	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
//	try {
//		String sql = "INSERT INTO `wz_member`(name,workNumber,tel,department,createTime,createId,state)"+"VALUES('"+name+"','"+workNumber+"','"+tel+"','"+department+"','"+now+"','"+userId+"',1)";
//		System.out.println(sql);
//		stmt.execute(sql);
//	} catch (Exception e) {
//		System.out.println(e);
//	} finally {
//		try {
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}
////更新员工信息UpdateMember
//public static void UpdateMember(String name,String workNumber,String tel,String department,String userId,String id) throws SQLException {
//	Connection conn = ConnectionFactory.makeConnection();
//	Statement stmt = conn.createStatement();
//	Timestamp now = new Timestamp(System.currentTimeMillis()); 	
//	try {
//		String sql = "UPDATE  wz_member wm set name='"+name+"',workNumber='"+workNumber+"',tel='"+tel+"',department='"+department+"',updateTime='"+now+"',updateId='"+userId+"' where  wm.id="+id;
//		System.out.println(sql);
//		stmt.execute(sql);
//	} catch (Exception e) {
//		System.out.println(e);
//	} finally {
//		try {
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}





}
