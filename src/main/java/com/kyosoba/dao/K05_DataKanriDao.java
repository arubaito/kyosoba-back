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

import com.kyosoba.entity.K05_KisyuMasterEntity;
import com.kyosoba.entity.K05_KyosobaMasterEntity;
import com.kyosoba.entity.K05_RaceMasterEntity;
import com.kyosoba.entity.K05_RaceResultEntity;
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
	public List<K05_RaceMasterEntity> selectRaceMaster() {
		
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
	
	
	/**
	 * 騎手マスタテーブルにレコードを登録
	 * @param entity: 登録対象の騎手マスタテーブルEntity
	 * @return 登録件数
	 */
	public int insertKisyuMaster(K05_KisyuMasterEntity entity) {
		
		String sql = "INSERT INTO kisyu(kisyumei) VALUES(?)";
		
		int insertCount = jdbcTemplate.update(sql, entity.getKisyumei());
		
		return insertCount;
		
	}
	
	
	
	/**
	 * 競走馬マスタテーブルにレコードを登録
	 * 
	 * @param entity : 登録対称の競走馬マスタテーブルEntity
	 * @return 登録件数
	 */
	public int insertKyosobaMaster(K05_KyosobaMasterEntity entity) {
		
		String sql = "INSERT INTO kyosoba(bamei, birthday, kyusya, banushi, seisansya, seibetsu, keiro, father_id, mather_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		int insertCount = jdbcTemplate.update(sql, 
				entity.getBamei(),
				entity.getBirthday(),
				entity.getKyusya(),
				entity.getBanushi(),
				entity.getSeisansya(),
				entity.isSebetsu(),
				entity.getKeiro(),
				entity.getFatherId(),
				entity.getMatherId()
				);
		
		return insertCount;
	}

	/**
	 * レース実施テーブルとレースマスタテーブルから全てのレース名とレース実施日を取得
	 * 
	 * @return レースIDとレース名をセットしたレースマスタEntityのリスト
	 */
	public List<K05_RaceZisshiEntity> selectRaceNameAndZisshiDate() {
		
		String sql = "SELECT t1.race_zisshi_id, t2.race_name, t1.date, t1.tousu FROM race_zisshi t1 INNER JOIN race t2 ON t1.race_id = t2.race_id ORDER BY t1.date DESC";
		
		// テーブルからレコードを取得してリストを生成
		List<K05_RaceZisshiEntity> entityList = jdbcTemplate.query(sql, 
				new RowMapper<K05_RaceZisshiEntity>() {
					@Override
					public K05_RaceZisshiEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						K05_RaceZisshiEntity entity = new K05_RaceZisshiEntity();
						entity.setRaceZisshiId(rs.getInt("race_zisshi_id"));
						entity.setRaceName(rs.getString("race_name"));
						entity.setKaisaiDate(rs.getDate("date"));
						entity.setTousu(rs.getInt("tousu"));

						return entity;
					}
			
		});
		
		return entityList;
	}
	
	/**
	 * 騎手マスタテーブルから騎手名と騎手IDを取得
	 * 
	 * @return Entityのリスト
	 */
	public List<K05_KisyuMasterEntity> selectKisyuMaster() {
		
		// 全件取得SQL
		String sql = "SELECT kisyu_id, kisyumei FROM kisyu ORDER BY kisyu_id";
		
		// テーブルからレコードを取得してリストを生成
		List<K05_KisyuMasterEntity> entityList = jdbcTemplate.query(sql, 
				new RowMapper<K05_KisyuMasterEntity>() {
					@Override
					public K05_KisyuMasterEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						K05_KisyuMasterEntity entity = new K05_KisyuMasterEntity();
						entity.setKisyuId(rs.getInt("kisyu_id"));
						entity.setKisyumei(rs.getString("kisyumei"));
						return entity;
					}
			
		});
		
		return entityList;
	}
	
	/**
	 * 競走馬マスタテーブルから馬名と競走馬IDを取得
	 * 
	 * @return Entityのリスト
	 */
	public List<K05_KyosobaMasterEntity> selectKyosobaMaster() {
		
		// 全件取得SQL
		String sql = "SELECT id, bamei FROM kyosoba ORDER BY id DESC";
		
		// テーブルからレコードを取得してリストを生成
		List<K05_KyosobaMasterEntity> entityList = jdbcTemplate.query(sql, 
				new RowMapper<K05_KyosobaMasterEntity>() {
					@Override
					public K05_KyosobaMasterEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						K05_KyosobaMasterEntity entity = new K05_KyosobaMasterEntity();
						entity.setId(rs.getInt("id"));
						entity.setBamei(rs.getString("bamei"));
						return entity;
					}
			
		});
		
		return entityList;
	}
	
	/**
	 * レース結果テーブルにレコードを登録
	 * 
	 * @param entity : 登録対称の競走馬マスタテーブルEntity
	 * @return 登録件数
	 */
	public int insertRaceResult(K05_RaceResultEntity entity) {
		
		String sql = "INSERT INTO race_result(race_zisshi_id, kyosoba_id, kisyu_id, tyakuzyun, waku, umaban, ninki)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ? )";
		
		int insertCount = jdbcTemplate.update(sql, 
				entity.getRaceZisshiId(),
				entity.getKyosobaId(),
				entity.getKisyuId(),
				entity.getTyakuzyun(),
				entity.getWaku(),
				entity.getUmaban(),
				entity.getNinki()
				);
		
		return insertCount;
	}
	
}
