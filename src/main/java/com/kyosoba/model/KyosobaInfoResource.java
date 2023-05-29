package com.kyosoba.model;

import java.util.Date;
import java.util.List;

/**
 * 競走馬詳細情報画面に表示するデータをJSON形式で返却するためのModel
 */
public class KyosobaInfoResource {
	
	private long id;
	private String bamei;
	private Date birthday;
	private String kyusya;
	private String seisansya;
	private String banushi;
	private String syokin;
	private TsusanSeisekiResource tsusanSeiseki;
	private List<RaceResultResource> raceResultList;
	private SosenResource sosen; 

	// setter getter //
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return bamei;
	}
	public void setName(String name) {
		this.bamei = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getKyusya() {
		return kyusya;
	}
	public void setKyusya(String kyusya) {
		this.kyusya = kyusya;
	}
	public String getSeisansya() {
		return seisansya;
	}
	public void setSeisansya(String seisansya) {
		this.seisansya = seisansya;
	}
	public String getBanushi() {
		return banushi;
	}
	public void setBanushi(String banushi) {
		this.banushi = banushi;
	}
	public String getSyokin() {
		return syokin;
	}
	public void setSyokin(String syokin) {
		this.syokin = syokin;
	}
	public TsusanSeisekiResource getTsusanSeiseki() {
		return tsusanSeiseki;
	}
	public void setTsusanSeiseki(TsusanSeisekiResource tsusanSeiseki) {
		this.tsusanSeiseki = tsusanSeiseki;
	}
	public List<RaceResultResource> getRaceResultList() {
		return raceResultList;
	}
	public void setRaceResultList(List<RaceResultResource> raceResultList) {
		this.raceResultList = raceResultList;
	}
	public SosenResource getSosen() {
		return sosen;
	}
	public void setSosen(SosenResource sosen) {
		this.sosen = sosen;
	}
	// setter getter ここまで //
	
	
	
	
//	/* コンストラクタ */
//	private KyosobaInfoResource(Builder builder) {
//		this.id = builder.id;
//		this.name = builder.name;
//		this.banushi = builder.banushi;
//		this.birthday = builder.birthday;
//		this.jockey = builder.jockey;
//		this.ketto = builder.ketto;
//		this.kyusya = builder.kyusya;
//		this.seisansya = builder.seisansya;
//		this.syokin = builder.syokin;
//		this.tsusanSeiseki = builder.tsusanseiseki;
//	}
//	/* getter */
//	public long getId() {
//		return id;
//	}
//	public String getName() {
//		return name;
//	}
//	public String getBanushi() {
//		return banushi;
//	}
//	public String getBirthday() {
//		return birthday;
//	}
//	public String getJockey() {
//		return jockey;
//	}
//	public String getKetto() {
//		return ketto;
//	}
//	public String getKyusya() {
//		return kyusya;
//	}
//	public String getSeisansya() {
//		return seisansya;
//	}
//	public String getSyokin() {
//		return syokin;
//	}
//	public String getTsusanseiseki() {
//		return tsusanSeiseki;
//	}
//
//	/* ビルダクラス */ 
//	public static class Builder {
//		private long id;
//		private String name;
//		private String birthday;
//		private String kyusya;
//		private String seisansya;
//		private String jockey;
//		private String banushi;
//		private String tsusanseiseki;
//		private String syokin;
//		private String ketto; // 血統はどういう感じにしようか
//		
//		public Builder() {}
//		
//		/* ビルドメソッド */
//		public KyosobaInfoResource build() {
//			return new KyosobaInfoResource(this);
//		}
//		
//		/* setter */
//		public Builder setId(long id) {
//			this.id = id;
//			return this;
//		}
//
//		public Builder setName(String name) {
//			this.name = name;
//			return this;
//		}
//
//		public Builder setBirthday(String birthday) {
//			this.birthday = birthday;
//			return this;
//		}
//
//		public Builder setKyusya(String kyusya) {
//			this.kyusya = kyusya;
//			return this;
//		}
//
//		public Builder setSeisansya(String seisansya) {
//			this.seisansya = seisansya;
//			return this;
//		}
//
//		public Builder setJockey(String jockey) {
//			this.jockey = jockey;
//			return this;
//		}
//
//		public Builder setBanushi(String banushi) {
//			this.banushi = banushi;
//			return this;
//		}
//
//		public Builder setTsusanseiseki(String tsusanseiseki) { // Seiseki
//			this.tsusanseiseki = tsusanseiseki;
//			return this;
//		}
//
//		public Builder setSyokin(String syokin) {
//			this.syokin = syokin;
//			return this;
//		}
//
//		public Builder setKetto(String ketto) {
//			this.ketto = ketto;
//			return this;
//		}
//	}
}
