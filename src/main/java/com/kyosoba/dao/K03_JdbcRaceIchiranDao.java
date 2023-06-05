package com.kyosoba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyosoba.entity.K03_RaceInfoEntity;
import com.kyosoba.entity.K03_RaceZisshiEntity;

/**
 * レース一覧を取得するDAO
 */
@Repository
public class K03_JdbcRaceIchiranDao {

	// Logger
	Logger logger = LoggerFactory.getLogger(K03_JdbcRaceIchiranDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 最新のレース実施IDを10件取得
	 * @return
	 */
	public List<K03_RaceZisshiEntity> getRaceZisshiIdOrderByDateLimit10(){
		
		// 返却するリスト
		List<K03_RaceZisshiEntity> raceZisshiIdList = new ArrayList<>();
		
		// 最新のレース10件のレース実施IDを取得
		String selectIdSql = "select race_zisshi_id from race_zisshi order by date desc limit 10";
		 raceZisshiIdList = jdbcTemplate.query(selectIdSql, 
				// RaceIchiranEntity変換処理
				new RowMapper<K03_RaceZisshiEntity>() {
					@Override
					public K03_RaceZisshiEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						K03_RaceZisshiEntity entity = new K03_RaceZisshiEntity();
						entity.setRaceZisshiId(rs.getInt("race_zisshi_id"));
						return entity;
					}
		});
		return raceZisshiIdList;
	}
	
	/**
	 * レース実施IDをキーにレース結果情報を取得
	 * @return
	 */
	public K03_RaceInfoEntity getRaceInfo(int raceZisshiId){
		
		// レース一覧画面に表示するレース結果情報
		K03_RaceInfoEntity raceInfo = new K03_RaceInfoEntity();
		
		// レース一覧画面に表示するレース情報を取得
		String selectRaceInfosql = "select"
				+ "    t1.race_zisshi_id"
				+ "    , t2.race_name"
				+ "    , t2.grade"
				+ "    , t1.date"
				+ "    , t2.course"
				+ "    , t2.kyori"
				+ "    , t1.tousu"
				+ "    , t1.tenko"
				+ "    , t1.baba"
				+ "    , t4.bamei as tyakuzyun1"
				+ "    , min(t4.bamei) over (order by t3.tyakuzyun asc rows between 1 following and 1 following) as tyakuzyun2"
				+ "    , min(t4.bamei) over (order by t3.tyakuzyun asc rows between 2 following and 2 following) as tyakuzyun3"
				+ " from"
				+ "    race_zisshi t1 "
				+ "    inner join race t2 "
				+ "        on t1.race_id = t2.race_id "
				+ "    left outer join race_result t3 "
				+ "        on t1.race_zisshi_id = t3.race_zisshi_id "
				+ "    left outer join kyosoba t4 "
				+ "        on t3.kyosoba_id = t4.id "
				+ " where "
				+ "    t1.race_zisshi_id = ? "
				+ " order by"
				+ "    t3.tyakuzyun asc"
				+ " limit 1";
		
		raceInfo = jdbcTemplate.queryForObject(selectRaceInfosql, 
				new RowMapper<K03_RaceInfoEntity>() {
				@Override
				public K03_RaceInfoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					K03_RaceInfoEntity entity = new K03_RaceInfoEntity();
					entity.setRaceZisshiId(rs.getInt("race_zisshi_id"));
					entity.setRaceName(rs.getString("race_name"));
					entity.setRaceGrade(rs.getInt("grade"));
					entity.setRaceDate(rs.getDate("date"));
					entity.setKyori(rs.getInt("kyori"));
					entity.setTousu(rs.getInt("tousu"));
					entity.setTenko(rs.getInt("tenko"));
					entity.setBaba(rs.getInt("baba"));
					entity.setTyakuzyun1(rs.getString("tyakuzyun1"));
					entity.setTyakuzyun2(rs.getString("tyakuzyun2"));
					entity.setTyakuzyun3(rs.getString("tyakuzyun3"));
					return entity;
				}
		}, raceZisshiId);
		
	return raceInfo;
	}
	
}
/*
select
    t1.race_zisshi_id
    , t2.race_name
    , t2.grade
    , t1.date
    , t2.course
    , t2.kyori
    , t1.tousu
    , t1.tenko
    , t1.baba
    , t4.bamei as tyakuzyun1
    , min(t4.bamei) over (order by t3.tyakuzyun asc rows between 1 following and 1 following) as tyakuzyun2
    , min(t4.bamei) over (order by t3.tyakuzyun asc rows between 1 following and 1 following) as tyakuzyun3
from
    race_zisshi t1 
    inner join race t2 
        on t1.race_id = t2.race_id 
    left outer join race_result t3 
        on t1.race_zisshi_id = t3.race_zisshi_id 
    left outer join kyosoba t4 
        on t3.kyosoba_id = t4.id 
where 
    t1.race_zisshi_id = 1 -- プレースホルダ
order by
    t3.tyakuzyun asc
*/