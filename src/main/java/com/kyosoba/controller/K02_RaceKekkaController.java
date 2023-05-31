package com.kyosoba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kyosoba.model.K02_RaceKekkaListResource;
import com.kyosoba.service.K02_RaceKekkaService;

/**
 * 競走馬のレース結果を返すためのController
 */
@RestController
public class K02_RaceKekkaController {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(K02_RaceKekkaController.class);
	
	@Autowired
	K02_RaceKekkaService raceKekkaService;
	
	/**
	 *  レース結果返却
	 *  
	 * @param raceZisshiId : レース実施情報テーブルのレース実施ID
	 * @return 競走馬のレース実施結果を着順(昇順)で並べたリスト
	 */
	@GetMapping("/race-kekka-{raceZisshiId}")
	public K02_RaceKekkaListResource getRaceKekka(
			@PathVariable int raceZisshiId) {
		
		// レース結果のリストをServiceクラスから取得
		K02_RaceKekkaListResource raceKekkaResource = raceKekkaService. findByRaceZisshiId(raceZisshiId);
		
		return raceKekkaResource;
	}
}
