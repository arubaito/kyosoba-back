package com.kyosoba.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.kyosoba.dao.JdbcKyosobaDao;
import com.kyosoba.model.KyosobaInfoResource;
import com.kyosoba.model.RaceResultResource;
import com.kyosoba.model.SosenResource;
import com.kyosoba.model.TsusanSeisekiResource;

/**
 * 競走馬詳細情報画面に関するAPIサービスクラス
 */
@Service
@ComponentScan("com.kyosoba.dao")
public class KyosobaInfoService {
	
	// Logger
	Logger logger = LoggerFactory.getLogger(KyosobaInfoService.class);
	
	@Autowired
	JdbcKyosobaDao dao;
	
	/**
	 * IDをキーに競走馬詳細情報画面に表示する全情報を取得
	 * 
	 * @param kyosobaId:競走馬ID
	 * @return KyosobaInfoResouce:競走馬詳細情報
	 */
	public KyosobaInfoResource findById(int kyosobaId) {
		
		// DBアクセス処理 // 
		int i = dao.find();
		
		// DBの情報からリソースへ変換
		KyosobaInfoResource kyosobaInfo = new KyosobaInfoResource();
		// 固定情報
		kyosobaInfo.setId(0);
		kyosobaInfo.setName("ソダシ");
		kyosobaInfo.setBirthday("2018年3月8日");
		kyosobaInfo.setKyusya("須貝尚介");
		kyosobaInfo.setSeisansya("ノーザンファーム");
		kyosobaInfo.setBanushi("金子真人ホールディングス");
		kyosobaInfo.setSyokin("59300万円");
		
		// 通算成績
		TsusanSeisekiResource  tsusanSeiseki = new TsusanSeisekiResource();
		tsusanSeiseki.setSyobusu(15);
		tsusanSeiseki.setSyorisu(7);
		kyosobaInfo.setTsusanSeiseki(tsusanSeiseki);
		
		// 直近レース結果
		List<RaceResultResource>	raceResultList = new ArrayList<>();
		RaceResultResource firstRace = new RaceResultResource();
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
		
		// 祖先
		SosenResource sosen = new SosenResource();
		sosen.setFatherName("クロフネ");
		sosen.setMatherName("ブチコ");
		sosen.setFathersFatherName("フレンチデピュティ");
		sosen.setFathersMatherName("ブルーアヴェニュー");
		sosen.setMathersFatherName("キングカメハメハ");
		sosen.setMathersMatherName("シラユキヒメ");
		
		kyosobaInfo.setSosen(sosen);
		
		return kyosobaInfo;
	}
}
