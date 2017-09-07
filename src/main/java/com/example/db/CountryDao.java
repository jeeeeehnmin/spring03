package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;

public class CountryDao implements DisposableBean{

	static Log log = LogFactory.getLog(CountryDao.class);
	
	BasicDataSource ds;
	
	public CountryDao() {
	}
	

	public void setDataSource(BasicDataSource ds) {
		log.info("==========================");
		log.info("start");
		log.info("--------------------------");
		log.info("setDataSource..");
		log.info("==========================");
		this.ds = ds;
	}
	
	public List<String> selectAll() throws SQLException{
		
		Connection conn = ds.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select Name from country where continent=?");
		pstmt.setString(1, "Asia");
		
		ResultSet rs = pstmt.executeQuery();
		List<String> list = new ArrayList<>();
		while(rs.next()){
			list.add(rs.getString("Name"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}

	@Override
	public void destroy() throws Exception {
		log.info("==========================");
		log.info("end");
		log.info("--------------------------");
		log.info("Destroy");
		log.info("==========================");
		ds.close();
	}
}
