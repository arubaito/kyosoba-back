package com.kyosoba.model;

import java.util.Date;

import lombok.Data;

/**
 * レース実施Entity
 */
@Data
public class K05_RaceZisshiResource {
	
	private int raceId;
	private Date kaisaiDate;
	private int tenko;
	private int baba;
	private int tousu;
}
