package com.kyosoba.entity;

import lombok.Data;

/**
 * レースマスタエンティティ
 */
@Data
public class K05_RaceMasterEntity {

	private String raceName;
	private int grade;
	private boolean course;
	private int kyori;
	private boolean mawari;
	private int place;
}
