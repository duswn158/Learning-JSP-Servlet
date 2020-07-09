package com.muldel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.muldel.db.JDBCTemplate;
import com.muldel.dto.MDBoardDto;

public class MDBoardDao extends JDBCTemplate{
   
   public List<MDBoardDto> selectList() {
      
      Connection con = getConnection();
      
      Statement stmt = null;
      ResultSet rs = null;
      List<MDBoardDto> list = new ArrayList<MDBoardDto>();
      String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
               + " FROM MDBOARD "
               + " ORDER BY SEQ DESC ";
      
      try {
         stmt = con.createStatement();
         System.out.println("3. query 준비");
         
         
         rs = stmt.executeQuery(sql); // 퀴리 실행문 !!
         System.out.println("4. query 실행 및 리턴 ");
         while(rs.next()) {
        	 MDBoardDto dto = new MDBoardDto(rs.getInt(1),
						                     rs.getString(2),
						                     rs.getString(3),
						                     rs.getString(4),
						                     rs.getDate(5));
        	 list.add(dto);

         }
         
      } catch (SQLException e) {
    	 System.out.println("[error] 3. 4");
         e.printStackTrace();
      } finally {
    	  close(rs);
    	  close(stmt);
    	  close(con);
    	  System.out.println("5.db종료");
      }
      
      return list;      
      
   }
   
   public MDBoardDto selectOne(int seq) {
	   
	   Connection con = getConnection();
	   
	   PreparedStatement pstm = null;
	   ResultSet rs = null;
	   
	   String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
			   		+ " FROM MDBOARD "
			   		+ " WHERE SEQ = ? ";
	   
	   // dto 객체생성
	   MDBoardDto dto = new MDBoardDto();
	   
	   try {
		pstm = con.prepareStatement(sql);
		// WHERE SEQ = ? 에 들어갈 값.
		pstm.setInt(1, seq);
		
		rs = pstm.executeQuery();
		
		while(rs.next()) {			
			// dto는 컬럼 한줄을 담는 버스, 따라서 쿼리문의 결과 즉 where로 필터한
			// SELECT 컬럼 한줄을 dto에 담아서 리턴 시켜줄것
			// insert나 update등은 ?값을 넣으면서 가져오는것도 한번에 하는듯.
			dto.setSeq(rs.getInt(1));
			dto.setWriter(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setRegdate(rs.getDate(5));
		}		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
	   // 위에서 담아온 컬럼한줄 dto 리턴
	   return dto;
   }
   
   public int insert(MDBoardDto dto) {
	   
	   Connection con = getConnection();
	   
	   PreparedStatement pstm = null;
	   
	   String sql = " INSERT INTO MDBOARD "
			   		+ " VALUES(MDBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
	   
	   // ResultSet 을 대신함
	   int res = 0;
	   
	   try {
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3 준비");
			
			res = pstm.executeUpdate();
			System.out.println("4 리턴");
			
			if (res > 0) {
				con.commit();
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
   public int update(MDBoardDto dto) {
	   
	   Connection con = getConnection();
	   
	   PreparedStatement pstm = null;
	   String sql = " UPDATE MDBOARD " +
			   		" SET WRITER = ?, TITLE = ?, CONTENT = ? " +
			   		" WHERE SEQ = ? ";
	   int res = 0;
	   try {
		   pstm = con.prepareStatement(sql);
		   pstm.setString(1, dto.getWriter());
		   pstm.setString(2, dto.getTitle());
		   pstm.setString(3, dto.getContent());
		   pstm.setInt(4, dto.getSeq());
		   
		   res = pstm.executeUpdate();
		   
		   if(res > 0) {
			   commit(con);
		   }
		   
		} catch (SQLException e) {
			System.out.println("3,4 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5종료");
		}  
	   
	   return res;
   }
   
   public int delete(int seq) {
	   
	   Connection con = getConnection();	   
	   PreparedStatement pstm = null;	   
	   String sql = " DELETE FROM MDBOARD "
			   		+ " WHERE SEQ = ? ";
	   
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
	} finally {
		close(pstm);
		close(con);
	}
	   
	   
	   return res;
   }
   
   // 체크한 글들이 전부 삭제되어야함
   // 삭제할 숫자들 배열로 받아옴
   // db에서 sql은 '1'이나 1이나 int 표기시 '' 생략 가능하기 때문에
   // 굳이 int로 형변환 하지 않고 String으로 받아옴
   
   /*
    * boardlist에서 받아온 dte.getSeq가
    * int형태로 받아왓지만 그걸 form태그를 통해
    * muldel로 보내줄때 문자형태로바뀜
    * 그걸 java에서 받아주기위해서
    * String배열로 받음
    * 근데 sql구문은 넘버타입에 ''
    * 덮어줘도 넘버로 잘 취급함
    * 그래서 int로 다시형변환해서
    * 넣어주지않고 그냥 때려넣어줘도 잘실행됨
    */
   
   public int multiDelete(String[] seq) {
	   
	   Connection con = getConnection();
	   PreparedStatement pstm = null;
	   int res = 0;
	   String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
	   
	   // addBatch(); 의 적재값 받아올 배열 변수
	   int[] cnt = null;
	   
	   try {
		pstm = con.prepareStatement(sql);
		
		// 삭제할 번호가 한번에 여러개 넘어왔기 때문에 for문
		for (int i = 0; i < seq.length; i++) {
			
			// 값 받아서 메모리에 넣고.
			pstm.setString(1, seq[i]);
			
			// 메모리에 적재 후, executeBatch() 메소드가 호출될 때 한번에 실행
			// 바로 실행하지 않고 잠깐 임시로 적재만 해둠 즉 쿼리문 여러개가 저장되어 있음
			pstm.addBatch();
			System.out.println("삭제할 번호 : " + seq[i]);
		}
		System.out.println("3.query 준비 : " + sql);
		
		// 위에서 적재해둔걸 한번에 실행 그 결과값들이 여러개 나오기 때문에 배열 변수로 받아넣음
		// execyteBatch() 이용.
		cnt = pstm.executeBatch();
		System.out.println("4.query 실행 및 리턴");
		
		// 성공 : -2 / 실패 : -3 이라고 정의됨  (오라클 규칙) executeBatch() api가보면 나옴
		for (int i= 0; i < cnt.length; i++) {
			
			// cnt = pstm.executeBatch(); 성공한것들 찾기 즉 성공시
			if(cnt[i] == -2) {
				// 성공한 갯수 카운트
				res++;
			}
			
		}
		
		// 성공한 갯수가 위에서 받아온 배열갯수 즉 처음 받아온갯수와 같다면
		// 즉 전부 성공했다면 commit
		if (seq.length == res) {
			commit(con);
		} 
		
		/*
		   else {
			rollback(con);
			}
			해주어도 되지만 Autocommit(false)이기 때문에 명시하지 않아도됨 
		*/
		
	} catch (SQLException e) {		
		e.printStackTrace();
	} finally {
		close(pstm);
		close(con);
		System.out.println("5. db 종료");
	}
	   
	   // 위의 성공했는지 여부를 검사하는 if 문 대신 사용가능
	   // return (seq.length == res)? 1 : 0;
	   return res;
   }
   
}