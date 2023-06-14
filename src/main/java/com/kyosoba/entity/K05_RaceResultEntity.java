package com.kyosoba.entity;

import lombok.Data;

/**
 * １競走馬単位のレース結果を保持するEntity
 */
@Data
public class K05_RaceResultEntity {

	private int raceZisshiId;
	int kyosobaId;
	int kisyuId;
	int tyakuzyun;
	int waku;
	int umaban;
	int yosou;
	int ninki;
}
