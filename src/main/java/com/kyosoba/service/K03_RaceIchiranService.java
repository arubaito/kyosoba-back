package com.kyosoba.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyosoba.dao.K03_JdbcRaceIchiranDao;
import com.kyosoba.entity.K03_RaceInfoEntity;
import com.kyosoba.entity.K03_RaceZisshiEntity;
import com.kyosoba.model.K03_RaceInfoResource;

/**
 * レース一覧画面に関するService
 */
@Service
public class K03_RaceIchiranService {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(K03_RaceIchiranService.class);

	@Autowired
	K03_JdbcRaceIchiranDao jdbcRaceIchiranDao;

	public List<K03_RaceInfoResource> getRaceIchiran() {
		
		// APIの呼び出し元へ返却するリソース
		List<K03_RaceInfoResource> raceIchiranListResource = new ArrayList<K03_RaceInfoResource>();
		
		// レース実施IDを最新から10件取得
		List<K03_RaceZisshiEntity> raceIchiran = jdbcRaceIchiranDao.getRaceZisshiIdOrderByDateLimit10();
		
		// IDごとにレース一覧画面に表示するレース結果情報を取得して返却するリストに追加
		raceIchiran.forEach(raceZisshiId -> {
			
			// リソースに追加するレース結果のオブジェクト
			K03_RaceInfoResource raceInfoResource = new K03_RaceInfoResource();
			// DBからレース結果を取得
			K03_RaceInfoEntity raceInfo = jdbcRaceIchiranDao.getRaceInfo(raceZisshiId.getRaceZisshiId());
			raceInfoResource.setRaceZisshiId(raceInfo.getRaceZisshiId());
			raceInfoResource.setRaceName(raceInfo.getRaceName());
			raceInfoResource.setRaceGrade(raceInfo.getRaceGrade());
			raceInfoResource.setRaceDate(raceInfo.getRaceDate());
			raceInfoResource.setKyori(raceInfo.getKyori());
			raceInfoResource.setTousu(raceInfo.getTousu());
			raceInfoResource.setTenko(raceInfo.getTenko());
			raceInfoResource.setBaba(raceInfo.getBaba());
			raceInfoResource.setTyakuzyun1(raceInfo.getTyakuzyun1());
			raceInfoResource.setTyakuzyun2(raceInfo.getTyakuzyun2());
			raceInfoResource.setTyakuzyun3(raceInfo.getTyakuzyun3());
			
			raceIchiranListResource.add(raceInfoResource);
		});
		
		return raceIchiranListResource;

	}
	
}
