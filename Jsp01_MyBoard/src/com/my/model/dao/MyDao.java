package com.my.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.my.model.db.JDBCTemplate.*;
import com.my.model.dto.MyDto;

public class MyDao {

	public List<MyDto> selectList(){
		
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		List <MyDto> list = new ArrayList<MyDto>();
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
					+ " FROM MYBOARD ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);			
			
			while(rs.next()) {
				// dto 파라미터 5개 객체에 넣으며 불러옴
				MyDto dto = new MyDto(rs.getInt(1),
									  rs.getString(2),
									  rs.getString(3),
									  rs.getString(4),
									  rs.getDate(5));
				
				/*
				 * 위와 동일
				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				*/
				list.add(dto);				
			}
			
		} catch (SQLException e) {
			System.out.println("3,4 실패");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		
		return list;
	}
	
	public MyDto selectOne(int myno) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
					+ " FROM MYBOARD "
					+ " WHERE MYNO = ? ";
		
		MyDto dto = new MyDto();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("3,4 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}
	
	public int insert(MyDto dto) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		String sql = " INSERT INTO MYBOARD "
					  + " VALUES(MYNOSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		// 적용된 Row의 갯수를 받을 변수
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("3. query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
				
		return res;
	}
	public int update(MyDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql =" UPDATE MYBOARD "
				 	+ " SET MYTITLE = ?, MYCONTENT = ? "
				 	+ " WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
			System.out.println("3. query준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	public int delete(int myno) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		String sql = " DELETE FROM MYBOARD "
					+ " WHERE MYNO = ? ";
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			
			res = pstm.executeUpdate();			
			
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
}
