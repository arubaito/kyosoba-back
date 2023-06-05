package com.kyosoba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyosoba.model.K02_RaceKekkaListResource;
import com.kyosoba.model.K02_RaceKekkaMemoResource;
import com.kyosoba.model.K02_RaceYosouResource;
import com.kyosoba.service.K02_RaceKekkaService;

/**
 * 競走馬のレース結果画面に関するController
 */
@CrossOrigin
@RestController
public class K02_RaceKekkaController {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(K02_RaceKekkaController.class);
	
	@Autowired
	K02_RaceKekkaService raceKekkaService;
	
	/**
	 *  レース結果返却
	 *  
	 * @param raceZisshiId : レース実施情 報テーブルのレース実施ID
	 * @return 競走馬のレース実施結果を着順(昇順)で並べたリスト
	 */
	@GetMapping("/race-kekka-{raceZisshiId}")
	public K02_RaceKekkaListResource getRaceKekka(
			@PathVariable int raceZisshiId) {
		
		// レース結果のリストをServiceクラスから取得
		K02_RaceKekkaListResource raceKekkaResource = raceKekkaService. findByRaceZisshiId(raceZisshiId);
		
		return raceKekkaResource;
	}
	
	/**
	 * レース結果のメモをPOSTで受け取ってレース結果メモテーブルに登録
	 * 
	 * @param newResource:リクエストボディのリソース
	 * @return HTTPステータスコード
	 */
	@PostMapping("/register-race-kekka-memo")
	public String registerRaceKekkaMemo(
			@RequestBody K02_RaceKekkaMemoResource newResource) {
		
		raceKekkaService.registerRaceKekka(newResource);
		
		return "201 OK"; // TODO レスポンス返却結果にOK以外も追加
	}
	
	
	/**
	 * パスで指定されたレース実施IDのレース結果メモを返却
	 * 
	 * @param int: レース実施ID
	 * @return レース結果メモリソース
	 */
	@GetMapping("/get-race-kekka-memo-{raceZisshiId}")
	public K02_RaceKekkaMemoResource getRaceKekkaMemo(
			@PathVariable int raceZisshiId) {
		
		K02_RaceKekkaMemoResource raceKekkaMemo = raceKekkaService.getRaceKekkaMemo(raceZisshiId);
		
		return raceKekkaMemo;

	}

	
	/**
	 * リクエストボディで送信されてきた競走馬のレース予想を登録
	 * 
	 * @param newResource
	 * @return HTTPステータスコード
	 */
	@PostMapping("/update-race-yosou")
	public String registerRaceYosou(
			@RequestBody K02_RaceYosouResource newResource) {
		
		raceKekkaService.registerRaceYosou(newResource);
		return "201 OK"; // TODO レスポンス返却結果にOK以外も追加
	}
	
	
}
