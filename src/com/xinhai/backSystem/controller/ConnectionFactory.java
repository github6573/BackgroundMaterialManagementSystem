package com.xinhai.backSystem.controller;
import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionFactory {
	/**
	 * 连接数据库zhongkonghe
	 * @return
	 */
	public static Connection makeConnection(){
//		try {
//			Connection conn = null;
//			 Class.forName("org.sqlite.JDBC");
//			//conn = DriverManager.getConnection("jdbc:sqlite:eightfactorysecond.db");
//			// conn = DriverManager.getConnection("jdbc:sqlite:D:\\workspace\\EightFactorySecond\\WebContent\\db\\eightfactorysecond.db");
//			// conn = DriverManager.getConnection("jdbc:sqlite:\\www\\server\\tomcat\\webapps\\EightFactorySecond\\db\\eightfactorysecond.db");
//			//  conn = DriverManager.getConnection("jdbc:sqlite:/www/server/tomcat/webapps/EightFactorySecond/db/eightfactorysecond.db");
//			 return conn;									 
//		} catch (Exception e) {
//			return null;
//			}
//		}
//		
//		 try {
//			 Connection conn = null;
//		        //加载数据库驱动类
//		        Class.forName("com.mysql.jdbc.Driver").newInstance();
//		     
//		     String  url = "jdbc:mysql://localhost:3306/backgroundmaterialmanagementsystem?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
//		      String user = "root";
//		      String password = "1234";
//		
//		        //根据数据库参数取得一个数据库连接
//		     conn = DriverManager.getConnection(url, user, password);
//		     return conn;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//	}
	
	
//	 try {
//		 Connection conn = null;
//	        //加载数据库驱动类
//	        Class.forName("com.mysql.jdbc.Driver").newInstance();
//	       String  url = "jdbc:mysql://111.231.206.138:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
//	      String user = "xh_ytz";
//	      String password = "xinhai0574";
//	
//	        //根据数据库参数取得一个数据库连接
//	     conn = DriverManager.getConnection(url, user, password);
//	     return conn;
//	} 
	 
	 
	 
	 try {
		  Connection conn = null;
	        //加载数据库驱动类
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        String  url = "jdbc:mysql://192.168.0.100:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
	        String user = "xh_ytz";
	        String password = "xinhai0574";
	
	        //根据数据库参数取得一个数据库连接
	     conn = DriverManager.getConnection(url, user, password);
	     return conn;
	}
//		 try {
//		  Connection conn = null;
//	        //加载数据库驱动类
//	        Class.forName("com.mysql.jdbc.Driver").newInstance();
//	      
//	        String  url = "jdbc:mysql://localhost:3306/backgroundmaterialmanagementsystem?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
//	        String user = "root";
//	        String password = "1234";
////	        String  url = "jdbc:mysql://192.168.0.100:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
////	        String user = "xh_ytz";
////	        String password = "xinhai0574";
//	
//	        //根据数据库参数取得一个数据库连接
//	     conn = DriverManager.getConnection(url, user, password);
//	     return conn;
//	} 
	 
	 
	 
	 
	 catch (Exception e) {
		// TODO: handle exception
		return null;
	}
}
	
	
	public static void main(String[] arg){
		Connection makeConnection = makeConnection();
		System.err.println(makeConnection);	
		}

}
