package com.kyosoba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyosoba.model.K05_KisyuMasterResource;
import com.kyosoba.model.K05_KyosobaMasterResource;
import com.kyosoba.model.K05_RaceMasterResource;
import com.kyosoba.model.K05_RaceResultResource;
import com.kyosoba.model.K05_RaceZisshiResource;
import com.kyosoba.service.K05_DataKanriService;

/**
 * 管理画面のデータ登録関係のController
 */
@CrossOrigin
@RestController
public class K05_DataKanriController {

	// Logger
	Logger logger = LoggerFactory.getLogger(K05_DataKanriController.class);
	
	@Autowired
	K05_DataKanriService dataKanriService;
	
	
	/**
	 * レースマスタにデータを登録
	 * @param newResource : リクエストボディで受け取るレースマスタ情報
	 * @return HTTPステータスコード
	 */
	@PostMapping("/register-race-master")
	public String registerRaceMaster(
			@RequestBody K05_RaceMasterResource newResource) {
		
		logger.info("K05_DataKanriController#registerRaceMaster");
		
		dataKanriService.registerRaceMaster(newResource);
		
		return "201 OK"; // TODO レスポンス返却結果にOK以外も追加
		
	}

	/**
	 * レースマスタの全レース名とレースIDを取得
	 * 
	 * @return レース名とレースIDをセットしたリソースのリスト
	 */
	@GetMapping("/get-race-name")
	public List<K05_RaceMasterResource> getRaceMaster() {
		
		// レースマスタのレースIDとレース名を取得
		List<K05_RaceMasterResource> raceNameAndRaceIdList = dataKanriService.getRaceMasterRaceNameAndRaceId();
		
		return raceNameAndRaceIdList;
		
	}
	
	/**
	 * レース実施概要を登録
	 * @param resource: リクエストボディで受け取るレース実施概要
	 * @return HTTPステータスコード
	 */
	@PostMapping("/register-race-zisshi")
	public String registerRaceZisshi(@RequestBody K05_RaceZisshiResource resource) {
		
		logger.info("K05_DataKanriController#registerRaceZisshi");
		
		dataKanriService.registerRaceZisshi(resource);
		
		return  "201 OK"; // TODO レスポンス返却結果にOK以外も追加
		
	}

	/**
	 * 騎手マスタを登録
	 * 
	 * @param resource: リクエストボディで受け取るレース実施概要
	 * @return HTTPステータスコード
	 */
	@PostMapping("/register-kisyu-master")
	public String registerKisyuMaster(@RequestBody K05_KisyuMasterResource resource) {
		
		logger.info("K05_DataKanriController#registerKisyuMaster");
		
		dataKanriService.registerKisyuMaster(resource);
		
		return  "201 OK"; // TODO レスポンス返却結果にOK以外も追加
		
	}
	
	
	/**
	 * 競走馬マスタを登録
	 * 
	 * @param resource: リクエストボディで受け取るレース実施概要
	 * @return HTTPステータスコード
	 */
	@PostMapping("/register-kyosoba-master")
	public String registerKyosobaMaster(@RequestBody K05_KyosobaMasterResource resource) {
		
		logger.info("K05_DataKanriController#registerKyosobaMaster");
		
		dataKanriService.registerKyosobaMaster(resource);
		
		return  "201 OK"; // TODO レスポンス返却結果にOK以外も追加
		
	}
	
	
	/**
	 *  過去のレース実施日とレース名とレース実施IDを取得
	 * 
	 * @return リソースのリスト
	 */
	@GetMapping("/get-race-zisshi-name-date")
	public List<K05_RaceZisshiResource> getRaceNameAndZisshiDate(){
		
		logger.info("K05_DataKanriController#getRaceNameAndZisshiDate");
		
		List<K05_RaceZisshiResource> raceNameAndZisshiDateList = dataKanriService.getRaceNameAndZisshiDate();
		
		return raceNameAndZisshiDateList;
	}

	
	/**
	 *  全騎手名と騎手IDを取得
	 * 
	 * @return リソースのリスト
	 */
	@GetMapping("/get-jockey")
	public List<K05_KisyuMasterResource> getJockey(){
		
		logger.info("K05_DataKanriController#getJockey");
		
		List<K05_KisyuMasterResource> resourceList = dataKanriService.getJockey();
		
		return resourceList;
	}	
	
	/**
	 *  全競走馬名とIDを取得
	 * 
	 * @return リソースのリスト
	 */
	@GetMapping("/get-kyosoba")
	public List<K05_KyosobaMasterResource> getKyosoba(){
		
		logger.info("K05_DataKanriController#getKyosoba");
		
		List<K05_KyosobaMasterResource> resourceList = dataKanriService.getKyosoba();
		
		return resourceList;
	}		
	
	
	/**
	 * レース実施結果を登録するAPI
	 * 
	 * @param resource: リソースのプロパティ{raceZisshiId}{raceResultList}をもつ
	 */
	@PostMapping("/register-race-result")
	public void registerRaceResult(
			@RequestBody K05_RaceResultResource resource) {
		
		logger.info("K05_DataKanriController#registerRaceResult");
		logger.info(Integer.toString(resource.getRaceZisshiId()));
		
		dataKanriService.registerRaceResult(resource);
		
		
		
	}
	
	
	
}

