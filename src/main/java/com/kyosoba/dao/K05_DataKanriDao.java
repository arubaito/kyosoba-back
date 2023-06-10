package com.kyosoba.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kyosoba.entity.K05_RaceMasterEntity;

/**
 * データ管理画面DAO
 */
@Repository
public class K05_DataKanriDao {

	// Logger
	Logger logger = LoggerFactory.getLogger(K05_DataKanriDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	/**
	 * レーステーブルにレコードを１件登録
	 * 
	 * @param entity: レーステーブルへINSERTするエンティティ
	 * @return 登録件数
	 */
	public int insertRaceMaster(K05_RaceMasterEntity entity) {
		
		String insertSql = "INSERT INTO race(race_name, grade, course, kyori, mawari, place) VALUES (?, ?, ?, ?, ?, ?)";
		
		int insertCount = jdbcTemplate.update(insertSql, 
				entity.getRaceName(),
				entity.getGrade(),
				entity.isCourse(),
				entity.getKyori(),
				entity.isMawari(),
				entity.getPlace()
		);
		
		return insertCount;
	}
	
}
