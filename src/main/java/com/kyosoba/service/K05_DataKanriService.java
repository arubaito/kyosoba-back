package com.kyosoba.service;

import java.util.ArrayList;
import java.util.List;

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
	
	
	/**
	 * 全レースのレースIDとレース名を取得
	 * 
	 * @return レースIDとレース名をセットしたResourceのリスト
	 */
	public List<K05_RaceMasterResource> getRaceMasterRaceNameAndRaceId() {
		
		
		// DBから全レースIDとレース名を取得
		List<K05_RaceMasterEntity> raceMasterEntityList = dataKanriDao.getRaceMasterRaceNameAndRaceId();
		
		// 呼び出し元に返却するリソースのリスト
		ArrayList<K05_RaceMasterResource> raceMasterResourceList = new ArrayList<K05_RaceMasterResource>();
		
		// EntityをResourceに変換してリストにセット
		raceMasterEntityList.forEach(entity -> {

			K05_RaceMasterResource resource = new K05_RaceMasterResource();
			
			resource.setRaceId(entity.getRaceId());
			resource.setRaceName(entity.getRaceName());
			
			raceMasterResourceList.add(resource);
			
		});
		
		return raceMasterResourceList;

	}
	
	
}
