package com.kyosoba.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyosoba.dao.JdbcRaceKekkaDao;
import com.kyosoba.entity.RaceKekka;
import com.kyosoba.model.K02_RaceKekkaListResource;
import com.kyosoba.model.K02_RaceKekkaListResource.K02_RaceKekkaResource;

/**
 * 競走馬のレース結果を返すためのService
 */
@Service
public class K02_RaceKekkaService {

	// Logger
	Logger logger = LoggerFactory.getLogger(K02_RaceKekkaService.class);

	@Autowired
	JdbcRaceKekkaDao jdbcRaceKekkaDao;

	/**
	 * レース実施IDをキーにレース結果のリストを取得
	 * 
	 * @return 昇順に並んだレース結果のリスト
	 */
	public K02_RaceKekkaListResource findByRaceZisshiId(int raceZisshiId) {
		
		// APIの呼び出し元へ返却するリソース
		K02_RaceKekkaListResource raceKekkaListResource = new K02_RaceKekkaListResource();
		// リソースのプロパティにセットするリスト
		List<K02_RaceKekkaResource> list = new ArrayList<>();
		
		// DBからレースに出走した競走馬の結果を取得
		List<RaceKekka> raceKekkaList = jdbcRaceKekkaDao.getRaceKekkaEntityList(raceZisshiId);
		
		
		// 競走馬単位でリストにデータを追加
		raceKekkaList.forEach(x -> {
			
			K02_RaceKekkaResource raceKekkaResource = new K02_RaceKekkaResource();
			
			raceKekkaResource.setTyakuzyun(x.getTyakuzyun());
			raceKekkaResource.setWaku(x.getWaku());
			raceKekkaResource.setUmaban(x.getUmaban());
			raceKekkaResource.setYosou(x.getYosou());
			raceKekkaResource.setBamei(x.getBamei());
			raceKekkaResource.setSeirei(x.isSeibetsu() == true ? "牡" : "牝"); // TODO Enum型から取得する
			raceKekkaResource.setKisyu(x.getKisyumei());
			raceKekkaResource.setNinki(x.getNinki());
			
			list.add(raceKekkaResource);
			
		});
		
		// リソースのプロパティに競走馬のレース結果を保持したリストをセット
		raceKekkaListResource.setRaceKekkaList(list);
		
		return raceKekkaListResource;
	}
	
}
