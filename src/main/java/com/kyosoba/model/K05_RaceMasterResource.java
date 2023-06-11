package com.kyosoba.model;

import lombok.Data;

/**
 * レースマスタ登録リソース
 */
@Data
public class K05_RaceMasterResource {

	private int raceId;
	private String raceName;
	private int grade;
	private boolean course;
	private int kyori;
	private boolean mawari;
	private int place;
}
