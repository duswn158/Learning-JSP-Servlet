package com.bike.dao;

import static com.bike.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bike.dto.BikeDto;

public class BikeDao {
		
	// 데이터 양이 많을때 평소와같이 insert를 하면 에러가남
	// 한번에 담아서 보내야함 addbach
	public int insert (List<BikeDto> bikes) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = " INSERT INTO BIKE_TB "
					+" VALUES(?, ?, ?, ?, ?, ?, ?) ";
		
		int res = 0;
		
		BikeDto dto = new BikeDto();
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i < bikes.size(); i++) {
				
				pstm.setString(1, bikes.get(i).getAddr_gu());
				pstm.setInt(2, bikes.get(i).getContent_id());
				pstm.setString(3, bikes.get(i).getContent_nm());
				pstm.setString(4, bikes.get(i).getNew_addr());
				pstm.setInt(5, bikes.get(i).getCradle_count());
				pstm.setDouble(6, bikes.get(i).getLongitude());
				pstm.setDouble(7, bikes.get(i).getLatitude());
				
				pstm.addBatch();
				/*
				dto = bikes.get(i);
				
				// 1, bikes[1] 는 list이기 때문에 값이아니라 배열 한줄의 주소값이됨
				pstm.setString(1, dto.getAddr_gu());
				pstm.setInt(2, dto.getContent_id());
				pstm.setString(3, dto.getContent_nm());
				pstm.setString(4, dto.getNew_addr());
				pstm.setInt(5, dto.getCradle_count());
				pstm.setDouble(6, dto.getLongitude());
				pstm.setDouble(7, dto.getLatitude());
				
				pstm.addBatch();
				*/
			}
			System.out.println("3쿼리준비 : "+sql);
			
			int[] result = pstm.executeBatch();
			
			System.out.println("4. query 실행 및 리턴");
			for(int i = 0; i < result.length; i++) {
				// 성공 : -2 / 실패 : -3
				if(result[i] == -2) {
					res++;
				}
			}
			
			if (res == bikes.size()) {
				commit(con);
				System.out.println("commit");
			} else {
				rollback(con);
			}
			
			System.out.println("4 리턴");
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("db종료");
		}		
		
		return (res == bikes.size()) ? 1: 0;
	}
	
	public boolean delete() {
		Connection con = getConnection();
		Statement stmt = null;
		int res = 0;
		String sql = " DELETE FROM BIKE_TB ";
		
		try {
			stmt = con.createStatement();
			System.out.println("쿼리 준비 : "+sql);
			
			res = stmt.executeUpdate(sql);
			System.out.println("쿼리 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
			System.out.println("db종료");
		}		
		
		return (res > 0)? true : false;
	}

}
