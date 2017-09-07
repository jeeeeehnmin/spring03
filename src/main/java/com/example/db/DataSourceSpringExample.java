package com.example.db;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DataSourceSpringExample {

	public static void main(String[] args) throws SQLException {

		/*
		 * DI 컨테이너 = bean factory
		 * LifeCycle
		 * 1. 객체 생성
		 * 2. 객체 초기화
		 * 3. Dependecy Injecetion
		 * 4. 객체 사용
		 * 5. 객체 소멸
		 */
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/spring/beans.xml");
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("after BeanFactory Create");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		//BEAN 사용
		CityDao dao = ctx.getBean(CityDao.class);
		System.out.println(dao.selectAll());
		
		/*
		BasicDataSource ds = ctx.getBean(BasicDataSource.class);
		
		System.out.println(ds.getDriverClassName());
		System.out.println(ds.getUrl());
		System.out.println(ds.getUsername());
		System.out.println(ds.getPassword());
		
		ds.getConnection();
		 */
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("before BeanFactory ctx.close()....");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		ctx.close();
	}

}
