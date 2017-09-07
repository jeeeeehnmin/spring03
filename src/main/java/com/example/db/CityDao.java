package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class CityDao implements InitializingBean, DisposableBean {

	static Log log = LogFactory.getLog(CityDao.class);

	public CityDao() {
		log.info("#######################");
		log.info("1. CityDao()....");
		log.info("#######################");
	}

	/*
	 * bean을 초기화하는 세가지 방법 Customed--> init-method Interface -->
	 * afterPropertiesSet() postConstructor --> 의존 주입 후에 annotation
	 */

	BasicDataSource ds;

	public void setDataSource(BasicDataSource ds) {
		this.ds = ds;

		log.info("########################");
		log.info("2. setDataSource()....");
		log.info("########################");
	}

	@PostConstruct
	public void postConstruct() {

		log.info("########################");
		log.info("3. @PostConstruct....");
		log.info("########################");

	}

	@Override
	public void afterPropertiesSet() throws Exception {

		// setDataSource()에 잘들어왔는지 확인용
		log.info(ds.getDriverClassName());
		log.info(ds.getUrl());
		log.info(ds.getUsername());
		log.info(ds.getPassword());

		/*
		 * 약속된 인터페이스도 호출 | properties를 다 호출한 다음에 바로 호출 spring에 의존적인 초기화 메소드
		 * 
		 */
		log.info("#################################################");
		log.info("4. InitializingBean.afterPropertiesSet()....");
		log.info("#################################################");

	}

	private void init() {

		/*
		 * 커스텀 초기화 메소드 : 이름은 상관 없음 bean.xml에 지정만 잘해주면 된다.
		 * 
		 */
		log.info("#######################");
		log.info("5. Customed Init()....");
		log.info("#######################");
	}

	/*
	 * 초기화 끝! 객체 사용
	 */

	public List<String> selectAll() throws SQLException {

		Connection conn = ds.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select Name from city where countryCode=?");
		pstmt.setString(1, "KOR");

		ResultSet rs = pstmt.executeQuery();

		List<String> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getString("name"));
		}

		rs.close();
		pstmt.close();
		conn.close();
		return list ;
	}

	@PreDestroy
	public void preDestory() {
		log.info("#######################");
		log.info("6. @PreDestroy....");
		log.info("#######################");
	}

	@Override
	public void destroy() throws Exception {

		log.info("####################################");
		log.info("7. DisposableBean.destory....");
		log.info("####################################");

	}

	public void close() {

		// 커스텀 초기화 메소드 : 종료 직전에 호출
		log.info("#######################");
		log.info("8. Customed Close()....");
		log.info("#######################");
	}

}
