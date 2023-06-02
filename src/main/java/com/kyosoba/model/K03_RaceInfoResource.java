package com.kyosoba.model;

import java.util.Date;

import lombok.Data;

/**
 * レース結果リソース
 */
@Data
public class K03_RaceInfoResource {

	private int raceZisshiId;
	private String raceName;
	private int raceGrade;
	private Date raceDate;
	private int kyori;
	private int tousu;
	private int tenko;
	private int baba;
	private String tyakuzyun1;
	private String tyakuzyun2;
	private String tyakuzyun3;
}
