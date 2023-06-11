package com.kyosoba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyosoba.entity.K05_RaceMasterEntity;
import com.kyosoba.entity.K05_RaceZisshiEntity;

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


	/**
	 * レースマスタテーブルから全てのレースIDとレース名を取得
	 * 
	 * @return レースIDとレース名をセットしたレースマスタEntityのリスト
	 */
	public List<K05_RaceMasterEntity> getRaceMasterRaceNameAndRaceId() {
		
		String sql = "SELECT race_id, race_name FROM race";
		
		// テーブルからレコードを取得してリストを生成
		List<K05_RaceMasterEntity> entityList = jdbcTemplate.query(sql, 
				new RowMapper<K05_RaceMasterEntity>() {
					@Override
					public K05_RaceMasterEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						K05_RaceMasterEntity entity = new K05_RaceMasterEntity();
						entity.setRaceId(rs.getInt("race_id"));
						entity.setRaceName(rs.getString("race_name"));
						
						return entity;
					}
			
		});
		
		return entityList;
	}
	
	/**
	 * レース実施テーブルに１件のレコードを登録
	 * @param entity: 登録対象のレース実施テーブルEntity
	 * @return 登録件数
	 */
	public int insertRaceZisshi(K05_RaceZisshiEntity entity) {
		
		String sql = "INSERT INTO race_zisshi(race_id, date, tenko, baba, tousu) VALUES(?, ?, ?, ?, ?)";
		
		int insertCount = jdbcTemplate.update(sql,
				entity.getRaceId(),
				entity.getKaisaiDate(),
				entity.getTenko(),
				entity.getBaba(),
				entity.getTousu()
				);
	
	return insertCount;
	}
	
	
	
	
	
	
	
	
	
	
	
}
