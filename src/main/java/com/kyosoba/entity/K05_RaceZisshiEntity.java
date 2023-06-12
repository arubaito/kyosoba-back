package com.kyosoba.entity;

import java.util.Date;

import lombok.Data;

/**
 * レース実施Entity
 */
@Data
public class K05_RaceZisshiEntity {
	
	private int raceZisshiId;
	private int raceId;
	private String raceName;
	private Date kaisaiDate;
	private int tenko;
	private int baba;
	private int tousu;
}
