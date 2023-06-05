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

import com.kyosoba.entity.K02_RaceKekkaMemo;
import com.kyosoba.entity.RaceKekka;

/**
 * レース結果取得DAO
 * 
 * 複数テーブルの結果を結合して取得
 */
@Repository
public class JdbcRaceKekkaDao {

	// Logger
	Logger logger = LoggerFactory.getLogger(JdbcRaceKekkaDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * レース実施IDをキーにレース結果のリストを取得
	 * 
	 * @param raceZisshiId : レース実施ID
	 * @return あるレースの競走馬の結果のリスト
	 */
	public List<RaceKekka> getRaceKekkaEntityList(int raceZisshiId) {
		
		// レース結果テーブルへ各マスタテーブルを結合するSQL
		String sql = "select"
				+ "    t3.race_zisshi_id as razeZisshiId"
				+ "    , t2.race_name"
				+ "    , t3.tyakuzyun"
				+ "    , t3.waku"
				+ "    , t3.umaban"
				+ "    , t3.yosou"
				+ "    , t4.bamei"
				+ "    , t4.seibetsu"
				+ "    , t5.kisyumei"
				+ "    , t3.ninki"
				+ " from race_zisshi t1"
				+ "    inner join race t2 on t1.race_id = t2.race_id "
				+ "    left outer join race_result t3 on t1.race_zisshi_id = t3.race_zisshi_id"
				+ "    left outer join kyosoba t4 on t3.kyosoba_id = t4.id"
				+ "    left outer join kisyu t5 on t3.kisyu_id = t5.kisyu_id"
				+ " where t3.race_zisshi_id = ?";
		
		List<RaceKekka> raceKekkaList = jdbcTemplate.query(sql, 
			// RaceKekkaエンティティ変換処理
			new RowMapper<RaceKekka>() {
				@Override
				public RaceKekka mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					RaceKekka raceKekka = new RaceKekka();
					
					raceKekka.setRaceZisshiId(raceZisshiId);
					raceKekka.setRaceName(rs.getString("race_name"));
					raceKekka.setTyakuzyun(rs.getInt("tyakuzyun"));
					raceKekka.setWaku(rs.getInt("waku"));
					raceKekka.setUmaban(rs.getInt("umaban"));
					raceKekka.setYosou(rs.getInt("yosou"));
					raceKekka.setBamei(rs.getString("bamei"));
					raceKekka.setSeibetsu(rs.getBoolean("seibetsu"));
					raceKekka.setKisyumei(rs.getString("kisyumei"));
					raceKekka.setNinki(rs.getInt("ninki"));
					
					return raceKekka;
				}
			},
		raceZisshiId);
		
		return raceKekkaList;
		
	}

	/**
	 * 特定のレースのレース結果メモのレコード数をカウント
	 * 
	 * @param raceKekkaMemo:レース結果メモエンティティ
	 * @return レース結果メモエンティティのレース実施IDと同じIDのレコードの数（1 or 0）
	 */
	public int countRaceKekkaMemo(K02_RaceKekkaMemo raceKekkaMemo) {

		String selectSql = "SELECT COUNT(*) FROM race_result_memo WHERE race_zisshi_id = ?";

		Integer count = jdbcTemplate.queryForObject(selectSql, Integer.class, raceKekkaMemo.getRaceZisshiId());

		// int型で返却
		return count.intValue();
	}

	/**
	 * レース結果メモをレース結果メモテーブルに登録
	 * @param raceKekkaMemo:レース結果メモエンティティ
	 * @return 登録件数
	 */
	public int insertRaceKekkaMemo(K02_RaceKekkaMemo raceKekkaMemo) {

		String insertSql = "INSERT INTO race_result_memo VALUES (?, ?)";

		int insertCount = jdbcTemplate.update(insertSql, raceKekkaMemo.getRaceZisshiId(), raceKekkaMemo.getRaceKekkaMemo());

		return insertCount;
	}

	/**
	 * レース結果メモをレース結果メモテーブルへ更新
	 * @param raceKekkaMemo:レース結果メモエンティティ
	 * @return 更新件数
	 */
	public int updateRaceKekkaMemo(K02_RaceKekkaMemo raceKekkaMemo) {
		
		String updateSql = "UPDATE race_result_memo SET race_kekka_memo = ? WHERE race_zisshi_id = ?";

		int updateCount = jdbcTemplate.update(updateSql, raceKekkaMemo.getRaceKekkaMemo(), raceKekkaMemo.getRaceZisshiId());

		return updateCount;
	}
	
	
	/**
	 * 引数で指定されたIDをもとにレース結果メモテーブルから１レコード取得
	 * 
	 * @param raceZisshiId:レース実施ID
	 * @return レース結果メモエンティティ
	 */
	public K02_RaceKekkaMemo getRaceKekkaMemo(int raceZisshiId) {
		
		String selectSql = "SELECT * FROM race_result_memo WHERE race_zisshi_id = ? limit 1";
		
		K02_RaceKekkaMemo raceKekkaMemo = jdbcTemplate.queryForObject(selectSql, 
				new RowMapper<K02_RaceKekkaMemo>() {
					@Override
					public K02_RaceKekkaMemo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
						// ドメイン変換
						K02_RaceKekkaMemo raceKekkaMemo = new K02_RaceKekkaMemo();
						raceKekkaMemo.setRaceZisshiId(raceZisshiId);
						raceKekkaMemo.setRaceKekkaMemo(rs.getString("race_kekka_memo"));
						return raceKekkaMemo;
					}
				}, raceZisshiId);
		
		return raceKekkaMemo;
	}
	
	
}
