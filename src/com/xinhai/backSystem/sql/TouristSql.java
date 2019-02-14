package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.MainWzEntity;

/**
* @author Tony
* @version 创建时间：2019年1月28日 上午10:47:54
* @ClassName 类名称
* @Description 类描述
*/
public class TouristSql {
	//获取所有的物资的名称 不分页
			public static List<MainWzEntity> SearchTouristData(int page, int limit,String SelectSql) throws SQLException {
				List<MainWzEntity> lessonList = new ArrayList<MainWzEntity>();
				Connection conn = ConnectionFactory.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				try {
					String sql =
			"SELECT wm.*,wd.name FROM wz_main wm  left join wz_dictionary wd on wm.groupId=wd.value where wm.state !='0'  and wd.type='groupId' and wd.state !='0' "+SelectSql+"  limit " + page + "," + limit;
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
						e.printStackTrace();
					}
				}
				return lessonList;
			}
			
		//SearchTouristDataCount	
			public static int SearchTouristDataCount(String SelectSql) throws SQLException {
				int count=0;
				Connection conn = ConnectionFactory.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				try {
					String sql = "SELECT count(*) FROM wz_main wm  left join wz_dictionary wd on wm.groupId=wd.value where wm.state !='0'  and wd.type='groupId' and wd.state !='0' "+SelectSql;
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
