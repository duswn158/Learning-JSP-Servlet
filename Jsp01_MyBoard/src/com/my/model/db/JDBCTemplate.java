package com.my.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("1연결 실패");
			e.printStackTrace();
		}
		
		Connection con = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2연결");
		} catch (SQLException e) {
			System.out.println("2연결 실패");
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("con close 실패");
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("stmt close 실패");
			e.printStackTrace();
		}	
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println("rs close 실패");
			e.printStackTrace();
		}
	}
	
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			System.out.println("커밋 실패");
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			System.out.println("rollback 실패");
			e.printStackTrace();
		}		
	}
	
}
