package com.kyosoba.model;

import java.util.List;

import lombok.Data;

/**
 * レース結果を保持するリソース
 */
@Data
public class K02_RaceKekkaListResource {
	private List<K02_RaceKekkaResource> raceKekkaList;

	
	/**
	 * 競走馬単体のレース結果を保持するリソース
	 */
	@Data
	public static class K02_RaceKekkaResource{
		
		private int tyakuzyun;
		private int waku;
		private int umaban;
		private int yosou;
		private int kyosobaId;
		private String bamei;
		private String seirei;
		private String kisyu;
		private int ninki;
	}
}
