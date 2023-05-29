package com.kyosoba.entity;

import java.util.Date;

import lombok.Data;

/**
 * 競走馬テーブルエンティティ
 */
@Data
public class Kyosoba {
	private int id;
	private String bamei;
	private Date birthday;
	private String kyusya;
	private String banushi;
	private String seisansya;
	private boolean seibetsu;
	private String keiro;
	private int fatherId;
	private int matherId;
	
}
