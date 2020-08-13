package com.muldel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.muldel.dto.MDBoardDto;

public class MDBoardDao extends SqlMapConfig{

	// namespace가 강제됨 (mapper에서)
	// 여러 테이블의 xml 즉 Mapper가 여러개 있을때
	// 어떤 Mapper의 selecLitst냐 를 찾아주는것
   private String namespace = "com.muldel.mapper.";
   
   public List<MDBoardDto> selectList() {
	   
	   SqlSession session = null;
	   List<MDBoardDto> list = null;
	   
	   // .openSession(true) : 오토커밋 활성화 (기본값 true)
	   // false = setAutoCommit(false)
	   try {
			session = getSqlSessionFactory().openSession(false);
			
			// session.selectList : SqlSession 객체의 selectOne 함수
			// namespace + selectList로 mapper.xml 넘긴다
			list = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	   
	   return list;            
   }
   
   public MDBoardDto selectOne(int seq) {	   
	    
	    SqlSession session = null;
	    MDBoardDto dto  = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne", seq);
		} catch (Exception e) {
			System.out.println("[error] selectOne");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
   }
   
   public int insert(MDBoardDto dto) {	
	   
	   SqlSession session = null;
	   int res  = 0;
	   
	   try {
		session = getSqlSessionFactory().openSession(false);
		res = session.insert(namespace+"insert", dto);
		
		// 오토커밋 false라서 커밋구문작성
		if(res > 0) {
			session.commit();
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		session.close();
	}
	    
	   return res;
   }
   public int update(MDBoardDto dto) {	
	   
	   SqlSession session = null;
		int res = 0;
		
		try {
			
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "update", dto);
			
			if(res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
		
   }
   
   public int delete(int seq) {	   
	   
	   SqlSession session = null;
		int res = 0;
		
		try {
			
			session = getSqlSessionFactory().openSession(true);
			res = session.delete(namespace + "delete", seq);
			if(res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
		
   }
  
   
   public int multiDelete(String[] seq) {	
	   
	   int count = 0;
	   
	   // HashMap ??
	   // Map <Key : Value>
	   Map<String, String[]> map = new HashMap<>();
	   map.put("seqs", seq);
	   SqlSession session = null;
	   
	   try {
		   session = getSqlSessionFactory().openSession(false);
		   count = session.delete(namespace + "muldel", map);
		   if (count == seq.length) {
			   session.commit();
		   }
		} catch (Exception e) {
	
			e.printStackTrace();
		} finally {
			session.close();
		}
		   
		   return count;
		   
		   /*
		    * delete from mdboard
		    * where seq in (1, 2, 5, 11)
		    */
   }
   
}// class