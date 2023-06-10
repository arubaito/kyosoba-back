package com.kyosoba.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyosoba.dao.K05_DataKanriDao;
import com.kyosoba.entity.K05_RaceMasterEntity;
import com.kyosoba.model.K05_RaceMasterResource;

/**
 * データ管理をするためのService
 */
@Service
public class K05_DataKanriService {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(K05_DataKanriService.class);
	
	@Autowired
	K05_DataKanriDao dataKanriDao;
	
	/**
	 * リソースで受け取ったレースマスタをDBに登録
	 */
	public void registerRaceMaster(K05_RaceMasterResource resource) {
		
		K05_RaceMasterEntity entity = new K05_RaceMasterEntity();
		entity.setRaceName(resource.getRaceName());
		entity.setGrade(resource.getGrade());
		entity.setPlace(resource.getPlace());
		entity.setMawari(resource.isMawari());
		entity.setKyori(resource.getKyori());
		// entity.setCourse(resource.isCourse()); // TODO:画面側からもらわないのでどうするか
		entity.setCourse(true); 
		
		// テーブルへエンティティの情報をインサート
		dataKanriDao.insertRaceMaster(entity);
		
	}
}
