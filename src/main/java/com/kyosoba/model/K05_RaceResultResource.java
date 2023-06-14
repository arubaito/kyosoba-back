package com.kyosoba.model;

import java.util.List;

import lombok.Data;

/**
 * レース結果Resource
 */
@Data
public class K05_RaceResultResource {

	private int raceZisshiId;
	private List<RaceResult> raceResultList;
	
	/**
	 * １競走馬単位のレース結果
	 */
	@Data
	public static class RaceResult{
		int kyosobaId;
		int kisyuId;
		int tyakuzyun;
		int waku;
		int umaban;
		int yosou;
		int ninki;
	}
}
