package com.muldel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1드라이버 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("error 1.");
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		Connection con = null;
		
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
	
	// 연결확인
	public static boolean isConnection(Connection con) {
		
		// 아래의 해당안되면 true 즉 기본값 true
		boolean valid = true;
		
		try {
			if(con == null || con.isClosed()) {
				valid = false;
			}
		} catch (SQLException e) {
			// 예외가 발생했을때에도 연결이 안됬을 태니 false
			valid = false;
			e.printStackTrace();
		}
		
		return valid;
	}
	
	public static void close(Connection con) {
		
		if (isConnection(con)) {
			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void close(Statement stmt) {
		
		// Statement 객체가 있다면
		if(stmt != null) {
			
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void close(ResultSet rs) {
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void commit(Connection con) {
		
		if(isConnection(con)) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void rollback(Connection con) {
		
		if(isConnection(con)) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}//class
