package com.kyosoba.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyosoba.dao.K05_DataKanriDao;
import com.kyosoba.entity.K05_KisyuMasterEntity;
import com.kyosoba.entity.K05_KyosobaMasterEntity;
import com.kyosoba.entity.K05_RaceMasterEntity;
import com.kyosoba.entity.K05_RaceResultEntity;
import com.kyosoba.entity.K05_RaceZisshiEntity;
import com.kyosoba.exception.TableDuplicatedException;
import com.kyosoba.model.K05_KisyuMasterResource;
import com.kyosoba.model.K05_KyosobaMasterResource;
import com.kyosoba.model.K05_RaceMasterResource;
import com.kyosoba.model.K05_RaceResultResource;
import com.kyosoba.model.K05_RaceZisshiResource;

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
		List<K05_RaceMasterEntity> raceMasterEntityList = dataKanriDao.selectRaceMaster();
		
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
	
	
	/**
	 * 実施したレースの概要を登録
	 * 
	 * @param resource: 登録対象のレース実施Resource
	 */
	public void registerRaceZisshi(K05_RaceZisshiResource resource) {
		
		// リソースから登録用のエンティティへ変換
		K05_RaceZisshiEntity entity = new K05_RaceZisshiEntity();
		entity.setRaceId(resource.getRaceId());
		entity.setKaisaiDate(resource.getKaisaiDate());
		entity.setTenko(resource.getTenko());
		entity.setBaba(resource.getBaba());
		entity.setTousu(resource.getTousu());
		
		// 登録
		dataKanriDao.insertRaceZisshi(entity);
		
	}
	
	
	/**
	 * 騎手を登録
	 * 
	 * @param resource: 登録対象の騎手Resource
	 */
	public void registerKisyuMaster(K05_KisyuMasterResource resource) {
		
		// リソースから登録用のエンティティへ変換
		K05_KisyuMasterEntity entity = new K05_KisyuMasterEntity();
		entity.setKisyumei(resource.getKisyumei());
		
		// 重複チェック
		int existedKisyu = dataKanriDao.countKisyuByName(entity.getKisyumei());
		if(existedKisyu == 1) {
			throw new TableDuplicatedException("テーブルにデータが既に存在します。");
		}
		
		// 登録
		dataKanriDao.insertKisyuMaster(entity);
	}
	
	/**
	 * 競走馬を登録
	 * 
	 * @param resource: 登録対象の競走馬Resource
	 */
	public void registerKyosobaMaster(K05_KyosobaMasterResource resource) {
		
		// リソースから登録用のエンティティへ変換
		K05_KyosobaMasterEntity entity = new K05_KyosobaMasterEntity();
		entity.setBamei(resource.getBamei());
		entity.setBirthday(resource.getBirthday());
		entity.setKyusya(resource.getKyusya());
		entity.setBanushi(resource.getBanushi());
		entity.setSeisansya(resource.getSeisansya());
		entity.setSebetsu(resource.isSebetsu());
		entity.setKeiro(resource.getKeiro());
		entity.setFatherId(0); // TODO:将来的には祖先を正確に入れる
		entity.setMatherId(0); 
		
		// 重複チェック
		int existedKyosoba = dataKanriDao.countKyosobaByName(entity.getBamei());
		if(existedKyosoba == 1) {
			throw new TableDuplicatedException("テーブルにデータが既に存在します。");
		}
		
		// 登録
		dataKanriDao.insertKyosobaMaster(entity);
	}

	
	/**
	 * レース実施IDと実施日とレース名を取得
	 * 
	 * @return Resourceのリスト
	 */
	public List<K05_RaceZisshiResource> getRaceNameAndZisshiDate() {
		
		
		// DBから全レースIDとレース名を取得
		List<K05_RaceZisshiEntity> raceZisshiEntityList = dataKanriDao.selectRaceNameAndZisshiDate();
		
		// 呼び出し元に返却するリソースのリスト
		List<K05_RaceZisshiResource> raceZisshiResourceList = new ArrayList<>();
		
		// EntityをResourceに変換してリストにセット
		raceZisshiEntityList.forEach(entity -> {

			K05_RaceZisshiResource resource = new K05_RaceZisshiResource();
			
			resource.setRaceZisshiId(entity.getRaceZisshiId());
			resource.setRaceName(entity.getRaceName());
			resource.setKaisaiDate(entity.getKaisaiDate());
			resource.setTousu(entity.getTousu());
			
			raceZisshiResourceList.add(resource);
			
		});
		
		return raceZisshiResourceList;

	}


	/**
	 * 全騎手名と騎手IDを取得
	 * 
	 * @return Resourceのリスト
	 */
	public List<K05_KisyuMasterResource> getJockey() {
		
		// DBから全騎手名と騎手IDを取得
		List<K05_KisyuMasterEntity> entityList = dataKanriDao.selectKisyuMaster();
		
		// 呼び出し元に返却するリソースのリスト
		List<K05_KisyuMasterResource> resourceList = new ArrayList<>();
		
		// EntityをResourceに変換してリストにセット
		entityList.forEach(entity -> {

			K05_KisyuMasterResource resource = new K05_KisyuMasterResource();
			
			resource.setKisyuId(entity.getKisyuId());
			resource.setKisyumei(entity.getKisyumei()	);
			
			resourceList.add(resource);
			
		});
		
		return resourceList;
	}

	
	/**
	 * 全競走馬名と競走馬IDを取得
	 * 
	 * @return Resourceのリスト
	 */
	public List<K05_KyosobaMasterResource> getKyosoba() {
		
		// DBから全競走馬名とIDを取得
		List<K05_KyosobaMasterEntity> entityList = dataKanriDao.selectKyosobaMaster();
		
		// 呼び出し元に返却するリソースのリスト
		List<K05_KyosobaMasterResource> resourceList = new ArrayList<>();
		
		// EntityをResourceに変換してリストにセット
		entityList.forEach(entity -> {

			K05_KyosobaMasterResource resource = new K05_KyosobaMasterResource();
			
			resource.setId(entity.getId());
			resource.setBamei(entity.getBamei()	);
			
			resourceList.add(resource);
			
		});
		
		return resourceList;
	}
	
	
	/**
	 * レース実施結果を登録
	 * 
	 * @param resource: レース実施結果Resource
	 */
	@Transactional
	public void registerRaceResult(K05_RaceResultResource resource) {
		
		// リソースから登録用のエンティティへ変換
		K05_RaceResultEntity entity = new K05_RaceResultEntity();
		
		
		
		// レース結果を各競走馬ごとに登録
		resource.getRaceResultList().forEach(raceResult -> {
			// TODO 入力値が空だった時の処理
			entity.setRaceZisshiId(resource.getRaceZisshiId());
			entity.setKyosobaId(raceResult.getKyosobaId());
			entity.setKisyuId(raceResult.getKisyuId());
			entity.setTyakuzyun(raceResult.getTyakuzyun()); // null許容
			entity.setWaku(raceResult.getWaku());
			entity.setUmaban(raceResult.getUmaban());
			entity.setNinki(raceResult.getNinki());
			
			// 登録
			dataKanriDao.insertRaceResult(entity);
			
		});

	}

}
