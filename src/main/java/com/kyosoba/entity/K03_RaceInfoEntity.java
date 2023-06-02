package com.kyosoba.entity;

import java.util.Date;

import lombok.Data;

/**
 * レース一覧Entity
 */
@Data
public class K03_RaceInfoEntity {

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
