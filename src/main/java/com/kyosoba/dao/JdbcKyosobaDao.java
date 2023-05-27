package com.kyosoba.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 競走馬テーブルDAO
 */
@Repository
@ComponentScan("com.kyosoba") // よくわからんけど、ここにComponentScanを付けるとDriverのエラーが出なくなる
public class JdbcKyosobaDao {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(JdbcKyosobaDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int find() {
		logger.info("----------JdbcKyosobaDao#find---------");
		String sql = "select max(id) from kyosoba";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
