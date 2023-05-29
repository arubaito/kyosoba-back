package com.kyosoba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kyosoba.model.KyosobaInfoResource;
import com.kyosoba.service.KyosobaInfoService;

/**
 * 競走馬詳細情報を返すためのController
 */
@RestController
@ComponentScan("com.kyosoba.service")
public class KyosobaInfoController {

	// Logger
	Logger logger = LoggerFactory.getLogger(KyosobaInfoController.class);

	@Autowired
	KyosobaInfoService kyosobaInfoService;
	
	/**
	 *  idを基に競走馬情報を返却
	 *  
	 * @param id
	 * @return
	 */
	@GetMapping("/kyosoba-info-detail/{kyosobaId}")
	public KyosobaInfoResource kyosobaInfoDetail(
			@PathVariable int kyosobaId) {

		// 競走馬情報を取得
		KyosobaInfoResource kyosobaInfo = kyosobaInfoService.findById(kyosobaId);

		return kyosobaInfo;

	}
}
