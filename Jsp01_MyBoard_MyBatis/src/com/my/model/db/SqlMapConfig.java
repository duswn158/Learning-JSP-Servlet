package com.my.model.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		// classs = . 
		// 경로 = /
		String resource = "com/my/model/db/mybatis-config.xml";
		
		InputStream inputStream = null;
		
		try {
			// resource를 읽어와서
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// SqlSessionFactory를 빌더가 만듬 (전역변수)
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	
		return sqlSessionFactory;
	}
	
	
}//class
