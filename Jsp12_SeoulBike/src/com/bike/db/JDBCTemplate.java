package com.bike.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	//connection
		public static Connection getConnection() {
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("1 에러");
				e.printStackTrace();
			}
			
			Connection con = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "kh";
			String password = "kh";
			
			try {
				con = DriverManager.getConnection(url, user, password);
				con.setAutoCommit(false);
			} catch (SQLException e) {
				System.out.println("2에러");
				e.printStackTrace();
			}
			
			return con;
		}
		
		
		//close
		public static void close(Connection con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(ResultSet rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Statement stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//commit
		public static void commit(Connection con) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//rollback
		public static void rollback(Connection con) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
