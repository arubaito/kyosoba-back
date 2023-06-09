package com.kyosoba.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kyosoba.dao.JdbcRaceKekkaDao;
import com.kyosoba.entity.K02_RaceKekkaMemo;
import com.kyosoba.entity.K02_RaceYosou;
import com.kyosoba.entity.RaceKekka;
import com.kyosoba.model.K02_RaceKekkaListResource;
import com.kyosoba.model.K02_RaceKekkaListResource.K02_RaceKekkaResource;
import com.kyosoba.model.K02_RaceKekkaMemoResource;
import com.kyosoba.model.K02_RaceYosouResource;

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
			raceKekkaResource.setKyosobaId(x.getKyosobaId());
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
	
	/**
	 * リソースで受け取ったレース結果メモをDBに登録
	 * 
	 * @param resource : レース結果メモリソース
	 */
	public void registerRaceKekka(K02_RaceKekkaMemoResource resource) {
		
		// リソースをDBに登録するためのエンティティ
		K02_RaceKekkaMemo raceKekkaMemo = new K02_RaceKekkaMemo();
		raceKekkaMemo.setRaceZisshiId(resource.getRaceZisshiId());
		raceKekkaMemo.setRaceKekkaMemo(resource.getRaceKekkaMemo());
		
		// テーブルに既に登録されているメモがあれば更新。無ければ登録
		int count = jdbcRaceKekkaDao.countRaceKekkaMemo(raceKekkaMemo);
		// レコード件数をもとに判定
		if(count == 0) {
			jdbcRaceKekkaDao.insertRaceKekkaMemo(raceKekkaMemo);
		}else {
			jdbcRaceKekkaDao.updateRaceKekkaMemo(raceKekkaMemo);
		}
		
	}
	
	/**
	 * 引数で指定されたレース実施IDのレース結果メモを取得
	 * 
	 * @return レース結果メモリソース
	 */
	public K02_RaceKekkaMemoResource getRaceKekkaMemo(int raceZisshiId) {
		
		// メソッドの呼び出し元に返却するリソース
		K02_RaceKekkaMemoResource raceKekkaMemoResource = new K02_RaceKekkaMemoResource();
		
		// DBからレース結果メモを取得
		K02_RaceKekkaMemo raceKekkaMemo = new K02_RaceKekkaMemo();
		try {
			raceKekkaMemo = jdbcRaceKekkaDao.getRaceKekkaMemo(raceZisshiId);
		}catch (EmptyResultDataAccessException e) {
			logger.info("レース結果メモが存在しない場合は例外処理で空白をセット");
			// データが取得できなかった場合は空白をセット
			raceKekkaMemo.setRaceZisshiId(raceZisshiId);
			raceKekkaMemo.setRaceKekkaMemo("");
		}
		
		// リソースとエンティティの変換処理
		raceKekkaMemoResource.setRaceZisshiId(raceZisshiId);
		raceKekkaMemoResource.setRaceKekkaMemo(raceKekkaMemo.getRaceKekkaMemo());
		
		return raceKekkaMemoResource;
	
	}
	
	/**
	 * 競走馬の予想を１件更新
	 * 
	 * @param K02_RaceYosouResource : レース予想リソース
	 */
	public void registerRaceYosou(K02_RaceYosouResource resource) {
		
		// リソースをDBに登録するためのエンティティへ変換
		K02_RaceYosou raceYosou = new K02_RaceYosou();
		raceYosou.setRaceZisshiId(resource.getRaceZisshiId());
		raceYosou.setKyosobaId(resource.getKyosobaId());	
		raceYosou.setYosou(resource.getYosou());
		
		// 予想を更新
		jdbcRaceKekkaDao.updateRaceYosou(raceYosou);
		
	}
	

}
