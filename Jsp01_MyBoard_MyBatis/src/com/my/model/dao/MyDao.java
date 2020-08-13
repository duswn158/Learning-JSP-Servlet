package com.my.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.my.model.db.SqlMapConfig;
import com.my.model.dto.MyDto;

public class MyDao extends SqlMapConfig {
	
	// myboard : mapper.xml 에서 map의 namespace
	// 뒤에 . 이 중요 (.까지 찍어둬서 아래에서 selectOne만 넘겨도 합쳐져서 myboard.selectOne 이 됨)
	private String namespace = "myboard.";

	public List<MyDto> selectList(){	
		
		SqlSession session = null;
		List<MyDto> list = null;
		
		session = getSqlSessionFactory().openSession(true);
		list = session.selectList("myboard.selectList");
		session.close();
		
		return list;
	}
	public MyDto selectOne(int myno) {
		
		SqlSession session = null;
		MyDto dto  = null;
		
		try {
			// 커넥션 객체처럼 사용하는 session
			session = getSqlSessionFactory().openSession(true);
			dto = session.selectOne(namespace+"selectOne", myno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}
	public int insert(MyDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			// openSession(true) : ?
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace + "insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
		
	}
	public int update(MyDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace + "update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
		
	}
	public int delete(int myno) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace + "delete", myno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
}
