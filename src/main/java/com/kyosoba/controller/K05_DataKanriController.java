package com.kyosoba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyosoba.model.K05_RaceMasterResource;
import com.kyosoba.service.K05_DataKanriService;

/**
 * 管理画面のデータ登録関係の
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
	 * @param newResource : リクエストボディで受け取るレースマスタテーブルに登録するJSON
	 * @return HTTPステータスコード
	 */
	@PostMapping("/register-race-master")
	public String registerRaceMaster(
			@RequestBody K05_RaceMasterResource newResource) {
		
		logger.info("K05_DataKanriController#registerRaceMaster");
		
		dataKanriService.registerRaceMaster(newResource);
		
		return "201 OK"; // TODO レスポンス返却結果にOK以外も追加
		
	}

}
