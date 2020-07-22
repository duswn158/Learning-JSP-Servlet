package com.mvc.model.dao;

import static com.mvc.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.model.dto.MVCBoardDto;

public class MVCBoardDao {
	
	public List<MVCBoardDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
					+ " FROM MVCBOARD ";
		
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MVCBoardDto dto = new MVCBoardDto(rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getDate(5));
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			System.out.println("3,4 에러");
			e.printStackTrace();
		} finally {
			close(rs);
	    	close(stmt);
	    	close(con);
	    	System.out.println("5.db종료");
	    }		
		
		return list;
		
	}
	
	public MVCBoardDto selectOne(int seq) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
					+ " FROM MVCBOARD "
					+ " WHERE SEQ = ? ";
		
		MVCBoardDto dto = new MVCBoardDto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
								
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new MVCBoardDto(rs.getInt(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									rs.getDate(5));			
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
	public int insert(MVCBoardDto dto) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		String sql = " INSERT INTO MVCBOARD "
					+ " VALUES(MVCBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
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
			System.out.println("db종료");
		}
		
		return res;
	}
	public int update(MVCBoardDto dto) {
		
		Connection con = getConnection();		
		PreparedStatement pstm = null;		
		String sql = " UPDATE MVCBOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
		
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
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
			System.out.println("종료");
		}
		
		return res;
	}
	public int delete(int seq) {
		
		Connection con = getConnection();		
		PreparedStatement pstm = null;		
		String sql = " DELETE FROM MVCBOARD WHERE SEQ = ? ";
		
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

}// class
