package com.kyosoba.entity;

import lombok.Data;

/**
 * あるレース結果を保持するエンティティ
 */
@Data
public class RaceKekka {
	
	private int raceZisshiId;
	private String raceName;
	private int tyakuzyun;
	private int waku;
	private int umaban;
	private int yosou;
	private int kyosobaId;
	private String bamei;
	private boolean seibetsu;
	private String kisyumei;
	private int ninki;
}
