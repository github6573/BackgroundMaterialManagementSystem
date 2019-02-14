package com.xinhai.backSystem.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.controller.ConnectionFactory;
import com.xinhai.backSystem.entity.Loginfo;



/**
 * @author Tony
 * @version 创建时间：2018年9月5日 上午11:28:54
 * @ClassName 类名称
 * @Description 类描述
 */
public class LogSql {

	// 插入日志
	public static void Addlog(String userId, String content) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "INSERT INTO `wz_log` (userId,logTime,content,state)" + "VALUES ('" + userId + "','" + now
					+ "','" + content + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 日志的查询
	// 获取日志列表
	public static List<Loginfo> SearchMainLogData(int page, int limit) throws SQLException {
		List<Loginfo> lessonList = new ArrayList<Loginfo>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		page = limit * (page - 1);
		try {
			String sql = "SELECT wl.*,wu.userName FROM wz_log  wl left join wz_user wu on wl.userId=wu.id   order by wl.logTime desc  limit "
					+ page + "," + limit;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Loginfo li = new Loginfo();
				li.setId(rs.getInt(1));
				li.setUserId(rs.getInt(2));
				li.setLogTime(rs.getTimestamp(3));
				li.setContent(rs.getString(4));
				li.setState(rs.getInt(5));
				li.setUserName(rs.getString(6));
				lessonList.add(li);
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
	// 获取日志数量

	public static int SearchtMainLogDataCount() throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM wz_log";
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

}
