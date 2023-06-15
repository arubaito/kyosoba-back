package com.kyosoba.entity;

import java.util.Date;

import lombok.Data;

/**
 * 競走馬マスタEntity
 */
@Data
public class K05_KyosobaMasterEntity {

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