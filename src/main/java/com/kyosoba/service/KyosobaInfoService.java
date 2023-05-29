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
		
		 // IDをキーにDBから競走馬の情報を取得 
		 Kyosoba kyosoba = dao.find(kyosobaId);
		
		// APIの呼び出し元へ返却するリソースにDBの情報をセット
		KyosobaInfoResource kyosobaInfo = new KyosobaInfoResource();
		// 固定情報
		kyosobaInfo.setId(kyosoba.getId());
		kyosobaInfo.setName(kyosoba.getBamei());
		kyosobaInfo.setBirthday(kyosoba.getBirthday());
		kyosobaInfo.setKyusya(kyosoba.getKyusya());
		kyosobaInfo.setSeisansya(kyosoba.getSeisansya());
		kyosobaInfo.setBanushi(kyosoba.getBanushi());
		kyosobaInfo.setSyokin("---");
		
		// 祖先
		SosenResource sosen = new SosenResource();
		
		// 祖先の馬名を取得
		Map<String, String> ryoshin = getSosen(kyosoba.getFatherId(), kyosoba.getMatherId());	
		sosen.setFatherName(ryoshin.get("fatherName"));
		sosen.setMatherName(ryoshin.get("matherName"));
		sosen.setFathersFatherName("フレンチデピュティ");
		sosen.setFathersMatherName("ブルーアヴェニュー");
		sosen.setMathersFatherName("キングカメハメハ");
		sosen.setMathersMatherName("シラユキヒメ");
		
		kyosobaInfo.setSosen(sosen);

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
