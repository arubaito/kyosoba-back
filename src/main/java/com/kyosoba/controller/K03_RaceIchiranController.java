package com.kyosoba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyosoba.model.K03_RaceInfoResource;
import com.kyosoba.service.K03_RaceIchiranService;

/**
 * レース一覧画面に関するController
 */
@RestController
public class K03_RaceIchiranController {

	// Logger
	Logger logger = LoggerFactory.getLogger(K03_RaceIchiranController.class);
	
	@Autowired
	K03_RaceIchiranService raceIchiranService;
	
	/**
	 * レース結果一覧返却
	 * 
	 * @return レース結果の一覧をレース実施日順に並べたリスト
	 */
	@GetMapping("/race-kekka-ichiran")
	public List<K03_RaceInfoResource> getRaceIchiran() {
		
		// レース一覧のリソースを取得
		List<K03_RaceInfoResource> raceInfoResourceList = raceIchiranService.getRaceIchiran();
		
		return raceInfoResourceList;

	}
}
