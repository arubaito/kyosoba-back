package com.kyosoba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyosoba.entity.Kyosoba;

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
	
	/**
	 * IDをキーに競走馬テーブルから１件の競走馬エンティティを取得
	 * 
	 * @param int 競走馬テーブルから取得したいID
	 * @return 取得した競走馬エンティティ
	 */
	public Kyosoba getKyosobaEntity(int findId) {
		
		logger.info("----------JdbcKyosobaDao#find---------");
		logger.info("findId:" + findId);
		
		// メソッドの呼び出し元に返却する競走馬エンティティ
		Kyosoba kyosoba = new Kyosoba();
		
		// DBからデータ取得時に該当データが存在しない場合に発生するExceptionをcatchしてデータが存在しない場合の競走馬情報を生成。
		String sql = "select * from kyosoba where id=?";
		try { 
		kyosoba = jdbcTemplate.queryForObject(
				sql, 
				// BeanPropertyRowMapperの方がプロパティをセットする必要がないが、あえてめんどい方で。
				new RowMapper<Kyosoba>() {
					@Override
					public Kyosoba mapRow(ResultSet rs, int rowNum) throws SQLException {
						Kyosoba kyosoba = new Kyosoba();
						kyosoba.setBamei(rs.getString("bamei"));
						kyosoba.setBirthday(rs.getDate("birthday"));
						kyosoba.setKyusya(rs.getString("kyusya"));
						kyosoba.setBanushi(rs.getString("banushi"));
						kyosoba.setSeisansya(rs.getString("seisansya"));
						kyosoba.setSeibetsu(rs.getBoolean("seibetsu"));
						kyosoba.setKeiro(rs.getString("keiro"));
						kyosoba.setFatherId(rs.getInt("father_id"));
						kyosoba.setMatherId(rs.getInt("mather_id"));
						return kyosoba;
					}
				},
				findId);
		}catch(EmptyResultDataAccessException e) {
			// とりあえず馬名が必要なので、馬名をセット
			kyosoba.setBamei("---");
		}
		 return kyosoba;
	}
	
	/**
	 * IDをキーに競走馬テーブルから１件の馬名を取得
	 * 
	 * @param findId 競走馬テーブルから取得したいID
	 * @return 取得した馬名
	 */
	public String getBamei(int findId) {
		
		logger.info("----------JdbcKyosobaDao#find---------");
		
		String  sql = "select bamei from kyosoba where id=?";
		
		String bamei = jdbcTemplate.queryForObject(sql, String.class, findId);
		
		return bamei;
	}
}
	
