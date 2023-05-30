package com.kyosoba.model;

/**
 * 競走馬のレース成績リソース
 */
public class K01_RaceResultResource {

	private String raceName;
	private String date; // Local Date を使わねば
	private String place;
	private short umaban;
	private int kyori;
	private short rank;
	private String jockey;
	private String raceRank;

	public String getRaceRank() {
		return raceRank;
	}
	public void setRaceRank(String raceRank) {
		this.raceRank = raceRank;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public short getWaku() {
		return umaban;
	}
	public void setUmaban(short umaban) {
		this.umaban = umaban;
	}
	public int getKyori() {
		return kyori;
	}
	public void setKyori(int kyori) {
		this.kyori = kyori;
	}
	public short getRank() {
		return rank;
	}
	public void setRank(short rank) {
		this.rank = rank;
	}
	public String getJockey() {
		return jockey;
	}
	public void setJockey(String jockey) {
		this.jockey = jockey;
	}
	
	
}
