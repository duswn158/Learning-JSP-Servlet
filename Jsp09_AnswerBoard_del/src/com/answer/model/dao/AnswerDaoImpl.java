package com.answer.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.answer.db.JDBCTemplate.*; 

import com.answer.model.dto.AnswerDto;

public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = SELECT_LIST_SQL;
		
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				AnswerDto dto = new AnswerDto(rs.getInt(1),
											rs.getInt(2),
											rs.getInt(3),
											rs.getInt(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getString(8),
											rs.getDate(9));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		
		return list;
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = SELECT_ONE_SQL;
		
		AnswerDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				dto = new AnswerDto(rs.getInt(1),
									rs.getInt(2),
									rs.getInt(3),
									rs.getInt(4),
									rs.getString(5),
									rs.getString(6),
									rs.getString(7),
									rs.getString(8),
									rs.getDate(9));
				
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

	@Override
	public int insert(AnswerDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = INSERT_SQL;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			
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

	@Override
	public int update(AnswerDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = UPDATE_SQL;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			pstm.setInt(4, dto.getBoardno());
			
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

	@Override
	public int delete(int boardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = DELETE_SQL;
		
		int res = 0;
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			
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

	@Override
	public int answerUpdate(int parentboardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = ANSWER_UPDATE_SQL;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, parentboardno);
			pstm.setInt(2, parentboardno);
			
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

	@Override
	public int answerInsert(AnswerDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = ANSWER_INSERT_SQL;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			
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
