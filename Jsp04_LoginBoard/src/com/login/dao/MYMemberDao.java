package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MYMemberDto;
import static com.login.db.JDBCTemplate.*;

public class MYMemberDao {	
	
	// 공통 기능 : login
	public MYMemberDto login(String myid, String mypw ) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MYMemberDto dto = null;
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, "
					+ " MYPHONE, MYEMAIL, MYENABLED, MYROLE "
					+ " FROM MYMEMBER "
					+ " WHERE MYID = ? AND MYPW = ? "
					+ " AND MYENABLED = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			// MYENABLED 값을 쿼리문이 아니라 여기서 넣어줌
			pstm.setString(3, "Y");
			System.out.println("3.query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리 실행및 리턴");
			
			while(rs.next()) {
				dto = new MYMemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
			
		} catch (SQLException e) {
			System.out.println("[erroe] 3.4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}
	
	// 관리자(ADMIN) 기능 : 회원 전체 정보, 가입된 회원 정보, 회원 등급 조정
	public List<MYMemberDto> selectList() {
		
		Connection con = getConnection();
		PreparedStatement ptmt = null;
		
		
		return null;	
	}
	public List<MYMemberDto> selectEnabled() {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<MYMemberDto> list = new ArrayList<MYMemberDto>();
		
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, "
					+ " MYPHONE, MYEMAIL, MYENABLED, MYROLE "
					+ " FROM MYMEMBER "
					+ " WHERE MYENABLED = 'Y' ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				MYMemberDto dto = new MYMemberDto(rs.getInt(1),
												  rs.getString(2),
												  rs.getString(3),
												  rs.getString(4),
												  rs.getString(5),
												  rs.getString(6),
												  rs.getString(7),
												  rs.getString(8),
												  rs.getString(9));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("3,4 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return list;	
	}
	public int updateUserRole(int myno, String myrole) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = " UPDATE MYMEMBER "
					+ " SET MYROLE = ? "
					+ " WHERE MYNO = ? ";
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myrole);
			pstm.setInt(2, myno);
			System.out.println("3.쿼리 준비");
			
			res = pstm.executeUpdate();
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		
		return res;	
	}
	
	// 유저(USER) 기능 : 아이디 중복체크 -> 회원가입, 내 정보 조회, 내 정보 수정(주소, 전화번호, 이메일), 탈퇴
	// id 중복체크를 함
	public MYMemberDto idCheck(String myid) {		
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 객체생성 null값 대비 null포인트 에러 500에러 대비 null값 처리 해주어야함
		MYMemberDto dto = new MYMemberDto();
		String sql = " SELECT MYID FROM MYMEMBER WHERE MYID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			System.out.println("3. 쿼리준비");
			
			rs = pstm.executeQuery();
			System.out.println("4");
			
			while(rs.next()) {
				dto.setMyid(rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.out.println("3,4에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
		
		
		return dto;	
	}
	
	// 리턴값을 int 대신 boolean 으로
	public boolean insertUser(MYMemberDto dto) {
		
		Connection con =  getConnection();
		PreparedStatement pstm = null;
		
		// 대문자로 넣도록 테이블에서 제약조건을 걸어두었기 때문에 Y/N 값 넣을때 주의
		String sql = " INSERT INTO MYMEMBER "
					+ " VALUES(MYNOSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'Y', 'USER') ";
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			System.out.println("3.쿼리 준비완료");
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3, 4에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		// return res 대신 (삼항연산자랑 친해지깅) 
		return (res > 0)? true : false;	
	}
	public MYMemberDto selectUser(int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, " + 
					" MYPHONE, MYEMAIL, MYENABLED, MYROLE "+ 
					" FROM MYMEMBER " + 
					" WHERE MYNO = ? ";
		
		MYMemberDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("쿼리준비 끝 : " + sql);
			
			rs = pstm.executeQuery();
			
			// 한줄인데 while로 담은 이유
			// ROW에 담겨져 있는 값을 한줄 가져올때 커서가 해당위치를 바라보지 않고 있기 때문에
			// 커서를 rs.next()를 통해 첫번째 위치에 올려주기 위해서 한번은 꼭 써줘야함
			// 쓰는김에 select 하는것과 동일하게 작성해주는중
			// 정확히 머리에 이해하기 **************************************************
			while(rs.next()) {
				// 안써주면 warning뜨며 값이 null이라고 뜸 즉 null포인터 execption 뜸
				dto = new MYMemberDto();
				
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
			System.out.println("쿼리준비완료 " + sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("3,4에러");
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return dto;	
	}
	public int updateUser (MYMemberDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = " UPDATE MYMEMBER "
					+" SET MYNAME = ?, MYADDR = ?, MYPHONE = ?, MYEMAIL = ? "
					+" WHERE MYNO = ? ";
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMyaddr());
			pstm.setString(3, dto.getMyphone());
			pstm.setString(4, dto.getMyemail());
			
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
	public int deleteUser (int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		String sql = " UPDATE MYMEMBER "
					+ " SET MYENABLED = 'N' "
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
			e.printStackTrace();
		}
		
		
		return res;	
	}

}
