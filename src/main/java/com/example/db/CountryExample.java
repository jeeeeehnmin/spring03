package com.example.db;

import java.sql.SQLException;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CountryExample {

	public static void main(String[] args) throws SQLException {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/spring/beans.xml");
		
		CountryDao dao = ctx.getBean(CountryDao.class);
		System.out.println(dao.selectAll());
		
		ctx.close();
	}

}
