package com.cal.dao;

import static com.cal.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

public class CalDao {
	
	public CalDto selectOne(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ,ID,TITLE,CONTENT,MDATE,REGDATE "
					+ " FROM CALBOARD "
					+ " WHERE SEQ = ? ";
		
		CalDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new CalDto(rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getDate(6));
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
				
		return dto;
	}
	
	public List<CalDto> getCalList(String id, String yyyyMMdd) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		// id를 받아오고 mdate를 1 ~ 8까지 자른것(년,월,일)과 동일한 것을 찾아옴
		// SUBSTR을 LIKE로 해도 상관없음
		String sql = " SELECT SEQ,ID,TITLE,CONTENT,MDATE,REGDATE "
					 + " FROM CALBOARD "
					 + " WHERE ID = ? "
					 + " AND SUBSTR(MDATE, 1, 8) = ? ";
		
		List<CalDto> list = new ArrayList<CalDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				CalDto dto = new CalDto(rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getDate(6));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;
	}
	
	// 일정 추가
	public int insertCalBoard(CalDto dto) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		
		String sql = " INSERT INTO CALBOARD "
					+ " VALUES(CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			
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
	
	// 화면에 뿌릴 일정 리스트 가져올것
	public List<CalDto> getViewList(String id, String yyyyMM){
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list =  new ArrayList<CalDto>();
		
		// SELECT * : 하단 while(rs.next) 부분에서 타입 미스매치 에러 인덱스가 맞지 않음 ROW_NUMBER가 1번임
		String sql = " SELECT SEQ,ID,TITLE,CONTENT,MDATE,REGDATE " + 
					" FROM " + 
					" (SELECT " + 
					" (ROW_NUMBER() OVER (PARTITION BY SUBSTR(MDATE, 1, 8) ORDER BY MDATE)) " + 
					" RN, SEQ, ID, TITLE, CONTENT, MDATE, REGDATE " + 
					" FROM CALBOARD " + 
					" WHERE ID = ? " + 
					" AND SUBSTR(MDATE, 1, 6) = ?) " + 
					" WHERE RN BETWEEN 1 AND 3 ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			System.out.println("3.쿼리 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4 쿼리 실행 및 리턴");
			
			while(rs.next()) {
				
				CalDto dto = new CalDto(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getDate(6));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;
	}
	
	// 마우스 오버시 출력될 일정 카운트 (해당하는 날짜의 해당하는 id가 작성한 일정 갯수)
	public int getViewCount(String id, String yyyyMMdd) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count =0;
		
		String sql = " SELECT COUNT(*) "
					+" FROM CALBOARD "
					+ " WHERE ID = ? "
					+ " AND SUBSTR(MDATE, 1, 8) = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("3.쿼리 준비");
			
			rs = pstm.executeQuery();
			System.out.println("4.쿼리 실행 및 리턴");		
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return count;
	}

}
