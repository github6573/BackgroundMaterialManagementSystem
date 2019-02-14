package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.MainWzEntity;
import com.xinhai.backSystem.entity.SuppliesInfo;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.UserInfo;

/**
 * @author Tony
 * @version 创建时间：2018年9月5日 上午11:23:31
 * @ClassName 类名称
 * @Description 类描述
 */
// 20楼物资的操作方法2改
public class MainWzSql {
	//使用者列表
	//SearchUserList
	//获取所有的物资的名称 不分页
		public static List<MainWzEntity> SearchUserList() throws SQLException {
			List<MainWzEntity> lessonList = new ArrayList<MainWzEntity>();
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT wm.userName FROM wz_main wm   where wm.state !='0' group by wm.userName";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					MainWzEntity wz = new MainWzEntity();
					//wz.setId(rs.getInt(1));
					wz.setUserName(rs.getString(1));
					lessonList.add(wz);
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
	
	
	
	
	//获取所有的物资的名称 不分页
	public static List<MainWzEntity> SearchMainWzDateAllName() throws SQLException {
		List<MainWzEntity> lessonList = new ArrayList<MainWzEntity>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT wm.name FROM wz_main wm   where wm.state !='0' group by wm.name";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MainWzEntity wz = new MainWzEntity();
				//wz.setId(rs.getInt(1));
				wz.setName(rs.getString(1));
				lessonList.add(wz);
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
	
	//获取所有的物品数量 by组
		public static List<MainWzEntity> SearchMainWzDataGroupByGroupId() throws SQLException {
			List<MainWzEntity> lessonList = new ArrayList<MainWzEntity>();
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT wd.name,count(*) FROM wz_main wm left join wz_dictionary wd on wd.value=wm.groupId where wm.state !='0' and wd.state !='0' group by wm.groupId";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					MainWzEntity wz = new MainWzEntity();
					wz.setExtraFile(rs.getString(1));
					wz.setExtraNumber(rs.getInt(2));
					lessonList.add(wz);
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
	// 批量导入
	// AddSuppliesDataByStringsql
	public static void BatchUploadMainWzDataByStringsql(String Stringsql) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			String sql = "INSERT INTO `wz_main`"
					+ "(name, supplier,model,unit,number,userName,state,position,note,groupId,createTime,createId)"
					+ "VALUES" + Stringsql;
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	// 物资禁用
	public static void DelMainWzData(String id) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			String sql = " UPDATE  wz_main wm set state='0' where  wm.id=" + id;
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
	
	// 物资更新
	public static void UpdateMainWzData(String name,String supplier, String model, String unit, String number,
			String userName, int state, String position, String note,int groupId, String updateId, String id) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			
				String sql = "UPDATE  wz_main wm set name='" + name + "',supplier='" + supplier
						+ "',model='" + model + "',unit='" + unit + "',number='" + number + "',userName='"
						+ userName + "',state='" + state + "',position='" + position + "',note='" + note + "',groupId='"
						+ groupId + "',updateTime='" + now + "',updateId='" + updateId + "' where  wm.id=" + id;
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
	
	
	
	// 物资的增加
	public static void AddMainWzData(String name,String supplier, String model, String unit, String number,
			String userName, int state, String position, String note,int groupId, String createId) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs = null;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try{
				String sql = "INSERT INTO `wz_main`"
						+ "(name, supplier,model,unit,number,userName,state,position,note,groupId,createTime,createId)"
						+ "VALUES" + "('" + name + "','" + supplier + "',' " + model + "', '" + unit + "', '"
						+ number + "' ,'" + userName + "','" + state + "'  , '" + position + "' ,'" + note + "' ,'" + groupId + "','" + now + "','" + createId + "')";
				System.out.println(sql);
				stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	
	//查询数量
	// 物资的数量获取方法SelectSuppliesDataCount
	public static int SearchMainWzDataCount(String SelectSql) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM wz_main wm where wm.state!='0' "+SelectSql;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
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
	
	
	
	
	//获取所有的物资
	
	public static List<MainWzEntity> SearchMainWzDate(int page, int limit,String SelectSql) throws SQLException {
	List<MainWzEntity> lessonList = new ArrayList<MainWzEntity>();
	Connection conn = ConnectionFactory.makeConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	page = limit * (page - 1);
	try {
		String sql = "SELECT wm.*,wd.name FROM wz_main wm  left join wz_dictionary wd on wm.groupId=wd.value where wm.state !='0'  and wd.type='groupId' and wd.state !='0' "+SelectSql+"  limit " + page + "," + limit;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			MainWzEntity wz = new MainWzEntity();
			wz.setId(rs.getInt(1));
			wz.setName(rs.getString(2));
			wz.setSupplier(rs.getString(3));
			wz.setModel(rs.getString(4));
			wz.setUnit(rs.getString(5));
			wz.setNumber(rs.getInt(6));
			wz.setUserName(rs.getString(7));
			wz.setState(rs.getInt(8));
			wz.setPosition(rs.getString(9));
			wz.setNote(rs.getString(10));
			wz.setGroupId(rs.getInt(11));
			wz.setCreateTime(rs.getTimestamp(12));
			wz.setCreateId(rs.getInt(13));
			wz.setUpdateTime(rs.getTimestamp(14));
			wz.setUpdateId(rs.getInt(15));
			wz.setGroupName(rs.getString(16));
			lessonList.add(wz);
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
