package com.kyosoba.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.kyosoba.dao.JdbcKyosobaDao;
import com.kyosoba.entity.Kyosoba;
import com.kyosoba.model.K01_KyosobaInfoResource;
import com.kyosoba.model.K01_RaceResultResource;
import com.kyosoba.model.K01_SosenResource;
import com.kyosoba.model.K01_TsusanSeisekiResource;

/**
 * 競走馬詳細情報画面に関するAPIサービスクラス
 */
@Service
@ComponentScan("com.kyosoba.dao")
public class K01_KyosobaInfoService {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(K01_KyosobaInfoService.class);
	
	@Autowired
	JdbcKyosobaDao dao;
	
	/**
	 * IDをキーに競走馬詳細情報画面に表示する全情報を取得
	 * 
	 * @param kyosobaId:競走馬ID
	 * @return KyosobaInfoResouce:競走馬詳細情報
	 */
	public K01_KyosobaInfoResource findById(int kyosobaId) {
		
		 // IDをキーにDBから競走馬の情報を取得 
		 Kyosoba kyosoba = dao.getKyosobaEntity(kyosobaId);
		
		// APIの呼び出し元へ返却するリソースにDBの情報をセット
		K01_KyosobaInfoResource kyosobaInfo = new K01_KyosobaInfoResource();
		// 固定情報
		kyosobaInfo.setId(kyosoba.getId());
		kyosobaInfo.setName(kyosoba.getBamei());
		kyosobaInfo.setBirthday(kyosoba.getBirthday());
		kyosobaInfo.setKyusya(kyosoba.getKyusya());
		kyosobaInfo.setSeisansya(kyosoba.getSeisansya());
		kyosobaInfo.setBanushi(kyosoba.getBanushi());
		kyosobaInfo.setSyokin("---");
		
		// 祖先
		K01_SosenResource sosen = new K01_SosenResource();
		
		// 祖先の馬名を取得
		Kyosoba father = dao.getKyosobaEntity(kyosoba.getFatherId());
		Kyosoba mather = dao.getKyosobaEntity(kyosoba.getMatherId());
		Kyosoba fathersFather = dao.getKyosobaEntity(father.getFatherId());
		Kyosoba fathersMather = dao.getKyosobaEntity(father.getMatherId());
		Kyosoba mathersFather = dao.getKyosobaEntity(mather.getFatherId());
		Kyosoba mathersMather = dao.getKyosobaEntity(mather.getMatherId());
		Kyosoba fathersFathersFather = dao.getKyosobaEntity(fathersFather.getFatherId());
		Kyosoba fathersFathersMather = dao.getKyosobaEntity(fathersFather.getMatherId());
		Kyosoba fathersMathersFather = dao.getKyosobaEntity(fathersMather.getFatherId());
		Kyosoba fathersMathersMather = dao.getKyosobaEntity(fathersMather.getMatherId());
		Kyosoba mathersFathersFather = dao.getKyosobaEntity(mathersFather.getFatherId());
		Kyosoba mathersFathersMather = dao.getKyosobaEntity(mathersFather.getMatherId());
		Kyosoba mathersMathersFather = dao.getKyosobaEntity(mathersMather.getFatherId());
		Kyosoba mathersMathersMather = dao.getKyosobaEntity(mathersMather.getMatherId());
		
		sosen.setFatherName(father.getBamei());
		sosen.setMatherName(mather.getBamei());
		sosen.setFathersFatherName(fathersFather.getBamei());
		sosen.setFathersMatherName(fathersMather.getBamei());
		sosen.setMathersFatherName(mathersFather.getBamei());
		sosen.setMathersMatherName(mathersMather.getBamei());

		sosen.setFathersFathersFatherName(fathersFathersFather.getBamei());
		sosen.setFathersFathersMatherName(fathersFathersMather.getBamei());
		sosen.setFathersMathersFatherName(fathersMathersFather.getBamei());
		sosen.setFathersMathersMatherName(fathersMathersMather.getBamei());
		sosen.setMathersFathersFatherName(mathersFathersFather.getBamei());
		sosen.setMathersFathersMatherName(mathersFathersMather.getBamei());
		sosen.setMathersMathersFatherName(mathersMathersFather.getBamei());
		sosen.setMathersMathersMatherName(mathersMathersMather.getBamei());
		kyosobaInfo.setSosen(sosen);

		// 通算成績
		K01_TsusanSeisekiResource  tsusanSeiseki = new K01_TsusanSeisekiResource();
		tsusanSeiseki.setSyobusu(15);
		tsusanSeiseki.setSyorisu(7);
		kyosobaInfo.setTsusanSeiseki(tsusanSeiseki);
		
		// 直近レース結果
		List<K01_RaceResultResource>	raceResultList = new ArrayList<>();
		K01_RaceResultResource firstRace = new K01_RaceResultResource();
		firstRace.setRaceName("ヴィクトリアマイル");
		firstRace.setRaceRank("G1");
		firstRace.setDate("23/05/14");
		firstRace.setJockey("レーン");
		firstRace.setKyori(1400);
		firstRace.setPlace("東京競馬場");
		firstRace.setRank((short) 2);
		firstRace.setUmaban((short) 16);
		// リストに追加して返却
		raceResultList.add(firstRace);
		kyosobaInfo.setRaceResultList(raceResultList);
		
		return kyosobaInfo;
	}
	
	
	
	
	
	/**
	 * 両親の競走馬IDをキーに馬名を取得
	 * 
	 * @param fatherId:父親の競走馬ID
	 * @param matherId:母親の競走馬ID
	 * @return 取得した馬名のマップ(キー: fatherName, matherName)
	 */
	private Map<String, String> getSosen(int fatherId, int matherId){
		String fatherName = dao.getBamei(fatherId);
		String matherName = dao.getBamei(matherId);
		
		Map<String, String> map = new HashMap<>();
		map.put("fatherName", fatherName);
		map.put("matherName", matherName);
		return map;
	}
	
	
}
